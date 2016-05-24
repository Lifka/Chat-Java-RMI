/****************************************
 *      Izquierdo Vera, Javier
 *      javierizquierdovera@gmail.com
 ***************************************/
package GUI;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import rmichat.Evento;
import rmichat.Observador;
import rmichat.RMIChatCliente;

public class Ventana extends javax.swing.JFrame implements Observador, ItemListener{

    public Ventana() {
        initComponents();
        start();
        RMIChatCliente.getInstance().observar(this);
    }
    
    public void start(){
        texto.setEnabled(true);
        jButton2.setEnabled(true);
        panelinicio.setVisible(true);
        panelinicio.setEnabled(true);
        panel_programa.setVisible(false);
        panel_programa.setEnabled(false);
        this.setSize(465, 231);
        this.setTitle("Chat cliente");
        panelinicio.setSize(465, 231);
        chat.setText("");
        list1.removeAll();
        list1.addItemListener(this);
           
        DefaultCaret caret = (DefaultCaret)chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        texto.setText("Nuevo mensaje...");
        texto.setForeground(new Color(171,174,174));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelinicio = new javax.swing.JPanel();
        usuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        policy = new javax.swing.JTextField();
        host = new javax.swing.JTextField();
        panel_programa = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        texto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        usuario.setText("Nombre de usuario");
        usuario.setSelectionStart(1);
        usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuarioFocusGained(evt);
            }
        });
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jButton1.setText("Acceder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        policy.setText("rmichat/server.policy");
        policy.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                policyFocusGained(evt);
            }
        });
        policy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyActionPerformed(evt);
            }
        });

        host.setText("localhost");
        host.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hostFocusGained(evt);
            }
        });
        host.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelinicioLayout = new javax.swing.GroupLayout(panelinicio);
        panelinicio.setLayout(panelinicioLayout);
        panelinicioLayout.setHorizontalGroup(
            panelinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelinicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(host)
                    .addComponent(policy)
                    .addComponent(usuario))
                .addContainerGap())
            .addGroup(panelinicioLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelinicioLayout.setVerticalGroup(
            panelinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelinicioLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(policy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        chat.setEditable(false);
        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        texto.setForeground(new java.awt.Color(171, 174, 174));
        texto.setText("...");
        texto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoFocusLost(evt);
            }
        });
        texto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textoKeyPressed(evt);
            }
        });

        jButton2.setText("Enviar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_programaLayout = new javax.swing.GroupLayout(panel_programa);
        panel_programa.setLayout(panel_programaLayout);
        panel_programaLayout.setHorizontalGroup(
            panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_programaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_programaLayout.createSequentialGroup()
                        .addGroup(panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(texto, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        panel_programaLayout.setVerticalGroup(
            panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_programaLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_programaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_programa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelinicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_programa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        usuario.setText("");
    }//GEN-LAST:event_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(usuario.getText() == ""){
               JOptionPane.showMessageDialog(new JFrame(),
                       "Nombre de usuario inválido.","Error",
                       JOptionPane.ERROR_MESSAGE);
        } else {
            RMIChatCliente.getInstance().setHost(host.getText());                                   
            RMIChatCliente.getInstance().setPolicy(policy.getText());                                   
            RMIChatCliente.getInstance().setUsuario(usuario.getText());  


           int result = RMIChatCliente.getInstance().connect();
           if(result == 0){
               this.setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().
                       getDefaultScreenDevice().getDisplayMode().getWidth()/2, 
                       GraphicsEnvironment.getLocalGraphicsEnvironment().
                               getDefaultScreenDevice().getDisplayMode().getHeight()/2);
            panelinicio.setVisible(false);
            panelinicio.setEnabled(false);
            panel_programa.setVisible(true);
            panel_programa.setEnabled(true);
               panel_programa.setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().
                       getDefaultScreenDevice().getDisplayMode().getWidth()/2, 
                       GraphicsEnvironment.getLocalGraphicsEnvironment().
                               getDefaultScreenDevice().getDisplayMode().getHeight()/2);
               this.setTitle("Chat cliente - Conectado como " + usuario.getText());
           } else if (result == 1){
               JOptionPane.showMessageDialog(new JFrame(),
                       "Usuario ya registrado, pruebe con otro nombre.","Error",
                       JOptionPane.ERROR_MESSAGE);
           } else if(result == 2){
               JOptionPane.showMessageDialog(new JFrame(),
                       "Imposible conectar con el servidor.","Error",
                       JOptionPane.ERROR_MESSAGE);
           } else {
               JOptionPane.showMessageDialog(new JFrame(),
                       "Error desconocido.","Error",
                       JOptionPane.ERROR_MESSAGE);
           }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void policyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyActionPerformed
        
    }//GEN-LAST:event_policyActionPerformed

    private void hostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostActionPerformed
        
    }//GEN-LAST:event_hostActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RMIChatCliente.getInstance().desconectarServidor();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        enviar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void enviar(){
        if (!"".equals(texto.getText()))
            RMIChatCliente.getInstance().enviarMensaje(texto.getText());
        texto.setText("");
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        RMIChatCliente.getInstance().desconectarServidor();
    }//GEN-LAST:event_formWindowClosing

    private void textoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoFocusGained
        texto.setText("");
        texto.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_textoFocusGained

    private void hostFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hostFocusGained
        
    }//GEN-LAST:event_hostFocusGained

    private void policyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_policyFocusGained
        
    }//GEN-LAST:event_policyFocusGained

    private void usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuarioFocusGained
        usuario.setText("");
    }//GEN-LAST:event_usuarioFocusGained

    private void textoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoFocusLost
        
    }//GEN-LAST:event_textoFocusLost

    private void textoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            enviar();
        }
    }//GEN-LAST:event_textoKeyPressed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chat;
    private javax.swing.JTextField host;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.List list1;
    private javax.swing.JPanel panel_programa;
    private javax.swing.JPanel panelinicio;
    private javax.swing.JTextField policy;
    private javax.swing.JTextField texto;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables

    
    /*************    Observador        *******/
    @Override
    public void notify(Evento event, String[] notificacion) {
        if (null != event)switch (event) {
            case MENSAJE:
                chat.setText(chat.getText() +
                        "[" + notificacion[0]
                        + "]: " + notificacion[1] + "\n");
                break;
            case FIN_CONEXION:
                JOptionPane.showMessageDialog(new JFrame(),
                        "Se ha cerrado la conexión con el servidor.","Conexión cerrada",
                        JOptionPane.ERROR_MESSAGE);
                texto.setEnabled(false);
                jButton2.setEnabled(false);
                
                break;
            case DESCONECTADO:
                panel_programa.setVisible(false);
                start();
                break;
            case NUEVO_CONTACTO:
                list1.add(notificacion[0]);
                chat.setText(chat.getText() +
                        notificacion[0] + " se ha conectado.\n");
                break;
            case ELIMINAR_CONTACTO:
                    list1.remove(notificacion[0]);
                chat.setText(chat.getText() +
                        notificacion[0] + " se ha desconectado.\n");
                break;
            case INICIAR_PRIVADO:
                Privado privado = new Privado(notificacion[0]);
                privado.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        boolean result = RMIChatCliente.getInstance().iniciarChatPrivadoCon(list1.getItem(Integer.valueOf(ie.getItem().toString())));
    
        if (!result){
            System.err.println("No se ha podido inicar el chat privado.");
        }
    
    }
}
