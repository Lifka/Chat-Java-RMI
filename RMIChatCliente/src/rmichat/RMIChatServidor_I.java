/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package rmichat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIChatServidor_I extends Remote{
    public boolean registrar(String nombre, RMIChatCliente_I cliente) throws RemoteException;
    public void difundir_mensaje(String usuario, String mensaje) throws RemoteException;
    public void desconectar(String nombre) throws RemoteException;
    
    /**** P2P ****/
    public void chatPrivadoEntre(String usu1, String usu2) throws RemoteException;
}
