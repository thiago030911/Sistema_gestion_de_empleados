package cablevision;

import java.io.Serializable;


public class AreaMercado implements Serializable {
    private String nombre;
    private String descripcion;
    
    public AreaMercado(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("Pais - Nombre: " + nombre);
        EntradaSalida.mostrarString("PIB: " + descripcion);
        EntradaSalida.mostrarString("----------------------------------------------");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean encontrarDato(String datos) {
        return datos.equals(nombre);
    }
    
    public boolean encontrarNombre(String datos) {
        return datos.equals(nombre);
    }
}
