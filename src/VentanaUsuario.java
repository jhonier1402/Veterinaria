import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VentanaUsuario extends JFrame {
    private String idUsuario;  // Correo electrónico del usuario

    public VentanaUsuario(String idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Panel del Usuario");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        PanelConFondo fondoPanel = new PanelConFondo();
        fondoPanel.setLayout(null);
        setContentPane(fondoPanel);

        JLabel lblTitulo = new JLabel("Bienvenido, " + idUsuario);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        lblTitulo.setBounds(100, 20, 300, 30);
        fondoPanel.add(lblTitulo);

        JButton btnPerfil = new JButton("Ver mi perfil");
        btnPerfil.setBounds(140, 70, 160, 30);
        fondoPanel.add(btnPerfil);

        JButton btnMascotas = new JButton("Mis mascotas");
        btnMascotas.setBounds(140, 110, 160, 30);
        fondoPanel.add(btnMascotas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(140, 160, 160, 30);
        fondoPanel.add(btnSalir);

        btnPerfil.addActionListener(e -> mostrarPerfil());
        btnMascotas.addActionListener(e -> new VentanaMascotas(idUsuario));
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaPrincipal();
        });

        setVisible(true);
    }

    private void mostrarPerfil() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4 && datos[1].equalsIgnoreCase(idUsuario)) {
                    String perfil = "Nombre: " + datos[0] +
                            "\nCorreo: " + datos[1] +
                            "\nTipo: " + datos[3];
                    JOptionPane.showMessageDialog(this, perfil, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró tu perfil.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer datos.");
        }
    }

    // Clase interna para el fondo de la ventana
    class PanelConFondo extends JPanel {
        private Image fondo;

        public PanelConFondo() {
            try {
                fondo = new ImageIcon(getClass().getResource("/FondoU.png")).getImage();
            } catch (Exception e) {
                System.out.println("No se encontró la imagen FondoU.png");
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

