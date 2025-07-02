import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {
    private Clip musicaClip; // Control para detener la música si se desea

    public VentanaPrincipal() {
        setTitle("Veterinaria - Inicio");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PanelConFondo panel = new PanelConFondo();
        panel.setLayout(null); // Posicionamiento manual
        setContentPane(panel);

        // Título
        JLabel lblTitulo = new JLabel("Grupo Veterinario SAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(100, 10, 20));
        lblTitulo.setBounds(120, 30, 300, 30);
        panel.add(lblTitulo);

        // Botón de login
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(170, 220, 150, 40);
        panel.add(btnLogin);

        // Botón de registro
        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(170, 270, 150, 40);
        panel.add(btnRegistro);

        // Eventos
        btnLogin.addActionListener(e -> {
            detenerMusica(); // Detener música al salir
            dispose();
            new VentanaLogin();
        });

        btnRegistro.addActionListener(e -> {
            detenerMusica();
            dispose();
            new VentanaRegistro();
        });

        // Reproducir música de fondo
        reproducirMusica("/musica_inicio.wav");

        setVisible(true);
    }

    // Panel con imagen de fondo
    class PanelConFondo extends JPanel {
        private Image fondo;

        public PanelConFondo() {
            try {
                fondo = new ImageIcon(getClass().getResource("/fondo.png")).getImage();
            } catch (Exception e) {
                System.out.println("No se encontró la imagen de fondo.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (fondo != null) {
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Método para reproducir música
    public void reproducirMusica(String ruta) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
            musicaClip = AudioSystem.getClip();
            musicaClip.open(audio);
            musicaClip.loop(Clip.LOOP_CONTINUOUSLY); // Música en bucle
            musicaClip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    // Método para detener música
    public void detenerMusica() {
        if (musicaClip != null && musicaClip.isRunning()) {
            musicaClip.stop();
            musicaClip.close();
        }
    }
}

