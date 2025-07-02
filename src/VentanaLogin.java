import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaLogin extends JFrame {
    private JTextField correoField;
    private JPasswordField claveField;

    public VentanaLogin() {
        setTitle("Iniciar Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        correoField = new JTextField();
        claveField = new JPasswordField();
        JButton btnIniciar = new JButton("Ingresar");

        add(new JLabel("Correo electrónico:"));
        add(correoField);
        add(new JLabel("Contraseña:"));
        add(claveField);
        add(btnIniciar);

        btnIniciar.addActionListener(e -> verificarCredenciales());

        setVisible(true);
    }

    private void verificarCredenciales() {
        String correo = correoField.getText().trim();
        String clave = new String(claveField.getPassword()).trim();

        if (correo.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4 && datos[1].equalsIgnoreCase(correo) && datos[2].equals(clave)) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + datos[0]);

                    dispose();

                    if (correo.endsWith("@veterinaria.co.com")) {
                        new VentanaEmpleado(correo); // Llama a ventana de empleado
                    } else {
                        new VentanaUsuario(correo); // Llama a ventana de usuario normal
                    }

                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios.");
        }
    }
}
