package middleware;

import guis.Aplicacion1GUI;
import entidades.Usuario;
import entidades.Alumno;
import com.fasterxml.jackson.databind.ObjectMapper;
import entidades.Profesor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class Middleware {

    private static volatile Middleware instance;
    private ObjectMapper objectMapper;
    private static Map<String, BufferedWriter> clientes = new HashMap<>();
    BufferedWriter salidaCliente1;
    BufferedWriter salidaCliente2;
    BufferedWriter salidaCliente3;

    public Middleware() {
        objectMapper = new ObjectMapper();

        try {
            ServerSocket socketServidor = new ServerSocket(1234);
            while (true) {
                Socket socketCliente1 = socketServidor.accept();
                BufferedReader entradaCliente1 = new BufferedReader(new InputStreamReader(socketCliente1.getInputStream()));
                salidaCliente1 = new BufferedWriter(new OutputStreamWriter(socketCliente1.getOutputStream()));
                clientes.put("Cliente Uno", salidaCliente1);

                Socket socketCliente2 = socketServidor.accept();
                BufferedReader entradaCliente2 = new BufferedReader(new InputStreamReader(socketCliente2.getInputStream()));
                salidaCliente2 = new BufferedWriter(new OutputStreamWriter(socketCliente2.getOutputStream()));
                clientes.put("Cliente Dos", salidaCliente2);

                Socket socketCliente3 = socketServidor.accept();
                BufferedReader entradaCliente3 = new BufferedReader(new InputStreamReader(socketCliente3.getInputStream()));
                salidaCliente3 = new BufferedWriter(new OutputStreamWriter(socketCliente3.getOutputStream()));
                clientes.put("Cliente Tres", salidaCliente3);

                boolean corriendo = true;

                while (corriendo) {

                    if (entradaCliente1.ready()) {
                        String tipoExportacionUsada = entradaCliente1.readLine();
                        String informacion = entradaCliente1.readLine();
                        String destinatario = entradaCliente1.readLine();
                        System.out.println(tipoExportacionUsada);
                        System.out.println(informacion);
                        System.out.println(destinatario);
                        String informacionConvertida = convertirInformacion(informacion, tipoExportacionUsada, "Usuario", destinatario);
                        enviarInformacion(destinatario, informacionConvertida, "Usuario");
                    }

                    if (entradaCliente2.ready()) {
                        String tipoExportacionUsada = entradaCliente2.readLine();
                        String informacion = entradaCliente2.readLine();
                        String destinatario = entradaCliente2.readLine();
                        System.out.println(tipoExportacionUsada);
                        System.out.println(informacion);
                        System.out.println(destinatario);
                        String informacionConvertida = convertirInformacion(informacion, tipoExportacionUsada, "Alumno", destinatario);
                        enviarInformacion(destinatario, informacionConvertida, "Alumno");
                    }

                    if (entradaCliente3.ready()) {
                        String tipoExportacionUsada = entradaCliente3.readLine();
                        String informacion = entradaCliente3.readLine();
                        String destinatario = entradaCliente3.readLine();
                        System.out.println(tipoExportacionUsada);
                        System.out.println(informacion);
                        System.out.println(destinatario);
                        if(destinatario.equals("Cliente Uno")){
                            enviarInformacion(destinatario, informacion, "Profesor");
                        }else{
                            String informacionConvertida = convertirInformacion(informacion, tipoExportacionUsada, "Profesor", destinatario);
                            enviarInformacion(destinatario, informacionConvertida, "Profesor");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarInformacion(String destinatario, String informacion, String tipoEntidad) throws IOException {
        BufferedWriter destino = clientes.get(destinatario);

        if (destino == null) {
            System.out.println("El destinatario no está conectado.");
        } else {
            destino.write(informacion);
            destino.newLine();
            destino.write(tipoEntidad);
            destino.newLine();
            destino.flush();
        }
    }

    public String convertirInformacion(String informacion, String tipoExportacion, String entidadOrigen, String destinatario) throws IOException, ClassNotFoundException {
        String informacionConvertida = null;

        switch (tipoExportacion) {
            case "Base64": {
                informacionConvertida = convertirAJson(informacion, entidadOrigen);
                break;
            }

            case "Json": {
                informacionConvertida = convertirABase64(informacion, destinatario);
                break;
            }
        }
        return informacionConvertida;
    }

    public String convertirABase64(String informacionJson, String destinatario) {
        Alumno alumno = null;
        try {
            alumno = objectMapper.readValue(informacionJson, Alumno.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ObjectOutputStream os = null;
        byte[] bytesUsuario = null;
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bs);
            if(destinatario.equals("Cliente Uno")){
                Usuario usuario = new Usuario(alumno.getNombre(), alumno.getEdad());
                os.writeObject(usuario);
            }else{
                Profesor profesor = new Profesor(alumno.getNombre(), alumno.getEdad());
                os.writeObject(profesor);
            }
            
            os.close();
            bytesUsuario = bs.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Aplicacion1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String usuarioBase64 = Base64.getEncoder().encodeToString(bytesUsuario);
        return usuarioBase64;
    }

    public String convertirAJson(String informacionBase64, String entidadOrigen) throws IOException, ClassNotFoundException {
        Alumno alumno = new Alumno();
        byte[] bytesDecodificados = Base64.getDecoder().decode(informacionBase64);

        try {
            ByteArrayInputStream bs = new ByteArrayInputStream(bytesDecodificados);
            ObjectInputStream is = new ObjectInputStream(bs);
            
            if ("Usuario".equals(entidadOrigen)) {
                Usuario entidad = (Usuario) is.readObject();
                alumno.setNombre(entidad.getNombre());
                alumno.setEdad(entidad.getEdad());
            } else {
                Profesor entidad = (Profesor) is.readObject();
                alumno.setNombre(entidad.getNombre());
                alumno.setEdad(entidad.getEdad());
            }
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Aplicacion1GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String alumnoJson = null;
        try {
            alumnoJson = objectMapper.writeValueAsString(alumno);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return alumnoJson;
    }

    public static Middleware getInstance() {
        Middleware result = instance;
        if (result != null) {
            return result;
        }

        synchronized (Middleware.class) {
            return instance;
        }
    }

}
