package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.PreguntaRespondida;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.login.LogInView;
import ec.edu.ups.vista.login.RegistraseView;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LogInController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LogInView logInView;
    private final RegistraseView registraseView;
    private final PreguntaDAO preguntaDAO;
    private MensajeInternacionalizacionHandler mIH;

    public LogInController(UsuarioDAO usuarioDAO,PreguntaDAO preguntaDAO, LogInView logInView,
                           RegistraseView registraseView, MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.mIH = mIH;
        this.logInView = logInView;
        this.registraseView = registraseView;
        this.usuario = null;
        this.preguntaDAO = preguntaDAO;

        configurarEventosEnVista();
    }

    private void configurarEventosEnVista(){
        logInView.getIniciarSesionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                autenticar();
                limpiarCamposLogIn();
            }
        });

        logInView.getRegistrarseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                registrarse();
            }
        });
    }


    private void autenticar(){
        String username = logInView.getUsernameTextField().getText();
        String password = logInView.getContrasenaPasswordField().getText();

        usuario = usuarioDAO.autenticar(username, password);
        if(usuario == null){
            logInView.mostrarMensaje(mIH.get("mensaje.login.incorrecto"));
        } else {
            logInView.dispose();
        }
    }

    private void registrarse() {
        logInView.setVisible(false);
        configurarEventosEnRegistrarse();
        registraseView.setVisible(true);
    }

    private void limpiarCamposLogIn() {
        logInView.getUsernameTextField().setText("");
        logInView.getContrasenaPasswordField().setText("");
    }

    private void configurarEventosEnRegistrarse() {
        final int[] contadorPreguntas = {1};
        final int[] contadorPreguntasRespondidas = {0};
        List<PreguntaRespondida> preguntasRespondidas = new ArrayList<>();
        cargarPregunta(contadorPreguntas[0]);
        contadorPreguntas[0]++;

        registraseView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(preguntasRespondidas.size() < 3){
                    registraseView.mostrarMensaje(mIH.get("registro.preguntas.requeridas"));
                    return;
                }
                String username = registraseView.getUsuarioTextField().getText();
                String password = registraseView.getPasswordField1().getText();

                if (username.isEmpty() || password.isEmpty()) {
                    registraseView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    registraseView.mostrarMensaje(mIH.get("mensaje.usuario.existe"));
                    return;
                }

                usuario = new Usuario(username, password, Rol.USUARIO);
                usuario.setPreguntasVerificacion(preguntasRespondidas);
                usuarioDAO.crear(usuario);
                registraseView.mostrarMensaje(mIH.get("mensaje.usuario.creado"));
                logInView.setVisible(true);
                registraseView.dispose();
            }
        });

        registraseView.getSiguienteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(contadorPreguntas[0] > 12){
                    return;
                }
                cargarPregunta(contadorPreguntas[0]);
                contadorPreguntas[0]++;
                if(!registraseView.getRespuestaTextField().getText().isEmpty()){
                    contadorPreguntasRespondidas[0]++;
                    PreguntaRespondida preguntaRespondida = new PreguntaRespondida(preguntaDAO.buscarPorCodigo(contadorPreguntas[0]), registraseView.getRespuestaTextField().getText());
                    preguntasRespondidas.add(preguntaRespondida);
                }
                registraseView.getRespuestaTextField().setText("");

            }
        });
    }

    private void cargarPregunta(int codigo){
        registraseView.getLblPreguntaCodigo().setText(mIH.get("registro.numero.pregunta") + " " + codigo);
        registraseView.getLblEnunciado().setText(mIH.get(preguntaDAO.buscarPorCodigo(codigo).getEnunciado()));
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        logInView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
        registraseView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }

}
