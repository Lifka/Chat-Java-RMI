/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import rmichat.Evento;
import rmichat.Observador;
import rmichat.RMIChatCliente;

public class Privado extends javax.swing.JFrame implements Observador{

    private String usuario = "";

    public Privado(String usuario) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.usuario = usuario;
        this.setTitle("[" + RMIChatCliente.getInstance().getUsuario() + "]: Chat privado con " + usuario);
        RMIChatCliente.observar(this);
        mensaje.setText("Nuevo mensaje...");
        mensaje.setForeground(new Color(171,174,174));
    }
    
    public void actualizar(){
        jButton2.setEnabled(true);
        mensaje.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat_privado = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        mensaje = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chat_privado.setEditable(false);
        chat_privado.setColumns(20);
        chat_privado.setRows(5);
        jScrollPane1.setViewportView(chat_privado);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enviar");
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton2FocusGained(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        mensaje.setText("...");
        mensaje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mensajeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mensajeFocusLost(evt);
            }
        });
        mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensajeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(mensaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void desconectar(){
        RMIChatCliente.getInstance().desconectarPrivadoCliente(usuario);
        RMIChatCliente.getInstance().cerrarPrivado(usuario);
        RMIChatCliente.eliminarObservador(this);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        desconectar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        enviar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void enviar(){
        if (!"".equals(mensaje.getText())){
            RMIChatCliente.getInstance().enviarMensajePrivado(usuario, mensaje.getText());
            mensaje.setText("");
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       desconectar();
    }//GEN-LAST:event_formWindowClosing

    private void mensajeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mensajeFocusGained
        mensaje.setText("");
        mensaje.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_mensajeFocusGained

    private void mensajeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mensajeFocusLost

    }//GEN-LAST:event_mensajeFocusLost

    private void mensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensajeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            enviar();
        }
    }//GEN-LAST:event_mensajeKeyPressed

    private void jButton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusGained
        mensaje.requestFocus();
    }//GEN-LAST:event_jButton2FocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chat_privado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mensaje;
    // End of variables declaration//GEN-END:variables

    
    /****************      Observador         ***********************/
    @Override
    public void notify(Evento event, String[] notificacion) {
        if(null != event)switch (event) {
            case MENSAJE_PRIVADO:
                if (notificacion[0].equals(usuario)){
                    chat_privado.setText(chat_privado.getText() +
                            "[" + notificacion[1]
                            + "]: " + notificacion[2] + "\n");
                }
                break;
            case DESCONECTADO_PRIVADO:
                if (notificacion[0].equals(usuario)){
                    chat_privado.setText(chat_privado.getText() +
                            "Fin de la comunicación.\n");
                    this.setVisible(false); 
                    this.dispose();
                }
                break;
            case CONEXION_CERRADA_PRIVADO:
                if (notificacion[0].equals(usuario)){
                    chat_privado.setText(chat_privado.getText() + notificacion[0] +
                            " ha cerrado el chat.\n");
                    jButton2.setEnabled(false);
                    mensaje.setEnabled(false);
                }
                break;
            case ACTUALIZAR_PRIVADO:
                if (notificacion[0].equals(usuario)){
                    chat_privado.setText(chat_privado.getText() + notificacion[0] +
                            " está conectado.\n");
                    actualizar();
                    
                }
                break;
            default:
                break;
        }
    }
}
