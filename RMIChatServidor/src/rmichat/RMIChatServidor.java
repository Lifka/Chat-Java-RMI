/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package rmichat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class RMIChatServidor implements RMIChatServidor_I{
    
    // Singleton
    private static RMIChatServidor instance = new RMIChatServidor();
    
    // Observador
    private static ArrayList<Observador> observadores = new ArrayList();
    
    // RMI
    private static Registry registry;
    private static final String NOMBRE_OBJ_REMOTO = "RMIChatServidor_I";
    private static String POLICY = "rmichat/server.policy";
    private static String HOST = "localhost";
    private static RMIChatServidor_I servidor = null;
    
    // Clientes
    private static HashMap<String, RMIChatCliente_I> clientes = new HashMap();
    
    
    
    /***********  Singleton  *************/
            
    private RMIChatServidor(){
    }
    
    public static RMIChatServidor getInstance(){
        return instance;
    }
    
    /***********  Observador  *************/
    public static void observar(Object observador){
        if (!observadores.contains(observador))
            observadores.add((Observador)observador);
    }
    
    public static void notificar(Evento event, String[] notificacion){
        for(int i = 0; i < observadores.size(); i++)
            observadores.get(i).notify(event, notificacion);
    }
    
    
    /***********  set's propiedades  *************/
    
    public static void setPolicy(String policy){
        POLICY = policy;
        Utils.getInstance().setPolicy(POLICY);
    }
    
    public static void setHost(String host){
        HOST = host; 
        Utils.getInstance().setHostName(HOST);
    }
    
    
    /***********  Realizar conexión  *************/
    
    public static void init(){
        Utils.getInstance().setCodeBase(RMIChatServidor_I.class);
        
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Iniciando servidor");
            
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            servidor = new RMIChatServidor();
            RMIChatServidor_I stub = (RMIChatServidor_I) UnicastRemoteObject.exportObject( servidor, 0);
            registry = LocateRegistry.getRegistry();
            registry.rebind(NOMBRE_OBJ_REMOTO, stub);
            
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Esperando peticiones...");
            
        } catch (Exception e) {
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET +
                    " Error al iniciar servidor: " + e.getMessage());
        }
    }

    /***********  Registrar clientes  *************/
    
    @Override
    public boolean registrar(String nombre, RMIChatCliente_I cliente) {
        boolean result = false;
        if (!clientes.containsKey(nombre)){
            
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Registrando a " + nombre);
            
            result = true;
            
            notificar(Evento.CONECTADO, new String[]{nombre});
            agregarContacto(nombre);
            clientes.keySet().stream().forEach((usuario) -> {
                try{
                    cliente.addContacto(usuario);
                } catch(Exception e){
                    /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                            " Error al agregar contactos " + e.getMessage());
                }
            });
            clientes.put(nombre, cliente);
            
        }
     return result;
    }
  
    
    /***********  Enviar mensajes  *************/
    
    @Override
    public void difundir_mensaje(String usuario, String mensaje){ 
            
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Difundiendo mensaje de " + usuario);
            
        try{
            for(RMIChatCliente_I cliente : clientes.values()){
               cliente.mostrar_mensaje(usuario, mensaje);
            }
            
            notificar(Evento.MENSAJE, new String[]{usuario,mensaje});
            
        } catch(Exception e){
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                    " Error al conectar con los clientes -> " + e.getMessage());
        }
    }
    
    
    /***********  Desconexión  *************/

    @Override
    public void desconectar(String nombre) {
       notificar(Evento.DESCONECTADO, new String[]{nombre});
       clientes.remove(nombre);
       eliminarContacto(nombre);
       
       /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "" + nombre + " desconectado");
            
    }
    
    public void desconectarServidor(){
       
       /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Desconectando servidor...");
       
        for(String cliente : clientes.keySet()){
            try{
                notificar(Evento.DESCONECTADO, new String[]{cliente});
                eliminarContacto(cliente);
                clientes.get(cliente).desconectar();
            } catch(Exception e){
                /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error al desconectar a " + cliente + " " + e.getMessage());
            }
        }
        
        clientes = new HashMap();
        
        System.gc();
        System.runFinalization();
        try{
            UnicastRemoteObject.unexportObject(servidor, true);
            registry.unbind(NOMBRE_OBJ_REMOTO);
        } catch(Exception e){
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                    " Error al desconectar el servidor --> " + e.getMessage());
        }
        registry = null;
        /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Servidor desconectado");
    }
    
    
    
    /***********  Chats privados: p2p  *************/
    
    // Lista de usuarios conectados
    public void agregarContacto(String usuario){
        clientes.values().stream().forEach((cliente) -> {
            try{
                cliente.addContacto(usuario);
            } catch (Exception e){
            }
        });
    }
    
    public void eliminarContacto(String usuario){
        clientes.values().stream().forEach((cliente) -> {
            try{
                cliente.eliminarContacto(usuario);
            } catch (Exception e){
            }
        });
    }

    // Iniciar chat p2p
    @Override
    public void chatPrivadoEntre(String usu1, String usu2) {
        try{
            clientes.get(usu1).chatPrivadoCon(usu2, clientes.get(usu2));
            clientes.get(usu2).chatPrivadoCon(usu1, clientes.get(usu1));
            notificar(Evento.PRIVADO, new String[]{usu1,usu2});
        } catch (Exception e){
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                    " Error al iniciar un char privado entre " + ColorTerminal.BLUE
                    + usu1 + ColorTerminal.RESET + " y " + ColorTerminal.BLUE + usu2
                    + ColorTerminal.RESET + " --> " + e.getMessage());
            
        }
    }
}
