package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private Rol rol;
    private List<Carrito> carritos;
    private List<PreguntaRespondida> preguntasVerificacion;

    public Usuario() {
        this.carritos = new ArrayList<>();
        this.preguntasVerificacion = new ArrayList<>();
    }

    public Usuario(String username, String password, Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.carritos = new ArrayList<>();
        this.preguntasVerificacion = new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void addCarrito(Carrito carrito) {
        carritos.add(carrito);
    }

    public List<PreguntaRespondida> getPreguntasVerificacion() {
        return preguntasVerificacion;
    }

    public void setPreguntasVerificacion(List<PreguntaRespondida> preguntasVerificacion) {
        this.preguntasVerificacion = preguntasVerificacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "rol=" + rol +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", preguntasVerificacion=" + preguntasVerificacion +
                '}';
    }
}
