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

    /**
     * Constructor que recibe los datos principales del usuario.
     * @param rol Rol del usuario.
     * @param nombreCompleto Nombre completo del usuario.
     * @param email Correo electrónico del usuario.
     * @param telefono Teléfono del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     */
    public Usuario(Rol rol, String nombreCompleto, String email, String telefono, GregorianCalendar fechaNacimiento) {
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.carritos = new ArrayList<>();
        this.preguntasVerificacion = new ArrayList<>();
    }

    /**
     * Constructor que recibe solo el username.
     * @param username Nombre de usuario.
     */
    public Usuario(String username) {
        this.username = username;
    }

    /**
     * Constructor que recibe todos los datos del usuario.
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @param rol Rol del usuario.
     * @param nombreCompleto Nombre completo.
     * @param email Correo electrónico.
     * @param telefono Teléfono.
     * @param fechaNacimiento Fecha de nacimiento.
     */
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

    /**
     * Obtiene el nombre de usuario.
     * @return Username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario, validando que sea una cédula válida.
     * @param username Nombre de usuario.
     * @throws CedulaValidationException Si la cédula no es válida.
     */
    public void setUsername(String username) throws CedulaValidationException {
        if (!esCedulaValida(username)) {
            throw new CedulaValidationException("La cédula ingresada no es válida.");
        }
        this.username = username;
    }

    /**
     * Valida si la cédula ingresada es válida.
     * @param cedula Cédula a validar.
     * @return true si es válida, false en caso contrario.
     */
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

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario, validando reglas de seguridad.
     * @param password Contraseña a asignar.
     * @throws PasswordException Si la contraseña no cumple las reglas.
     */
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

    /**
     * Obtiene el rol del usuario.
     * @return Rol del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     * @param rol Rol a asignar.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la lista de carritos del usuario.
     * @return Lista de carritos.
     */
    public List<Carrito> getCarritos() {
        return carritos;
    }

    /**
     * Agrega un carrito a la lista de carritos del usuario.
     * @param carrito Carrito a agregar.
     */
    public void addCarrito(Carrito carrito) {
        carritos.add(carrito);
    }

    /**
     * Obtiene la lista de preguntas de verificación respondidas.
     * @return Lista de preguntas respondidas.
     */
    public List<PreguntaRespondida> getPreguntasVerificacion() {
        return preguntasVerificacion;
    }

    /**
     * Establece la lista de preguntas de verificación respondidas.
     * @param preguntasVerificacion Lista de preguntas respondidas.
     */
    public void setPreguntasVerificacion(List<PreguntaRespondida> preguntasVerificacion) {
        this.preguntasVerificacion = preguntasVerificacion;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * @return Nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del usuario.
     * @param nombreCompleto Nombre completo a asignar.
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email Email a asignar.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el teléfono del usuario.
     * @return Teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del usuario.
     * @param telefono Teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     * @return Fecha de nacimiento como GregorianCalendar.
     */
    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene la fecha de nacimiento como String en formato dd/MM/yyyy.
     * @return Fecha de nacimiento como String.
     */
    public String getFechaNacimientoString() {
        return fechaNacimiento.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                (fechaNacimiento.get(GregorianCalendar.MONTH) + 1) + "/" +
                fechaNacimiento.get(GregorianCalendar.YEAR);
    }

    /**
     * Obtiene el día de nacimiento como String.
     * @return Día de nacimiento.
     */
    public String getDiaNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.DAY_OF_MONTH));
    }

    /**
     * Obtiene el mes de nacimiento como String.
     * @return Mes de nacimiento.
     */
    public String getMesNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.MONTH));
    }

    /**
     * Obtiene el año de nacimiento como String.
     * @return Año de nacimiento.
     */
    public String getAnioNacimientoString() {
        return String.valueOf(fechaNacimiento.get(GregorianCalendar.YEAR));
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     * @param fechaNacimiento Fecha de nacimiento a asignar.
     */
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna una representación en texto del usuario, incluyendo todos sus datos, carritos y preguntas.
     * @return String representando el usuario.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",")
                .append(password).append(",")
                .append(rol.name()).append(",")
                .append(nombreCompleto).append(",")
                .append(telefono).append(",")
                .append(email).append(",")
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
