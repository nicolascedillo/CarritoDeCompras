package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntaRespondida;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.login.LogInView;
import ec.edu.ups.vista.login.RecuperarContraseniaView;
import ec.edu.ups.vista.login.RegistraseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PreguntaController {
    private final UsuarioDAO usuarioDAO;
    private final LogInView logInView;
    private final RegistraseView registraseView;
    private final PreguntaDAO preguntaDAO;
    private final RecuperarContraseniaView recuperarContraseniaView;
    private int contadorPreguntas;
    private MensajeInternacionalizacionHandler mIH;

    public PreguntaController(UsuarioDAO usuarioDAO, PreguntaDAO preguntaDAO, LogInView logInView,
                              RegistraseView registraseView, MensajeInternacionalizacionHandler mIH, RecuperarContraseniaView recuperarContraseniaView) {
        this.usuarioDAO = usuarioDAO;
        this.mIH = mIH;
        this.logInView = logInView;
        this.registraseView = registraseView;
        this.preguntaDAO = preguntaDAO;
        this.recuperarContraseniaView = recuperarContraseniaView;
        this.contadorPreguntas = 1;

        configurarEventosEnVista();
    }

    private void configurarEventosEnVista(){

        logInView.getRegistrarseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarse();
            }
        });

        logInView.getOlvidadaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logInView.getUsernameTextField().getText().isEmpty()){
                    recuperarContraseniaView.mostrarMensaje(mIH.get("recuperacion.usuario.vacio"));
                    return;
                }
                if(usuarioDAO.buscarPorUsername(logInView.getUsernameTextField().getText()) == null){
                    recuperarContraseniaView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                    return;
                }
                if (usuarioDAO.buscarPorUsername(logInView.getUsernameTextField().getText()).getPreguntasVerificacion().isEmpty()) {
                    recuperarContraseniaView.mostrarMensaje(mIH.get("recuperacion.usuario.sin.preguntas"));
                    return;
                }
                logInView.setVisible(false);
                configurarEventosEnOlvidada();
                recuperarContraseniaView.setVisible(true);

            }
        });
    }

    private void registrarse() {
        logInView.setVisible(false);
        configurarEventosEnRegistrarse();
        registraseView.setVisible(true);
    }

    private void configurarEventosEnRegistrarse() {
        registraseView.getUsuarioTextField().setText("");
        registraseView.getPasswordField1().setText("");
        final int[] contadorPreguntasRespondidas = {0};
        List<PreguntaRespondida> preguntasRespondidas = new ArrayList<>();
        cargarPregunta(contadorPreguntas);

        quitarActionListeners(registraseView.getGuardarButton());
        quitarActionListeners(registraseView.getSiguienteButton());

        registraseView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(preguntasRespondidas.size() < 3){
                    registraseView.mostrarMensaje(mIH.get("registro.preguntas.requeridas"));
                    return;
                }
                if (registraseView.getUsuarioTextField().getText().isEmpty() ||
                        registraseView.getPasswordField1().getText().isEmpty() ||
                        registraseView.getNombreTextField().getText().isEmpty() ||
                        registraseView.getTelefonoTextField().getText().isEmpty() ||
                        registraseView.getCorreoTextField().getText().isEmpty() ||
                        registraseView.getAnioTextField().getText().isEmpty() ||
                        registraseView.getDiaComboBox().getSelectedItem() == null ||
                        registraseView.getMesComboBox().getSelectedItem() == null ) {
                    registraseView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }
                if (usuarioDAO.buscarPorUsername(registraseView.getUsuarioTextField().getText()) != null) {
                    registraseView.mostrarMensaje(mIH.get("mensaje.usuario.existe"));
                    return;
                }
                String username = registraseView.getUsuarioTextField().getText();
                String password = registraseView.getPasswordField1().getText();
                String nombre = registraseView.getNombreTextField().getText();
                String telefono = registraseView.getTelefonoTextField().getText();
                String correo = registraseView.getCorreoTextField().getText();
                GregorianCalendar fecha = new GregorianCalendar();
                int dia =(Integer) registraseView.getDiaComboBox().getSelectedItem();
                int mes = -1;
                String mesSeleccionado = (String) registraseView.getMesComboBox().getSelectedItem();
                if (mesSeleccionado.equals(mIH.get("mes.enero"))) {
                    mes = 0;
                } else if (mesSeleccionado.equals(mIH.get("mes.febrero"))) {
                    mes = 1;
                } else if (mesSeleccionado.equals(mIH.get("mes.marzo"))) {
                    mes = 2;
                } else if (mesSeleccionado.equals(mIH.get("mes.abril"))) {
                    mes = 3;
                } else if (mesSeleccionado.equals(mIH.get("mes.mayo"))) {
                    mes = 4;
                } else if (mesSeleccionado.equals(mIH.get("mes.junio"))) {
                    mes = 5;
                } else if (mesSeleccionado.equals(mIH.get("mes.julio"))) {
                    mes = 6;
                } else if (mesSeleccionado.equals(mIH.get("mes.agosto"))) {
                    mes = 7;
                } else if (mesSeleccionado.equals(mIH.get("mes.septiembre"))) {
                    mes = 8;
                } else if (mesSeleccionado.equals(mIH.get("mes.octubre"))) {
                    mes = 9;
                } else if (mesSeleccionado.equals(mIH.get("mes.noviembre"))) {
                    mes = 10;
                } else if (mesSeleccionado.equals(mIH.get("mes.diciembre"))) {
                    mes = 11;
                } else {
                    registraseView.mostrarMensaje(mIH.get("mes.invalido"));
                    return;
                }
                int anio = Integer.parseInt(registraseView.getAnioTextField().getText()) ;
                fecha.set(anio, mes, dia);

                registraseView.limpiarCampos();

                Usuario usuario1 = new Usuario(username, password, Rol.USUARIO,
                        nombre, telefono, correo, fecha);
                usuario1.setPreguntasVerificacion(preguntasRespondidas);
                usuarioDAO.crear(usuario1);
                registraseView.mostrarMensaje(mIH.get("mensaje.usuario.creado"));
                logInView.setVisible(true);
                registraseView.dispose();
                contadorPreguntas = 1;
                contadorPreguntasRespondidas[0] = 0;
            }
        });

        registraseView.getSiguienteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(contadorPreguntas > 12){
                    return;
                }
                if(!registraseView.getRespuestaTextField().getText().isEmpty()){
                    contadorPreguntasRespondidas[0]++;
                    PreguntaRespondida preguntaRespondida = new PreguntaRespondida(preguntaDAO.buscarPorCodigo(contadorPreguntas), registraseView.getRespuestaTextField().getText());
                    preguntasRespondidas.add(preguntaRespondida);
                }
                contadorPreguntas++;
                cargarPregunta(contadorPreguntas);
                registraseView.getRespuestaTextField().setText("");

            }
        });
    }

    private void cargarPregunta(int codigo){
        registraseView.getLblPreguntaCodigo().setText(mIH.get("registro.numero.pregunta") + " " + codigo);
        registraseView.getLblEnunciado().setText(mIH.get(preguntaDAO.buscarPorCodigo(codigo).getEnunciado()));
    }

    private void configurarEventosEnOlvidada() {
        Usuario usuarioRecuperacion = usuarioDAO.buscarPorUsername(logInView.getUsernameTextField().getText());
        List<PreguntaRespondida> preguntas = usuarioRecuperacion.getPreguntasVerificacion();
        randomizarListaPreguntaRespondida(preguntas);
        List<Integer> codigos = new ArrayList<>();
        List<String> respuestas = new ArrayList<>();
        for (PreguntaRespondida preguntaRespondida : preguntas) {
            codigos.add(preguntaRespondida.getPregunta().getCodigo());
            respuestas.add(preguntaRespondida.getRespuesta());
        }
        final int[] iteradorCodigo = {0};
        final int[] correctas = {0};
        cargarPreguntaOlvidada(codigos.get(iteradorCodigo[0]));

        quitarActionListeners(recuperarContraseniaView.getRestablecerButton());
        quitarActionListeners(recuperarContraseniaView.getSiguienteButton());

        recuperarContraseniaView.getSiguienteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if(recuperarContraseniaView.getRespuestaTextField().getText().toLowerCase().equals(respuestas.get(iteradorCodigo[0]).toLowerCase())){
                    recuperarContraseniaView.getSiguienteButton().setEnabled(false);
                    recuperarContraseniaView.getRestablecerButton().setEnabled(true);
                    recuperarContraseniaView.getLblEnunciado().setText("");
                    recuperarContraseniaView.getLblPreguntaCodigo().setText("");
                    recuperarContraseniaView.getRespuestaTextField().setText("");
                    recuperarContraseniaView.getLblTituloPreguntas().setText(mIH.get("recuperacion.nueva.contrasena"));
                    return;
                }

                if (recuperarContraseniaView.getRespuestaTextField().getText().isEmpty() ||
                        !recuperarContraseniaView.getRespuestaTextField().getText().toLowerCase().equals(respuestas.get(iteradorCodigo[0]).toLowerCase())) {
                    recuperarContraseniaView.mostrarMensaje(mIH.get("recuperacion.respuesta.erronea"));
                    return;
                }
                iteradorCodigo[0]++;
                correctas[0]++;
                recuperarContraseniaView.getRespuestaTextField().setText("");
//                cargarPreguntaOlvidada(codigos.get(iteradorCodigo[0]));

            }
        });

        recuperarContraseniaView.getRestablecerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (recuperarContraseniaView.getRespuestaTextField().getText().isEmpty()) {
                    recuperarContraseniaView.mostrarMensaje(mIH.get("recuperacion.nueva.contrasena"));
                    return;
                }

                String nuevaContrasenia = recuperarContraseniaView.getRespuestaTextField().getText();
                usuarioRecuperacion.setPassword(nuevaContrasenia);
                usuarioDAO.actualizar(usuarioRecuperacion);
                recuperarContraseniaView.mostrarMensaje(mIH.get("recuperacion.contrasena.actualizada"));
                recuperarContraseniaView.getRespuestaTextField().setText("");
                recuperarContraseniaView.getSiguienteButton().setEnabled(true);
                recuperarContraseniaView.getRestablecerButton().setEnabled(false);
                iteradorCodigo[0] = 0;
                correctas[0] = 0;
                logInView.setVisible(true);
                recuperarContraseniaView.dispose();
            }
        });
    }

    private void cargarPreguntaOlvidada(int codigo){
        recuperarContraseniaView.getLblPreguntaCodigo().setText(mIH.get("registro.numero.pregunta") + " " + codigo);
        recuperarContraseniaView.getLblEnunciado().setText(mIH.get(preguntaDAO.buscarPorCodigo(codigo).getEnunciado()));
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        registraseView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
        recuperarContraseniaView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
        cargarPregunta(contadorPreguntas);
    }

    private void randomizarListaPreguntaRespondida(List<PreguntaRespondida> lista) {
        List<PreguntaRespondida> listaRandomizada = new LinkedList<>();
        while (!lista.isEmpty()) {
            int randomIndex = (int) (Math.random() * lista.size());
            listaRandomizada.add(lista.remove(randomIndex));
        }
        lista.addAll(listaRandomizada);
    }

    private void quitarActionListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    public void configurarEventosUsuarioSinPregunta(Usuario usuarioSinPregguntas, MenuPrincipalView menuPrincipalView) {
        registraseView.getUsuarioTextField().setText(usuarioSinPregguntas.getUsername());
        registraseView.getPasswordField1().setText(usuarioSinPregguntas.getPassword());
        registraseView.getNombreTextField().setText(usuarioSinPregguntas.getNombreCompleto());
        registraseView.getTelefonoTextField().setText(usuarioSinPregguntas.getTelefono());
        registraseView.getCorreoTextField().setText(usuarioSinPregguntas.getEmail());
        registraseView.getAnioTextField().setText(String.valueOf(usuarioSinPregguntas.getFechaNacimiento().get(Calendar.YEAR)));
        registraseView.getDiaComboBox().setSelectedItem(usuarioSinPregguntas.getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
        int mesNumero = usuarioSinPregguntas.getFechaNacimiento().get(Calendar.MONTH);
        String[] mesesEspanol = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        registraseView.getMesComboBox().setSelectedItem(mIH.get("mes." + mesesEspanol[mesNumero]));
        registraseView.setTitle(mIH.get("registro.titulo.preguntas"));
        registraseView.getLblTitulo().setText(mIH.get("ventana.usuario.usuario"));
        registraseView.getUsuarioTextField().setEditable(false);
        registraseView.getPasswordField1().setEditable(false);
        registraseView.getNombreTextField().setEditable(false);
        registraseView.getTelefonoTextField().setEditable(false);
        registraseView.getCorreoTextField().setEditable(false);
        registraseView.getAnioTextField().setEditable(false);
        registraseView.getDiaComboBox().setEnabled(false);
        registraseView.getMesComboBox().setEnabled(false);

        final int[] contadorPreguntasRespondidas = {0};
        List<PreguntaRespondida> preguntasRespondidas = new ArrayList<>();
        cargarPregunta(contadorPreguntas);
        registraseView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        quitarActionListeners(registraseView.getGuardarButton());
        quitarActionListeners(registraseView.getSiguienteButton());

        registraseView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(preguntasRespondidas.size() < 3){
                    registraseView.mostrarMensaje(mIH.get("registro.preguntas.requeridas"));
                    return;
                }

                usuarioSinPregguntas.setPreguntasVerificacion(preguntasRespondidas);
                usuarioDAO.actualizar(usuarioSinPregguntas);
                registraseView.mostrarMensaje(mIH.get("preguntas.asignadas"));
                registraseView.dispose();
                contadorPreguntas = 1;
                contadorPreguntasRespondidas[0] = 0;
                menuPrincipalView.setVisible(true);
                menuPrincipalView.mostrarMensaje(mIH.get("app.bienvenida") + ": " + usuarioSinPregguntas.getUsername());
            }
        });

        registraseView.getSiguienteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(contadorPreguntas > 12){
                    return;
                }
                if(!registraseView.getRespuestaTextField().getText().isEmpty()){
                    contadorPreguntasRespondidas[0]++;
                    PreguntaRespondida preguntaRespondida = new PreguntaRespondida(preguntaDAO.buscarPorCodigo(contadorPreguntas), registraseView.getRespuestaTextField().getText());
                    preguntasRespondidas.add(preguntaRespondida);
                }
                contadorPreguntas++;
                cargarPregunta(contadorPreguntas);
                registraseView.getRespuestaTextField().setText("");

            }
        });
    }
}
