import javax.swing.*;
import java.io.*;

public class VentanaRegistro extends JFrame {
    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 20, 80, 25);
        add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 180, 25);
        add(txtNombre);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 60, 80, 25);
        add(lblCorreo);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 60, 180, 25);
        add(txtCorreo);

        JLabel lblContra = new JLabel("Contraseña:");
        lblContra.setBounds(30, 100, 80, 25);
        add(lblContra);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(120, 100, 180, 25);
        add(txtContra);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 150, 100, 30);
        add(btnRegistrar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(170, 150, 100, 30);
        add(btnSalir);

        JLabel lblMensaje = new JLabel("");
        lblMensaje.setBounds(30, 190, 300, 25);
        add(lblMensaje);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contra = new String(txtContra.getPassword());

            if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                lblMensaje.setText("Correo inválido.");
                return;
            }

            String tipo = correo.endsWith("@veterinaria.co.com") ? "empleado" : "normal";

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
                bw.write(nombre + "," + correo + "," + contra + "," + tipo);
                bw.newLine();
                lblMensaje.setText("Usuario registrado correctamente.");
            } catch (IOException ex) {
                lblMensaje.setText("Error al guardar usuario.");
            }
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaPrincipal();
        });

        setVisible(true);
    }
}
