package cablevision;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vendedor extends Persona implements Serializable {

    private String empresa;
    private String codigo;
    private String direccion;
    private ArrayList<String> subVendedores;

    public Vendedor(String empresa, String codigo, String direccion, String u, String p, ArrayList<String> subVendedores) {
        setUsuario(u);
        setPassword(p);
        this.empresa = empresa;
        this.codigo = codigo;
        this.direccion = direccion;
        this.subVendedores = subVendedores;
    }

    @Override
    public boolean proceder(SistemaHolding sistemaHolding) {
        try {
            EntradaSalida.mostrarString("Bienvenido/a al sistema, Sr(a) Vendedor(a)\nA continuacion se les va a mostrar sus datos");
            mostrar();
            EntradaSalida.pausaSistema();
        } catch (IOException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        EntradaSalida.mostrarString("Muchas gracias, Sr(a) Vendedor(a). Eso ha sido todo por ahora...");
        return true;
    }

    @Override
    public void mostrar() {
        EntradaSalida.mostrarString("Vendedor - Codigo: " + codigo);
        EntradaSalida.mostrarString("Usuario: " + this.getUsuario());
        EntradaSalida.mostrarString("Password: " + this.getPassword());
        EntradaSalida.mostrarString("Direccion: " + direccion);
        EntradaSalida.mostrarString("Nombre de la empresa: " + empresa);
        EntradaSalida.mostrarString("Numero de vendedores captados: " + (subVendedores.size() - 1));
        for (int i = 0; i < subVendedores.size() - 1; i++) {
            System.out.println((i + 1) + "-" + subVendedores.get(i + 1));
        }
        EntradaSalida.mostrarString("----------------------------------------------");
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<String> getVendedores() {
        return subVendedores;
    }

    public boolean encontrar(String datos) {
        return datos.equals(this.getUsuario());
    }

    public String devolcerUsuario() {
        return this.getUsuario();
    }
    
    public boolean encontrarCodigoVendedor(String datos) {
        return datos.equals(codigo);
    }

}
