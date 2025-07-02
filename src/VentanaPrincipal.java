import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Veterinaria - Inicio");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PanelConFondo panel = new PanelConFondo();
        panel.setLayout(null); // Posicionamiento manual
        setContentPane(panel);

        // Título arriba
        JLabel lblTitulo = new JLabel("Grupo Veterinario SAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(110, 40, 40)); // Color gris oscuro
        lblTitulo.setBounds(120, 30, 300, 30); // Posición centrada
        panel.add(lblTitulo);

        // Botón de login
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(170, 220, 150, 40);
        panel.add(btnLogin);

        // Botón de registro
        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setBounds(170, 270, 150, 40);
        panel.add(btnRegistro);

        btnLogin.addActionListener(e -> {
            dispose();
            new VentanaLogin();
        });

        btnRegistro.addActionListener(e -> {
            dispose();
            new VentanaRegistro();
        });

        setVisible(true);
    }

    // Panel con fondo de imagen
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
}
