package cablevision;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asesor extends Persona implements Serializable {

    private String codigo;
    private String direccion;
    private String titulacion;
    private ArrayList<String> empresa;
    private ArrayList<String> area;
    private String fechaCaptacion;

    public Asesor(String codigo, String direccion, String titulacion, ArrayList<String> empresa, ArrayList<String> area, String u, String p, String fechaCaptacion) {
        setUsuario(u);
        setPassword(p);
        this.codigo = codigo;
        this.direccion = direccion;
        this.titulacion = titulacion;
        this.empresa = empresa;
        this.area = area;
        this.fechaCaptacion = fechaCaptacion;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    @Override
    public boolean proceder(SistemaHolding sistemaHolding) {
        try {
            EntradaSalida.mostrarString("Bienvenido/a al sistema, Sr(a) Asesor(a)\nA continuacion se les va a mostrar sus datos");
            mostrar();
            EntradaSalida.pausaSistema();
        } catch (IOException ex) {
            Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        EntradaSalida.mostrarString("Muchas gracias, Sr(a) Asesor(a). Eso ha sido todo por ahora...");
        return true;
    }

    @Override
    public void mostrar() {
        EntradaSalida.mostrarString("Asesor - Codigo: " + codigo);
        EntradaSalida.mostrarString("Usuario: " + this.getUsuario());
        EntradaSalida.mostrarString("Password: " + this.getPassword());
        EntradaSalida.mostrarString("Direccion: " + direccion);
        EntradaSalida.mostrarString("Titulacion: " + titulacion);
        EntradaSalida.mostrarString("Numero de empresas trabajando: " + empresa.size());
        for (int i = 0; i < empresa.size(); i++) {
            System.out.println((i + 1) + "-" + empresa.get(i));
        }
        EntradaSalida.mostrarString("Numero de areas trabajando: " + area.size());
        for (int i = 0; i < area.size(); i++) {
            System.out.println((i + 1) + "-" + area.get(i));
        }
        EntradaSalida.mostrarString("----------------------------------------------");

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ArrayList<String> empresa) {
        this.empresa = empresa;
    }

    public ArrayList<String> getArea() {
        return area;
    }

    public void setArea(ArrayList<String> area) {
        this.area = area;
    }

    public String getFechaCaptacion() {
        return fechaCaptacion;
    }

    public void setFechaCaptacion(String fechaCaptacion) {
        this.fechaCaptacion = fechaCaptacion;
    }

    public boolean encontrar1(String datos) {
        return datos.equals(this.getUsuario());
    }

    public boolean encontrarCodigo(String datos) {
        return datos.equals(codigo);
    }

    public String devolcerUsuario1() {
        return this.getUsuario();
    }

}
