package guis;

import entidades.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class Aplicacion1GUI extends javax.swing.JFrame implements Runnable{

    private String host = "localhost";
    private int puerto = 1234;
    private BufferedReader entrada;
    public BufferedWriter salida;
    public Socket socket;
    
    /**
     * Creates new form Aplicacion1GUI
     */
    public Aplicacion1GUI() {
        initComponents();
        iniciarSockets();
    }
    
    
    public Usuario deserializar(String contenido) throws ClassNotFoundException{
        byte[] bytesDecodificados = Base64.getDecoder().decode(contenido);
        Usuario usuario = null;
        try{
            ByteArrayInputStream bs= new ByteArrayInputStream(bytesDecodificados); 
            ObjectInputStream is = new ObjectInputStream(bs);
            usuario = (Usuario)is.readObject();
            is.close();
        }catch (IOException ex) {
            Logger.getLogger(Aplicacion1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public void iniciarSockets(){
        try{
            socket = new Socket(host, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Aplicacion1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtEdadUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreAlumno = new javax.swing.JTextField();
        txtEdadAlumno = new javax.swing.JTextField();
        cbxDestino = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblEntRecibida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente 1");

        jLabel1.setText("Nombre:");

        jLabel2.setText("Edad:");

        jLabel3.setText("Usuario");

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:");

        jLabel5.setText("Entidad Recibida: ");

        jLabel6.setText("Edad");

        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente Dos", "Cliente Tres" }));

        jLabel7.setText("Destino:");

        lblEntRecibida.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEntRecibida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnEnviar)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtEdadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdadAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEdadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar)
                    .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblEntRecibida))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEdadAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        ObjectOutputStream os = null;
        byte[] bytesUsuario = null;
        
        try {
            Usuario usuario = new Usuario(txtNombreUsuario.getText(), Integer.parseInt(txtEdadUsuario.getText()));
            ByteArrayOutputStream bs= new ByteArrayOutputStream();
            os = new ObjectOutputStream (bs);
            os.writeObject(usuario); 
            os.close();
            bytesUsuario =  bs.toByteArray();
        }catch (IOException ex) {
            Logger.getLogger(Aplicacion1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String usuarioBase64 = Base64.getEncoder().encodeToString(bytesUsuario);
        
        try{
            salida.write("Base64");
            salida.newLine();
            salida.write(usuarioBase64);
            salida.newLine();
            salida.write(this.cbxDestino.getSelectedItem().toString());
            salida.newLine();
            salida.flush();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }//GEN-LAST:event_btnEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblEntRecibida;
    private javax.swing.JTextField txtEdadAlumno;
    private javax.swing.JTextField txtEdadUsuario;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.setVisible(true);
        
        while(true){
            try{
                if(entrada.ready()){
                    String contenido = entrada.readLine();
                    String entidadRecibida = entrada.readLine();
                    Usuario usuario = deserializar(contenido);
                    txtNombreAlumno.setText(usuario.getNombre());
                    txtEdadAlumno.setText(Integer.toString(usuario.getEdad()));
                    lblEntRecibida.setText(entidadRecibida);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
