package cablevision;

import java.io.Serializable;


public class Pais implements Serializable {
    private String nombre;
    private String pib;
    private String nHabitantes;
    private String capital;
    private boolean actividadPais;

    public Pais(String nombre, String pib, String nHabitantes, String capital) {
        this.nombre = nombre;
        this.pib = pib;
        this.nHabitantes = nHabitantes;
        this.capital = capital;
    }
    
    public void mostrar(){
        EntradaSalida.mostrarString("Pais - Nombre: " + nombre);
        EntradaSalida.mostrarString("PIB: " + pib);
        EntradaSalida.mostrarString("Numero de habitantes: " + nHabitantes);
        EntradaSalida.mostrarString("Capital: " + capital);
        EntradaSalida.mostrarString("----------------------------------------------");

    }
    
    public boolean encontrarDato(String datos) {
        return datos.equals(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getnHabitantes() {
        return nHabitantes;
    }

    public void setnHabitantes(String nHabitantes) {
        this.nHabitantes = nHabitantes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean isActividadPais() {
        return actividadPais;
    }

    public void setActividadPais(boolean actividadPais) {
        this.actividadPais = actividadPais;
    }
    
}
