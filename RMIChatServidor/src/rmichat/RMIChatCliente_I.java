/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package rmichat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIChatCliente_I extends Remote{
    
    /**********    Sala de chat    **************/
    public void mostrar_mensaje(String usuario, String mensaje) throws RemoteException;
    public void desconectar() throws RemoteException;
    public void addContacto(String usuario) throws RemoteException;
    public void eliminarContacto(String usuario) throws RemoteException;
    
    /**********    Privado: P2P    **************/
    public void chatPrivadoCon(String nombre, RMIChatCliente_I usu1) throws RemoteException;
    public void enviarMensajePrivado(String mensaje, String usuario) throws RemoteException;
    public void recibirMensajePrivado(String usuario_conexion, String usuario_mensaje, String mensaje) throws RemoteException;
    public void indicarDesconexionPrivado(String usuario) throws RemoteException;
    public void revivirChatPrivado(String usuario) throws RemoteException;
}
