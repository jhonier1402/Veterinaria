import javax.swing.*;
import java.io.*;

public class VentanaLogin extends JFrame {
    public VentanaLogin() {
        setTitle("Inicio de Sesión");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 30, 80, 25);
        add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 30, 180, 25);
        add(txtCorreo);

        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setBounds(30, 70, 80, 25);
        add(lblContra);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(120, 70, 180, 25);
        add(txtContra);

        JButton btnIniciar = new JButton("Entrar");
        btnIniciar.setBounds(50, 120, 100, 30);
        add(btnIniciar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(170, 120, 100, 30);
        add(btnSalir);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(30, 160, 300, 25);
        add(lblMensaje);

        btnIniciar.addActionListener(e -> {
            String correo = txtCorreo.getText().trim();
            String contra = new String(txtContra.getPassword());

            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                String linea;
                boolean encontrado = false;

                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 4 &&
                            datos[1].equalsIgnoreCase(correo) &&
                            datos[2].equals(contra)) {

                        encontrado = true;

                        if (datos[3].equalsIgnoreCase("empleado")) {
                            dispose();
                            new VentanaEmpleado(correo);
                        } else {
                            lblMensaje.setText("Acceso solo disponible para empleados.");
                        }
                        break;
                    }
                }

                if (!encontrado) {
                    lblMensaje.setText("Correo o contraseña incorrectos.");
                }
            } catch (IOException ex) {
                lblMensaje.setText("Error al leer usuarios.");
            }
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaPrincipal();
        });

        setVisible(true);
    }
}

