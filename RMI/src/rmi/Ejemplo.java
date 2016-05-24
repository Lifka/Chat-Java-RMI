import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;
import rmi.Utils;


public class Ejemplo implements Ejemplo_I {

    public Ejemplo() {
        super();
    }

    public void escribir_mensaje (int id_proceso) {
      System.out.println("Recibida peticin de proceso: "+id_proceso);
      if (id_proceso == 0) {
        try{
          System.out.println("Empezamos a dormir");
          Thread.sleep(5000);
          System.out.println("Terminamos de dormir");
        }
        catch (Exception e) {
          System.err.println("Ejemplo exception:");
          e.printStackTrace();
        }
      }
      System.out.println("\nHebra "+id_proceso);
    }
    public static void main(String[] args) {
        
        Utils.getInstance().setCodeBase(Cliente_Ejemplo.class);
        Utils.getInstance().setPolicy("server.policy");
        Utils.getInstance().setHostName(args[0]);
        
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String nombre_objeto_remoto = "Ejemplo_I";
            Ejemplo_I prueba = new Ejemplo();
            Ejemplo_I stub =
                (Ejemplo_I) UnicastRemoteObject.exportObject(prueba, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(nombre_objeto_remoto, stub);
            System.out.println("Ejemplo bound");
        } catch (Exception e) {
            System.err.println("Ejemplo exception:");
            e.printStackTrace();
        }
    }
}
