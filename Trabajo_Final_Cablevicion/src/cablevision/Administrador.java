package cablevision;
//****************************

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Administrador extends Persona implements Serializable {

    public Administrador(String usr, String psw) {
        setUsuario(usr);
        setPassword(psw);
    }

    @Override
    public void mostrar() {
        EntradaSalida.mostrarString("Administrador - Usuario: " + this.getUsuario());
        EntradaSalida.mostrarString("Password: " + this.getPassword());
        EntradaSalida.mostrarString("=============================================");
    }

    @Override
    public boolean proceder(SistemaHolding sistemaHolding) {
        char opc;
        boolean seguir = true;
        do {
            opc = EntradaSalida.leerChar(
                    "OPCIONES DEL ADMINISTRADOR(a)\n"
                    + "[1] Ingresar empresa\n"
                    + "[2] Ingresar una nueva area de mercado o un nuevo pais\n"
                    + "[3] Ingresar vendedor(a) o asesor(a) a una empresa\n"//hacer una punto donde podes elegir si es area o pais
                    + "[4] Captacion de un nuevo vendedor\n"
                    + "[5] Mostrar el contenido del sistema\n"
                    + "[6] Salir de este menu\n"
                    + "[7] Salir del sistema");

            switch (opc) {
                case '1':
                    crearEmpresa(sistemaHolding);
                    break;

                case '2':
                    crearPaisOrArea(sistemaHolding);
                    break;

                case '3':
                    crearVendedorOrAsesor(sistemaHolding);
                    break;

                case '4':
                    captarOtroVendedor(sistemaHolding);
                    break;

                case '5':
                    mostarContenidoSistema(sistemaHolding);
                    break;

                case '6':
                    seguir = true;
                    break;

                case '7':
                    seguir = false;
                    break;
                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida");
                    opc = '*';

            }

            if (opc >= '1' && opc <= '4') {
                try {
                    sistemaHolding.serializar("cablevicion.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (opc != '6' && opc != '7');

        return seguir;
    }

    public void crearPaisOrArea(SistemaHolding sistemaHolding) {
        char opc;
        boolean seguir = true;

        do {
            opc = EntradaSalida.leerChar(
                    "OPCIONES DEL ADMINISTRADOR(a)\n"
                    + "[1] Ingresar una nueva area de mercado\n"
                    + "[2] ngresar un nuevo pais\n"
                    + "[3] Salir del sistema");

            switch (opc) {
                case '1':
                    crearAreaEmpresa(sistemaHolding);
                    break;

                case '2':
                    crearPais(sistemaHolding);
                    break;

                case '3':
                    EntradaSalida.mostrarString("Se esta saliendo del programa");
                    break;

                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida");
                    opc = '*';

            }
        } while (opc != '3');
    }

    public void crearVendedorOrAsesor(SistemaHolding sistemaHolding) {
        char opc;
        boolean seguir = true;

        do {
            opc = EntradaSalida.leerChar(
                    "OPCIONES DEL ADMINISTRADOR(a)\n"
                    + "[1] ngresar un nuevo vendedor\n"
                    + "[2] Ingresar una nuevo asesor\n"
                    + "[3] Salir del sistema");

            switch (opc) {
                case '1':
                    ingresarVendedor(sistemaHolding);
                    break;

                case '2':
                    ingresarAsesor(sistemaHolding);
                    break;

                case '3':
                    EntradaSalida.mostrarString("Se esta saliendo del programa");
                    break;

                default:
                    EntradaSalida.mostrarString("ERROR: Opcion invalida");
                    opc = '*';

            }
        } while (opc != '3');
    }

    public void crearEmpresa(SistemaHolding sistemaHolding) {
        Empresa empresa = new Empresa();
        String nombre = EntradaSalida.leerString("INGRESO DE NUEVA EMPRESA\nPor favor,Ingrese nombre de la empresa:");
        if (nombre.equals("")) {
            EntradaSalida.mostrarString("ERROR: nombre nulo no se permite");
        } else {
            if (sistemaHolding.verificacionEmpresaRepetido(nombre)) {
                EntradaSalida.mostrarString("ERROR: El nombre de la empresa ya exite");
            } else {
                String factura
                        = EntradaSalida.leerString("Ingrese la facturacion anual de la empresa " + nombre + ":");
                if (factura.equals("")) {
                    EntradaSalida.mostrarString("ERROR: no puede tener valor nulo");
                } else {
                    ArrayList<Pais> listadoPais = sistemaHolding.getPais();
                    ArrayList<AreaMercado> listadoAreaMercado = sistemaHolding.getAreaMercado();
                    if (listadoAreaMercado.isEmpty()) {
                        EntradaSalida.mostrarString("ERROR: no hay areas de mercado, se procede a ingresar los paises predeterminados");
                        sistemaHolding.agregarAreaPredeterminado();
                        empresa.setAreaDeMercado(sistemaHolding.ingresoAreaMercado(nombre));
                    } else {
                        empresa.setAreaDeMercado(sistemaHolding.ingresoAreaMercado(nombre));
                    }

                    if (listadoPais.isEmpty()) {
                        EntradaSalida.mostrarString("No hay paises, por lo tanto se procede a crear un nuevo pais");
                        sistemaHolding.agregarPaisPredeterminado();

                        empresa.setPaises(sistemaHolding.ingresoPais(nombre));
                        EntradaSalida.mostrarString("Se procede con el ingreso de la sede");
                        empresa.setCiudadSede(sistemaHolding.ingresoSedePaisEmpresa(nombre));
                    } else {
                        empresa.setPaises(sistemaHolding.ingresoPais(nombre));
                        empresa.setCiudadSede(sistemaHolding.ingresoSedePaisEmpresa(nombre));
                    }
                    String fecha = EntradaSalida.leerTiempo();
                    empresa.setNombre(nombre);
                    empresa.setFacturacion(factura);
                    empresa.setFechaEntrada(fecha);
                    sistemaHolding.getEmpresas().add(empresa);
                    EntradaSalida.mostrarString("Se ha incorporado la empresa al sistema correctamente");
                }

            }
        }

    }

    public void crearAreaEmpresa(SistemaHolding sistemaHolding) {
        ArrayList<Empresa> ListadoEmpresa = sistemaHolding.getEmpresas();
        String nomArea = EntradaSalida.leerString("Ingrese el nombre de la area");
        if (sistemaHolding.verificarAreaRepetido(nomArea) || nomArea.equals("")) {
            EntradaSalida.mostrarString("ERROR: nombre no permitido");
        } else {
            String Descrip = EntradaSalida.leerString("Ingrese la descripcion del area");
            if (Descrip.equals("")) {
                EntradaSalida.mostrarString("ERROR: descripcion no permitido");
            } else {
                AreaMercado area = new AreaMercado(nomArea, Descrip);
                sistemaHolding.getAreaMercado().add(area);
                EntradaSalida.mostrarString("Se ha incorporado el area de mercado al sistema correctamente");
            }
        }
    }

    public void crearPais(SistemaHolding sistemaHolding) {
        String nomPais = EntradaSalida.leerString("Ingrese el nombre del pais");
        if (sistemaHolding.verificarPaisRepetido(nomPais) || nomPais.equals("")) {
            EntradaSalida.mostrarString("ERROR: valor no permitido");
        } else {
            String PIB = EntradaSalida.leerString("PIB del paisa:");
            if (PIB.equals("")) {
                EntradaSalida.mostrarString("ERROR: nombre no valido");
            } else {
                String NumHabitantes = EntradaSalida.leerString("Ingrese el numero de habitantes:");
                if (NumHabitantes.equals("")) {
                    EntradaSalida.mostrarString("ERROR: nombre no valido");
                } else {
                    String Capital = EntradaSalida.leerString("Ingrese la capital:");
                    if (Capital.equals("")) {
                        EntradaSalida.mostrarString("ERROR: nombre no valido");
                    } else {
                        Pais pais = new Pais(nomPais, PIB, NumHabitantes, Capital);
                        sistemaHolding.getPais().add(pais);
                        EntradaSalida.mostrarString("Se ha incorporado el pais al sistema correctamente");
                    }
                }
            }
        }

    }

    public void ingresarVendedor(SistemaHolding sistemaHolding) {
        ArrayList<Empresa> ListadoEmpresa = sistemaHolding.getEmpresas();
        ArrayList<String> subVendedores = new ArrayList<>();
        //ArrayList<String> nombreEmpresa = new ArrayList<>();
        if (ListadoEmpresa.isEmpty()) {
            EntradaSalida.mostrarString("ERROR: Primero debio haberse ingresado una empresa");
        } else {
            String codigo = EntradaSalida.leerString("ALTA DE VENDEDOR\nIngrese el codigo del vendedor:");
            if(sistemaHolding.verificarCodigoRepetidoVendedor(codigo)){
            String usVend = EntradaSalida.leerString("Ingrese el vendedor:");
            String paVend = EntradaSalida.leerPassword("Ingrese la password:");
            Persona p = sistemaHolding.buscarUsuario(usVend + ":" + paVend);
            if (p != null || usVend.equals("") || paVend.equals("") || codigo.equals("")) {
                EntradaSalida.mostrarString("ERROR: El usuario no se pudo registrar");
            } else {
                String direc = EntradaSalida.leerPassword("Ingrese la direccion");
                if (direc.equals("")) {
                    EntradaSalida.mostrarString("ERROR: direccion no valida");
                } else {
                    String trabajador = sistemaHolding.ingresoVendAseEmpresa(usVend, "vendedor").get(0);
                    p = new Vendedor(trabajador, codigo, direc, usVend, paVend, subVendedores);
                    sistemaHolding.agregarVendedor((Vendedor) p);
                    sistemaHolding.getPersonas().add(p);
                    EntradaSalida.mostrarString("Se ha incorporado al vendedor al sistema correctamente");

                }

            }
        }else{
                EntradaSalida.mostrarString("No se ha incorporado al vendedor al sistema, por un proble del codigo");
            }
        }

    }

    public void ingresarAsesor(SistemaHolding sistemaHolding) {
        ArrayList<Empresa> ListadoEmpresa = sistemaHolding.getEmpresas();
        if (ListadoEmpresa.isEmpty()) {
            EntradaSalida.mostrarString("ERROR: Primero debio haberse ingresado una empresa");
        } else {
            String codigo = EntradaSalida.leerString("ALTA DE ASESOR\nIngrese el codigo de usuario: ");
            if (sistemaHolding.verificarCodigoRepetidoAsesor(codigo)) {
                String usAse = EntradaSalida.leerString("Ingrese el usuario: ");
                String paAse = EntradaSalida.leerPassword("Ingrese la password: ");
                Persona p = sistemaHolding.buscarUsuario(usAse + ":" + paAse);
                if (p != null || paAse.equals("") || usAse.equals("") || codigo.equals("")) {
                    EntradaSalida.mostrarString("ERROR: El usuario ya figura en el sistema");
                } else {
                    String direc = EntradaSalida.leerPassword("Ingrese la direccion: ");
                    if (direc.equals("")) {
                        EntradaSalida.mostrarString("ERROR: password no valida");
                    } else {
                        String titul = EntradaSalida.leerPassword("Ingrese la titulacion del asesor: ");
                        if (titul.equals("")) {
                            EntradaSalida.mostrarString("ERROR: password no valida");
                        } else {
                            String fecha = EntradaSalida.leerTiempo();
                            p = new Asesor(codigo, direc, titul, sistemaHolding.ingresoVendAseEmpresa(usAse, "asesor"), sistemaHolding.ingresoAseArea(), usAse, paAse, fecha);
                            sistemaHolding.agregarAsesor((Asesor) p);
                            sistemaHolding.getPersonas().add(p);
                            EntradaSalida.mostrarString("Se ha incorporado el asesor al sistema correctamente");
                        }
                    }
                }
            } else {
                EntradaSalida.mostrarString("Se ha incorporado el asesor al sistema correctamente, por codigo repetido");
            }
        }
    }

    public void mostarContenidoSistema(SistemaHolding sistemaHolding) {
        //System.out.println("\n=============================================");
        System.out.println("=============TRABAJADORES===============");
        ArrayList<Persona> vecPer = sistemaHolding.getPersonas();
        for (int i = 0; i < vecPer.size(); i++) {
            vecPer.get(i).mostrar();
        }

        System.out.println("\n=============EMPRESAS===============");
        ArrayList<Empresa> vecEmp = sistemaHolding.getEmpresas();
        if (vecEmp.isEmpty()) {
            System.out.println("No hay empresas registrados en el sistema.");
        } else {
            for (int i = 0; i < vecEmp.size(); i++) {
                vecEmp.get(i).mostrar();
            }
        }

        System.out.println("\n=============PAIS===============");
        ArrayList<Pais> vecPais = sistemaHolding.getPais();
        if (vecPais.isEmpty()) {
            System.out.println("No hay paises registrados en el sistema.");
        } else {
            for (int i = 0; i < vecPais.size(); i++) {
                vecPais.get(i).mostrar();
            }
        }

        System.out.println("\n=============AREA DE MERCADO===============");
        ArrayList<AreaMercado> vecArea = sistemaHolding.getAreaMercado();
        if (vecArea.isEmpty()) {
            System.out.println("No hay areas registrados en el sistema.");
        } else {
            for (int i = 0; i < vecArea.size(); i++) {
                vecArea.get(i).mostrar();
            }
        }
    }

    public void captarOtroVendedor(SistemaHolding sistemaHolding) {
        String usAspi = EntradaSalida.leerString("Ingrese el nombre del super vendedor:");
        if (usAspi.equals("")) {
            EntradaSalida.mostrarString("ERROR: usuario no valido");
        } else {
            sistemaHolding.CaptarVendedor(usAspi);

        }
    }

}
