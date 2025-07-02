import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VentanaUsuario extends JFrame {
    private String idUsuario;  // correo electrónico del usuario

    public VentanaUsuario(String idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Panel del Usuario");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Bienvenido, " + idUsuario);
        lblTitulo.setBounds(50, 20, 300, 25);
        add(lblTitulo);

        JButton btnPerfil = new JButton("Ver mi perfil");
        btnPerfil.setBounds(120, 60, 150, 30);
        add(btnPerfil);

        JButton btnMascotas = new JButton("Mis mascotas");
        btnMascotas.setBounds(120, 100, 150, 30);
        add(btnMascotas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(120, 150, 150, 30);
        add(btnSalir);

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
}
