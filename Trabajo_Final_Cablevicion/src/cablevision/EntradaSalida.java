package cablevision;

import javax.swing.JPasswordField;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EntradaSalida {

    public static char leerChar(String texto) {
        Scanner sc = new Scanner(System.in);
        System.out.println(texto);
        String st = sc.nextLine();
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
    }

    public static String leerTiempo() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String texto = ahora.format(formato);
        return texto;
    }

    public static String leerString(String texto) {
        System.out.println(texto);
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return (st == null ? "" : st);
    }

    public static boolean leerBoolean(String texto) {
        boolean valor = true;
        Scanner sc = new Scanner(System.in);
        System.out.println(texto + "\n(S=si / N=no)");
        String st = sc.nextLine();
        if (st.equals("S") || st.equals("s")) {
            return valor;
        } else {
            return valor = false;
        }
    }

    public static void mostrarString(String s) {
        System.out.println(s);
    }

    public static void pausaSistema() throws IOException {
        System.out.println("Presione Enter para continuar...");
        System.in.read();
    }

    public static String leerPassword(String texto) {
        final JPasswordField pwd = new JPasswordField();
        ActionListener al = new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                pwd.requestFocusInWindow();
            }
        };
        Timer timer = new Timer(200, al);
        timer.setRepeats(false);
        timer.start();
        String password = "";
        System.out.println(texto);
        Scanner sc = new Scanner(System.in);
        password = sc.nextLine();
        return password;
    }
}