package ec.edu.ups.modelo;

import ec.edu.ups.util.PasswordException;
import ec.edu.ups.util.CedulaValidationException;

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

    public Usuario(Rol rol, String nombreCompleto, String email, String telefono, GregorianCalendar fechaNacimiento) {
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.carritos = new ArrayList<>();
        this.preguntasVerificacion = new ArrayList<>();
    }

    public Usuario(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws CedulaValidationException {
        if (!esCedulaValida(username)) {
            throw new CedulaValidationException("La cédula ingresada no es válida.");
        }
        this.username = username;
    }

    private boolean esCedulaValida(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }
        int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
        if (tercerDigito >= 6) {
            return false;
        }
        int sumaPar = 0;
        int sumaImpar = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Integer.parseInt(cedula.substring(i, i + 1));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) digito -= 9;
                sumaImpar += digito;
            } else {
                sumaPar += digito;
            }
        }
        int total = sumaPar + sumaImpar;
        int decenaSuperior = ((total + 9) / 10) * 10;
        int digitoVerificador = decenaSuperior - total;
        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
        return digitoVerificador == Integer.parseInt(cedula.substring(9));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordException {
        char[] passwordChars = password.toCharArray();

        // Verificacion mayuscula
        boolean tieneMayuscula = false;
        for (Character c : passwordChars) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
                break;
            }
        }
        if (!tieneMayuscula) {
            throw new PasswordException("La contraseña debe contener al menos una letra mayúscula");
        }

        // Verificacion minuscula
        boolean tieneMinuscula = false;
        for (Character c : passwordChars) {
            if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
                break;
            }
        }
        if (!tieneMinuscula) {
            throw new PasswordException("La contraseña debe contener al menos una letra minúscula");
        }

        // Caracter especial
        boolean tieneCaracterEspecial = false;
        for (Character c : passwordChars) {
            if (c == '@' || c == '-' || c == '_' || c == '.') {
                tieneCaracterEspecial = true;
                break;
            }
        }
        if (!tieneCaracterEspecial) {
            throw new PasswordException("La contraseña debe contener al menos un carácter especial @, -, _, .");
        }

        // Verificacion longitud
        if (password.length() < 6) {
            throw new PasswordException("La contraseña debe tener al menos 6 caracteres");
        }

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
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",")
                .append(password).append(",")
                .append(rol.name()).append(",")
                .append(nombreCompleto).append(",")
                .append(email).append(",")
                .append(telefono).append(",")
                .append(fechaNacimiento.getTimeInMillis()).append(",");
        // carritos:
        if (carritos.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[");
            for (int i = 0; i < carritos.size(); i++) {
                sb.append(carritos.get(i).toString());
                if (i < carritos.size() - 1) sb.append(",");
            }
            sb.append("]");
        }
        sb.append(",");
        // preguntas:
        if (preguntasVerificacion.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[");
            for (int i = 0; i < preguntasVerificacion.size(); i++) {
                sb.append(preguntasVerificacion.get(i).toString());
                if (i < preguntasVerificacion.size() - 1) sb.append(",");
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
