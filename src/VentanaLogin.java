import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaLogin extends JFrame {
    private JTextField correoField;
    private JPasswordField claveField;

    public VentanaLogin() {
        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // panel con imagen puesta de fondo
        PanelConFondo fondoPanel = new PanelConFondo();
        fondoPanel.setLayout(null);
        setContentPane(fondoPanel);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Times new roman", Font.BOLD, 25));
        lblTitulo.setForeground(new Color(100, 10, 20));
        lblTitulo.setBounds(130, 20, 200, 30);
        fondoPanel.add(lblTitulo);

        // Etiqueta y campo para el correo
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(50, 70, 130, 25);
        fondoPanel.add(lblCorreo);

        correoField = new JTextField();
        correoField.setBounds(190, 70, 150, 25);
        fondoPanel.add(correoField);

        // Etiqueta y campo para la contraseña
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(50, 110, 130, 25);
        fondoPanel.add(lblClave);

        claveField = new JPasswordField();
        claveField.setBounds(190, 110, 150, 25);
        fondoPanel.add(claveField);

        // Botón para iniciar sesión
        JButton btnIniciar = new JButton("Ingresar");
        btnIniciar.setBounds(140, 170, 120, 30);
        fondoPanel.add(btnIniciar);

        // Acción del botón: llama al método que verifica los datos
        btnIniciar.addActionListener(e -> verificarCredenciales());

        setVisible(true);  // Muestra la ventana
    }

    // Método que verifica el correo y la contraseña ingresados
    private void verificarCredenciales() {
        String correo = correoField.getText().trim();                 // Obtener correo
        String clave = new String(claveField.getPassword()).trim();  // Obtener clave

        // Validación simple de campos vacíos
        if (correo.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        // Leer el archivo de usuarios línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");  // Se espera: nombre,correo,clave,tipo
                if (datos.length == 4 && datos[1].equalsIgnoreCase(correo) && datos[2].equals(clave)) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + datos[0]);  // Mensaje de bienvenida

                    dispose();  // Cierra esta ventana

                    // Redirige según el tipo de usuario (empleado o usuario normal)
                    if (correo.endsWith("@veterinaria.co.com")) {
                        new VentanaEmpleado(correo); // Abre panel del empleado
                    } else {
                        new VentanaUsuario(correo);  // Abre panel del usuario normal
                    }
                    return;
                }
            }
            // Si no encontró coincidencias
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios.");
        }
    }

    // Clase interna que define un panel con imagen de fondo
    class PanelConFondo extends JPanel {
        private Image fondo;

        public PanelConFondo() {
            try {
                // Carga la imagen desde recursos
                fondo = new ImageIcon(getClass().getResource("/FondoE.png")).getImage();
            } catch (Exception e) {
                System.out.println("No se encontró la imagen de fondo FondoE.png");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibuja la imagen como fondo del panel
            if (fondo != null) {
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}