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

public class RMIChatCliente implements RMIChatCliente_I{

    /***************   RMI   *******************/
    private static RMIChatServidor_I servidor = null;
    private static String POLICY = "rmichat/server.policy";
    private static String HOST = "localhost";
    private static String usuario = null;
    private static boolean conectado = false;
    private static Registry registry = null;
    
    // Clientes conectados (Salas privadas abiertas) (P2P)
    private static HashMap<String, RMIChatCliente_I> clientes;
    
    /***************    Singleton  *******************/
    private static RMIChatCliente instance = new RMIChatCliente();
    
    /***************    Observador  *******************/
    private static ArrayList<Observador> observadores = new ArrayList();
    
    
    
    /**************************************************************************/
    
    
    /***************    Singleton  *******************/
    private RMIChatCliente(){
    }
    
    public static RMIChatCliente getInstance(){
        return instance;
    }
    
    
    /***************    Observador  *******************/
    public static void observar(Object observador){
        if (!observadores.contains(observador))
            observadores.add((Observador)observador);
    }
    
    public static void eliminarObservador(Object observador){
        observadores.remove((Observador)observador);
    }
    
    public static void notificar(Evento event, String[] notificacion){
        for(int i = 0; i < observadores.size(); i++)
            observadores.get(i).notify(event, notificacion);
    }
    
    /*************   Setter's RMI   ******************/
    public static  void setPolicy(String policy){
        POLICY = policy;
        Utils.getInstance().setPolicy(POLICY);
    }
    
    public static void setHost(String host){
        HOST = host;
    }
    
