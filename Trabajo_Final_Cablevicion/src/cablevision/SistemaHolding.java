package cablevision;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaHolding implements Serializable {

    private ArrayList<Empresa> empresas;
    private ArrayList<Persona> personas;
    private ArrayList<AreaMercado> areaMercado;
    private ArrayList<Pais> pais;
    private ArrayList<Vendedor> nombreVendedor;
    private ArrayList<Asesor> nombreAsesor;
    private ArrayList<String> valoresSubVendedor;

    public SistemaHolding() {
        empresas = new ArrayList<Empresa>();
        personas = new ArrayList<Persona>();
        areaMercado = new ArrayList<AreaMercado>();
        pais = new ArrayList<Pais>();
        nombreVendedor = new ArrayList<>();
        nombreAsesor = new ArrayList<>();
        valoresSubVendedor = new ArrayList<>();
    }

    public SistemaHolding deSerializar(String a) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(a);
        ObjectInputStream o = new ObjectInputStream(f);
        SistemaHolding s = (SistemaHolding) o.readObject();
        o.close();
        f.close();
        return s;
    }

    public void serializar(String a) throws IOException {
        FileOutputStream f = new FileOutputStream(a);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
    }

    public boolean verificacionEmpresaRepetido(String datos) {
        for (Empresa emp : empresas) {
            if (emp.encontrar(datos)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarPaisRepetido(String dato) {
        for (Pais p : pais) {
            if (p.encontrarDato(dato)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarAreaRepetido(String dato) {
        for (AreaMercado a : areaMercado) {
            if (a.encontrarDato(dato)) {
                return true;
            }
        }
        return false;

    }

    public boolean verificarAsesorRepetido(String dato) {
        for (Asesor a : nombreAsesor) {
            if (a.encontrar1(dato)) {
                return true;
            }
        }
        return false;

    }

    public boolean verificarVendedorRepetido(String dato) {
        valoresSubVendedor = null;
        for (Vendedor v : nombreVendedor) {
            if (v.encontrar(dato)) {
                valoresSubVendedor = v.getVendedores();
                return true;
            }
        }
        return false;

    }

    public Persona buscarUsuario(String datos) {
        for (Persona p : personas) {
            if (p.encontrarUsrPsw(datos)) {
                return p;
            }
        }
        return null;
    }

    public Empresa buscarEmpresa(String datos) {
        for (Empresa e : empresas) {
            if (e.encontrarNombre(datos)) {
                return e;
            }
        }
        return null;
    }

    public AreaMercado buscarArea(String datos) {
        for (AreaMercado a : areaMercado) {
            if (a.encontrarNombre(datos)) {
                return a;
            }
        }
        return null;
    }

    public Pais buscarPais(String datos) {
        for (Pais p : pais) {
            if (p.encontrarDato(datos)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public ArrayList<AreaMercado> getAreaMercado() {
        return areaMercado;
    }

    public ArrayList<Pais> getPais() {
        return pais;
    }

    public void ingresoEmpresa(String nombre, String seleccion) {
        boolean masEmpresas;
        boolean vacio = true;
        ArrayList<String> listaEmp = new ArrayList<String>();
        do {
            for (Empresa emp : empresas) {
                if (!listaEmp.contains(emp.getNombre())) {
                    EntradaSalida.mostrarString("*_" + emp.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masEmpresas = false;
                EntradaSalida.mostrarString("No hay mas empresas disponibles en el sistema");
            } else {
                String nombreEmpresa = EntradaSalida.leerString("Elija una empresa, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;
                if (verificacionEmpresaRepetido(nombreEmpresa)) {
                    opcValida = true;
                }
                if (!opcValida || listaEmp.contains(nombreEmpresa)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    listaEmp.add(nombreEmpresa);
                    Empresa e = buscarEmpresa(nombreEmpresa);
                    if (seleccion.equals("area")) {
                        e.agregarArea(nombre);
                    } else {
                        e.agregarPais(nombre);
                    }
                }
            }
            masEmpresas = EntradaSalida.leerBoolean("Desea ingresar mas Empresas?");
        } while (masEmpresas);

    }

    public ArrayList<String> ingresoVendAseEmpresa(String nombre, String seleccion) {
        boolean masEmpresas = true;
        boolean vacio = true;
        ArrayList<String> listaEmp = new ArrayList<String>();
        ArrayList<String> empresaTrabajadores = new ArrayList<String>();
        String nombreEmpresa;
        do {
            for (Empresa emp : empresas) {
                if (!listaEmp.contains(emp.getNombre())) {
                    EntradaSalida.mostrarString("*_" + emp.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masEmpresas = false;
                EntradaSalida.mostrarString("No hay mas empresas disponibles en el sistema");
            } else {
                nombreEmpresa = EntradaSalida.leerString("Elija una empresa, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;
                if (verificacionEmpresaRepetido(nombreEmpresa)) {
                    opcValida = true;
                }

                if (!opcValida || listaEmp.contains(nombreEmpresa)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    listaEmp.add(nombreEmpresa);
                    Empresa e = buscarEmpresa(nombreEmpresa);
                    if (seleccion.equals("vendedor")) {
                        empresaTrabajadores.add(nombreEmpresa);
                        e.agregarVendedor(nombre);
                    } else {
                        empresaTrabajadores.add(nombreEmpresa);
                    }
                }
            }
            if (seleccion.equals("vendedor")) {
                return empresaTrabajadores;
            } else {
                masEmpresas = EntradaSalida.leerBoolean("Desea ingresar mas Empresas?");
            }
        } while (masEmpresas);
        return empresaTrabajadores;
    }

    public ArrayList<String> ingresoAseArea() {
        boolean masAreas;
        boolean vacio = true;
        ArrayList<String> listaArea = new ArrayList<String>();
        ArrayList<String> areasTrabajadores = new ArrayList<String>();
        String nombreArea;
        do {
            for (AreaMercado area : areaMercado) {
                if (!listaArea.contains(area.getNombre())) {
                    EntradaSalida.mostrarString("*_" + area.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masAreas = false;
                EntradaSalida.mostrarString("No hay mas areas disponibles en el sistema");
            } else {
                nombreArea = EntradaSalida.leerString("Elija un area de mercado, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;
                if (verificarAreaRepetido(nombreArea)) {
                    opcValida = true;
                }

                if (!opcValida || listaArea.contains(nombreArea)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    listaArea.add(nombreArea);
                    areasTrabajadores.add(nombreArea);
                }
            }
            masAreas = EntradaSalida.leerBoolean("Desea ingresar mas areas?");

        } while (masAreas);
        return areasTrabajadores;
    }

    public ArrayList<String> ingresoAreaMercado(String nombre) {
        boolean masAreas;
        boolean vacio;
        ArrayList<String> listaAre = new ArrayList<>();
        do {
            vacio = true;
            for (AreaMercado are : areaMercado) {
                if (!listaAre.contains(are.getNombre())) {
                    EntradaSalida.mostrarString("*_" + are.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masAreas = false;
                EntradaSalida.mostrarString("No hay mas areas disponibles en el sistema");
                return listaAre;
            } else {
                String nombreArea = EntradaSalida.leerString("Elija un area, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;

                if (verificarAreaRepetido(nombreArea)) {
                    opcValida = true;
                }

                if (!opcValida || listaAre.contains(nombreArea)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                    masAreas = EntradaSalida.leerBoolean("Desea repetir el proceso?");
                } else {
                    listaAre.add(nombreArea);
                    masAreas = EntradaSalida.leerBoolean("Desea ingresar mas Areas?");
                }
            }

        } while (masAreas);
        return listaAre;
    }

    public ArrayList<String> ingresoAsesor(String nombre) {
        boolean masAsesores;
        boolean vacio = false;
        ArrayList<String> listaAse = new ArrayList<String>();
        do {
            vacio = true;
            for (AreaMercado are : areaMercado) {
                if (!listaAse.contains(are.getNombre())) {
                    EntradaSalida.mostrarString("*_" + are.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masAsesores = false;
                EntradaSalida.mostrarString("No hay mas asesores disponibles en el sistema");
                return listaAse;
            } else {
                String nombreAsesor = EntradaSalida.leerString("Elija un asesor, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;

                if (verificarAsesorRepetido(nombreAsesor)) {
                    opcValida = true;
                }

                if (!opcValida || listaAse.contains(nombreAsesor)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    listaAse.add(nombreAsesor);
                    Empresa e = buscarEmpresa(nombre);
                    e.agregarArea(nombreAsesor);
                }
            }
            masAsesores = EntradaSalida.leerBoolean("Desea ingresar mas Asesores?");
        } while (masAsesores);
        return listaAse;
    }

    public ArrayList<String> ingresoVendedor(String nombre) {
        boolean masVendedor;
        boolean vacio = false;
        ArrayList<String> listaVen = new ArrayList<String>();
        do {
            vacio = true;
            for (Vendedor are : nombreVendedor) {
                if (!listaVen.contains(are.devolcerUsuario())) {
                    EntradaSalida.mostrarString("*_" + are + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masVendedor = false;
                EntradaSalida.mostrarString("No hay mas vendedores disponibles en el sistema");
                return listaVen;
            } else {
                String nomVendedor = EntradaSalida.leerString("Elija un vendedores, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;

                if (verificarVendedorRepetido(nomVendedor)) {
                    opcValida = true;
                }

                if (!opcValida || listaVen.contains(nomVendedor)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    listaVen.add(nomVendedor);
                }//despues fijarse si es necesario poner el agregar
            }
            masVendedor = EntradaSalida.leerBoolean("Desea ingresar mas vendedores?");
        } while (masVendedor);
        return listaVen;
    }

    public ArrayList<String> ingresoPais(String nombre) {
        boolean masPaises;
        boolean vacio = true;
        ArrayList<String> listaPais = new ArrayList<String>();
        do {
            vacio = true;
            for (Pais p : pais) {
                if (!listaPais.contains(p.getNombre())) {
                    EntradaSalida.mostrarString("*_" + p.getNombre() + "\n");
                    vacio = false;
                }
            }
            if (vacio) {
                masPaises = false;
                EntradaSalida.mostrarString("No hay mas paises disponibles en el sistema");
                return listaPais;
            } else {
                String nombrePais = EntradaSalida.leerString("Elija un pais, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
                boolean opcValida = false;
                if (verificarPaisRepetido(nombrePais)) {
                    opcValida = true;
                }
                if (!opcValida || listaPais.contains(nombrePais)) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                    masPaises = EntradaSalida.leerBoolean("Desea repetir el proceso?");
                } else {
                    listaPais.add(nombrePais);
                    masPaises = EntradaSalida.leerBoolean("Desea ingresar mas paises?");
                }
            }
        } while (masPaises);
        return listaPais;
    }

    public String ingresoSedePaisEmpresa(String nombreEmpresa) {
        boolean comfirmacionSede, errorNombre = true;
        do {
            for (Pais p : pais) {
                EntradaSalida.mostrarString("*_" + p.getNombre() + "\n");
            }
            String nombrePais = EntradaSalida.leerString("Elija un pais para ingresar su sede, ingresando el nombre (Por favor ingresar el nombre como se muestra en las opciones): ");
            if (verificarPaisRepetido(nombrePais)) {
                comfirmacionSede = EntradaSalida.leerBoolean("Desea ingresar en el pais " + nombrePais + " su sede?");
                if (!comfirmacionSede) {
                    EntradaSalida.mostrarString("Se procede con la finalizacion del ingreso de la sede");
                } else {
                    Pais p = buscarPais(nombrePais);
                    if (!p.isActividadPais()) {
                        String nombreCiudad = EntradaSalida.leerString("Ingrese una ciudad del pais donde se va ponoer la sede: ");
                        p.setActividadPais(true);
                        errorNombre = false;
                        return nombreCiudad;
                    } else {
                        EntradaSalida.mostrarString("Error: en este pais ya tiene una actividad en curso, se procede con la reanudacion del sistema");
                    }
                }
            } else {
                EntradaSalida.mostrarString("ERROR: no se encuentra el pais");
            }
        } while (errorNombre);
        return null;
    }

    public ArrayList<Vendedor> getNombreVendedor() {
        return nombreVendedor;
    }

    public ArrayList<Asesor> getNombreAsesor() {
        return nombreAsesor;
    }

    public void agregarVendedor(Vendedor datos) {
        if (nombreVendedor == null) {
            nombreVendedor = new ArrayList<>();
        }
        nombreVendedor.add(datos);
    }

    public void agregarAsesor(Asesor datos) {
        if (nombreAsesor == null) {
            nombreAsesor = new ArrayList<>();
        }
        nombreAsesor.add(datos);
    }

    public void econtrarValorVendedor(String dato, ArrayList<String> valores) {
        for (int i = 0; i < nombreVendedor.size(); i++) {
            if (nombreVendedor.get(i).equals(dato)) {
                nombreVendedor.get(i).getVendedores().addAll(valores);
            }
        }
    }

    public void CaptarVendedor(String dato) {
        ArrayList<String> valores = new ArrayList<>();
        verificarVendedorRepetido(dato);
        valoresSubVendedor.add(dato);
        for (Empresa emp : empresas) {
            if (emp.getnVendedores().contains(dato)) {
                valores = emp.captacionVendedor(valoresSubVendedor);
            }
        }
        econtrarValorVendedor(dato, valores);
    }

    public void agregarPaisPredeterminado() {
        Pais primerPais = new Pais("Argentina", "2.123.000", "10", "Buenos Aires");
        Pais segundaPais = new Pais("Estados Unidos", "123.000.000", "15", "EE UU");
        Pais terceroPais = new Pais("Brasil", "1.123.000", "20", "Brasilia");
        pais.add(primerPais);
        pais.add(segundaPais);
        pais.add(terceroPais);
    }

    public void agregarAreaPredeterminado() {
        AreaMercado primerArea = new AreaMercado("Economia", "esta es la area de Economia");
        AreaMercado segundaArea = new AreaMercado("Politica", "esta es la area de Politica");
        AreaMercado terceroArea = new AreaMercado("Financiera", "esta es la area de Financiera");
        areaMercado.add(primerArea);
        areaMercado.add(segundaArea);
        areaMercado.add(terceroArea);
    }

    public boolean verificarCodigoRepetidoAsesor(String dato) {
        for (Asesor a : nombreAsesor) {
            if (a.encontrarCodigo(dato)) {
                return false;
            }
        }
        return true;
    }

    public boolean verificarCodigoRepetidoVendedor(String dato) {
        for (Vendedor v : nombreVendedor) {
            if (v.encontrarCodigoVendedor(dato)) {
                return false;
            }
        }
        return true;
    }

}
