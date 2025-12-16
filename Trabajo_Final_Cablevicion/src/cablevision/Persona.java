package cablevision;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Persona implements Serializable {
    private String usuario;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public abstract boolean proceder(SistemaHolding sistemaHolding);

    public abstract void mostrar();
    
    public boolean encontrarUsrPsw(String datos) {
        return datos.equals(usuario + ":" + password);
    }
}