    public static void setUsuario(String usu){
        usuario = usu;
    }
    
    
    /*************   Getter's RMI   ******************/
    public String getUsuario(){
        return usuario;
    }
    
    
    /***************    Conexión  *******************/
    public int connect(){
        int result = 0;
        Utils.getInstance().setCodeBase(RMIChatServidor_I.class);
        
        
        /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Conectando a " + HOST + "...");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String nombre_objeto_remoto = "RMIChatServidor_I";
            
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Buscando el objeto remoto");
            
            registry = LocateRegistry.getRegistry(HOST);
            servidor = (RMIChatServidor_I) registry.lookup(nombre_objeto_remoto);
            
            
            if (servidor == null){
                
                /***/System.out.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " No se ha podido establecer conexión");
                
                conectado = false;
            } else {
                conectado = true;
                
                /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Exportando cliente");
            
                if(!servidor.registrar(usuario, (RMIChatCliente_I)UnicastRemoteObject.exportObject(this, 0))){
                    result = 1;
                    UnicastRemoteObject.unexportObject(this, true);
                    
                    /***/System.out.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error 1, el cliente ya existe");
                }
                clientes = new HashMap();
            }
           
            
        } catch (Exception e) {
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error 2, no es posible establecer conexión: " + e.getMessage());
            conectado = false;
            result = 2;
            try{
                UnicastRemoteObject.unexportObject(this, true);
            } catch(Exception ex){
               /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Problema con la exportación del cliente: " + ex.getMessage());
            }
        }
        
        return result;
        
    }
    
    
    /***************    Desconexión  *******************/
    @Override
    public void desconectar(){
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "El servidor quiere desconectar. Desconectando...");
            
            notificar(Evento.FIN_CONEXION, new String[]{});
            conectado = false;
            try{
                deconectarTodosPrivados();
                UnicastRemoteObject.unexportObject(this, true);
            } catch(Exception e){
               /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " No se ha podido desconectar el cliente: " + e.getMessage());
            }
            /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "" + getUsuario() + " desconectado");
            System.gc();
            System.runFinalization();
            
    }
    
    public void desconectarServidor(){
        /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Desconectando del servidor...");
        notificar(Evento.DESCONECTADO, new String[]{});
        conectado = false;
       try{
            servidor.desconectar(usuario);
            deconectarTodosPrivados();
            UnicastRemoteObject.unexportObject(this, true);
        } catch(Exception e){
               /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Servidor offline: " + e.getMessage());
        }
        /***/System.out.println(ColorTerminal.GREEN + "[*]" + ColorTerminal.RESET
                    + "Desconectado");
        System.gc();
        System.runFinalization();
    }
    
    
    /***************    Mensajería  *******************/
    
    public void enviarMensaje(String mensaje){
        if (conectado){
            try{
                servidor.difundir_mensaje(getUsuario(), mensaje);
            } catch(Exception e){
                /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error al conectar con el servidor para enviar un mensaje: "
                        + " mensaje \"" + mensaje + "\" de " + getUsuario() + e.getMessage());
               /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Se cerrará la conexión por precaución");
                notificar(Evento.FIN_CONEXION, new String[]{});
            }
        }
    }

    @Override
    public void mostrar_mensaje(String usuario, String mensaje){
        notificar(Evento.MENSAJE, new String[]{usuario, mensaje});
    }
    
    
    
    
    
    /**************************************************/
    /*             P2P: Mensajes privados            */
    /*************************************************/

    /***************    Lista conectados  *******************/
    @Override
    public void addContacto(String usuario) {
        notificar(Evento.NUEVO_CONTACTO, new String[]{usuario});
    }

    @Override
    public void eliminarContacto(String usuario) {
        if (usuario != getUsuario())
            notificar(Evento.ELIMINAR_CONTACTO, new String[]{usuario});
    }

    /***************    Chat privado (conexión)  *******************/
    @Override
    public void chatPrivadoCon(String nombre, RMIChatCliente_I usu1){
        if (!clientes.containsKey(nombre)){
            clientes.put(nombre, usu1);
            notificar(Evento.INICIAR_PRIVADO, new String[]{nombre});
        }
    }
    
    public boolean iniciarChatPrivadoCon(String usu2){
        boolean result = false;
        if (conectado && !clientes.containsKey(usu2)){
            result = true; 
            try{
                servidor.chatPrivadoEntre(getUsuario(), usu2);
                
                try{
                    clientes.get(usu2).revivirChatPrivado(getUsuario());
                } catch(Exception e){ 
                /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                    " Error al revivir al servidor de chat privado: " 
                     + e.getMessage());
                }
                  
                
                
            } catch(Exception e){
                result = false; 
                /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error al notificar al servidor de chat privado: " 
                        + e.getMessage());
                notificar(Evento.FIN_CONEXION, new String[]{});
            }
        } else {
                result = false;
        }
        return result;
    }
    
    @Override
    public void revivirChatPrivado(String usuario){
           notificar(Evento.ACTUALIZAR_PRIVADO, new String[]{usuario});
    }
    
    
    
    /***************    Desconexión  *******************/
    @Override
    public void indicarDesconexionPrivado(String usuario) {
        
            notificar(Evento.CONEXION_CERRADA_PRIVADO, new String[]{usuario});
    }
    
    public void cerrarPrivado(String usuario){
            notificar(Evento.DESCONECTADO_PRIVADO, new String[]{usuario});
    }
    
    public void desconectarPrivadoCliente(String usuario){
        if (clientes.get(usuario) != null){
                try{
                    clientes.get(usuario).indicarDesconexionPrivado(getUsuario());
                    clientes.remove(usuario);
                } catch(Exception e){
                    /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error al desconectar " + usuario);    
                }
        }
    }
    
    public void deconectarTodosPrivados(){
        /***/System.out.println("[*] Desconectado todos los chats privados..."); 
        
        for(String cliente : clientes.keySet()){
            desconectarPrivadoCliente(cliente);
            cerrarPrivado(cliente);
        }
    }
    
    /***************    Mensajería privada  *******************/
    @Override
    public void enviarMensajePrivado(String usuario, String mensaje)  {
        try{
            recibirMensajePrivado(usuario, getUsuario(), mensaje);
            clientes.get(usuario).recibirMensajePrivado(getUsuario(), getUsuario(), mensaje);
        } catch(Exception e){
            /***/System.err.println(ColorTerminal.RED + "(!)" + ColorTerminal.RESET + 
                        " Error al conectar con el cliente privado para enviar un mensaje: " 
                    + e.getMessage());        
            notificar(Evento.DESCONECTADO_PRIVADO, new String[]{});
        }
    }

    @Override
    public void recibirMensajePrivado(String usuario_conexion, String usuario_mensaje, String mensaje) {
        notificar(Evento.MENSAJE_PRIVADO, new String[]{usuario_conexion, usuario_mensaje, mensaje});
    }

    
}
