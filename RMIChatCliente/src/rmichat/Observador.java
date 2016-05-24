/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package rmichat;

public interface Observador {
    public void notify(Evento event, String[] notificacion);
}
