/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package rmichat;

public enum Evento {
    /************    Eventos sala de chat     ******************/
    CONECTADO, DESCONECTADO, MENSAJE, FIN_CONEXION, 
    
    /************  Eventos para privado: P2P      **************/
    NUEVO_CONTACTO, ELIMINAR_CONTACTO, MENSAJE_PRIVADO, 
    DESCONECTADO_PRIVADO, INICIAR_PRIVADO,
    CONEXION_CERRADA_PRIVADO, ACTUALIZAR_PRIVADO
    
}
