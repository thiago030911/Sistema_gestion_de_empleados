package cablevision;

import java.io.IOException;
import java.io.Serializable;

public class Control implements Serializable{

    public void ejecutar(){

        SistemaHolding sistemaHolding = new SistemaHolding();

        boolean seguir;
        try {
            sistemaHolding = sistemaHolding.deSerializar("cablevicion.txt");
            seguir = EntradaSalida.leerBoolean("SISTEMA HOLDING DE CABLEVICION\nDesea ingresar?");
        } catch (Exception e) {
            String usuario = EntradaSalida.leerString("Arranque inicial del sistema.\n"
                    + "Sr(a) Administrador(a), ingrese su nombre de usuario:");
            if (usuario.equals("")) {
                throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
            }
            String password = EntradaSalida.leerPassword("Ingrese su password:");
            if (password.equals("")) {
                throw new NullPointerException("ERROR: La password no puede ser nula.");
            }
            sistemaHolding.getPersonas().add(new Administrador(usuario, password));
            try {
                sistemaHolding.serializar("cablevicion.txt");
                EntradaSalida.mostrarString("El arranque ha sido exitoso. Ahora se debe reiniciar el sistema...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            seguir = false;
        }

        while (seguir) {
            String usuario = EntradaSalida.leerString("Ingrese el usuario:");
            String password = EntradaSalida.leerPassword("Ingrese la password:");

            Persona p = sistemaHolding.buscarUsuario(usuario + ":" + password);

            if (p == null) {
                EntradaSalida.mostrarString("ERROR: La combinacion usuario/password ingresada no es valida.");
            } else {
                seguir = p.proceder(sistemaHolding);
            }
        }
    }
}
