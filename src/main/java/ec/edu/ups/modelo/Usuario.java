package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private Rol rol;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private GregorianCalendar fechaNacimiento;
    private List<Carrito> carritos;
    private List<PreguntaRespondida> preguntasVerificacion;

    public Usuario() {
        this.carritos = new ArrayList<>();
        this.preguntasVerificacion = new ArrayList<>();
    }

    public Usuario(String username, String password, Rol rol, String nombreCompleto, String email, String telefono, GregorianCalendar fechaNacimiento) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        return fechaNacimiento.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
               (fechaNacimiento.get(GregorianCalendar.MONTH) + 1) + "/" +
               fechaNacimiento.get(GregorianCalendar.YEAR);
    }

    public String getDiaNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.DAY_OF_MONTH));
    }
    public String getMesNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.MONTH));
    }
    public String getAnioNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.YEAR));
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Usuario{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + dateFormat.format(fechaNacimiento.getTime()) +
                ", carritos=" + carritos +
                ", preguntasVerificacion=" + preguntasVerificacion +
                '}';
    }
}
