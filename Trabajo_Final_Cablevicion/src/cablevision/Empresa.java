package cablevision;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Empresa implements Serializable {

    private String nombre;
    private String fechaEntrada;
    private String facturacion;
    private String ciudadSede;
    private ArrayList<String> nVendedores;
    private ArrayList<String> paises;
    private ArrayList<String> areaDeMercado;

    public String getCiudadSede() {
        return ciudadSede;
    }

    public Empresa() {
        this.nVendedores = new ArrayList<>();
    }

    public void setCiudadSede(String ciudadSede) {
        this.ciudadSede = ciudadSede;
    }

    public String encontrarVendedor(String nombreVendedor) {
        if (nVendedores.contains(nombreVendedor)) {
            return nombre;
        }
        return "";
    }

    public boolean encontrar(String datos) {
        return datos.equals(nombre);
    }

    public ArrayList<String> captacionVendedor(ArrayList<String> valor) {
        boolean masVendedores;
        boolean vacio;
        do {
            vacio = true;
            for (int i = 0; i < nVendedores.size(); i++) {
                if (!valor.contains(nVendedores.get(i))) {
                    EntradaSalida.mostrarString(i + 1 + "_" + nVendedores.get(i) + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masVendedores = false;
                EntradaSalida.mostrarString("No hay mas vendedores disponibles en el sistema");
                return valor;
            } else {
                String nombreVendedor = EntradaSalida.leerString("Elija un vendedor, ingresando el nombre: ");
                boolean opcValida = false;
                if (nVendedores.contains(nombreVendedor)) {
                    opcValida = true;
                }
                if (!opcValida || valor.contains(nombreVendedor)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    valor.add(nombreVendedor);
                }
            }
            masVendedores = EntradaSalida.leerBoolean("Desea captar mas vendedores?");
        } while (masVendedores);
        return valor;
    }

    public void mostrar() {
        EntradaSalida.mostrarString("Curso - Nombre: " + nombre);
        EntradaSalida.mostrarString("Fecha de entrada en el holding: " + fechaEntrada);
        EntradaSalida.mostrarString("Facturacion anual: " + facturacion);
        if (nVendedores == null) {
            EntradaSalida.mostrarString("La empresa no tiene vendedores");
        } else {
            EntradaSalida.mostrarString("Numero de vendedores: " + nVendedores.size());
            for (int i = 0; i < nVendedores.size(); i++) {
                System.out.println(i + "-" + nVendedores.get(i));
            }
        }
        EntradaSalida.mostrarString("=============================================");

        EntradaSalida.mostrarString("Numero de paises: " + paises.size());
        for (int i = 0; i < paises.size(); i++) {
            System.out.println(i + "-" + paises.get(i));
        }

        EntradaSalida.mostrarString("=============================================");

        System.out.println("Numero de areas de mercado: " + areaDeMercado.size());
        for (int i = 0; i < areaDeMercado.size(); i++) {
            System.out.println(i + "-" + areaDeMercado.get(i));
        }

        EntradaSalida.mostrarString("----------------------------------------------");

    }

    public ArrayList<String> mostrarNombreArea(ArrayList<AreaMercado> area) {
        ArrayList<String> nombreArea = new ArrayList<String>();
        for (int i = 0; i < area.size(); i++) {
            nombreArea.add(area.get(1).getNombre());
        }
        return nombreArea;
    }

    public boolean encontrarNombre(String datos) {
        return datos.equals(nombre);
    }

    public void agregarArea(String datos) {
        areaDeMercado.add(datos);
    }

    public void agregarPais(String datos) {
        paises.add(datos);
    }

    public void agregarVendedor(String datos) {
        if(nVendedores == null){
            nVendedores = new ArrayList<>();
        }
        nVendedores.add(datos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(String facturacion) {
        this.facturacion = facturacion;
    }

    public ArrayList<String> getnVendedores() {
        return nVendedores;
    }

    public void setnVendedores(ArrayList<String> nVendedores) {
        this.nVendedores = nVendedores;
    }

    public ArrayList<String> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<String> paises) {
        this.paises = paises;
    }

    public ArrayList<String> getAreaDeMercado() {
        return areaDeMercado;
    }

    public void setAreaDeMercado(ArrayList<String> areaDeMercado) {
        this.areaDeMercado = areaDeMercado;
    }


}
