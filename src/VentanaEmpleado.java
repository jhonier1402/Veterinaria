import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaEmpleado extends JFrame {

    public VentanaEmpleado() {
        setTitle("Panel del Empleado");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Opciones del Empleado:");
        lblTitulo.setBounds(20, 10, 200, 25);
        add(lblTitulo);

        JButton btnVerUsuarios = new JButton("Ver usuarios");
        btnVerUsuarios.setBounds(50, 50, 150, 30);
        add(btnVerUsuarios);

        JButton btnAgregarDatos = new JButton("Agregar datos");
        btnAgregarDatos.setBounds(250, 50, 150, 30);
        add(btnAgregarDatos);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 100, 100, 30);
        add(btnSalir);

        // Acción del botón Ver Usuarios
        btnVerUsuarios.addActionListener(e -> {
            JTextArea area = new JTextArea();
            area.setEditable(false);

            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    area.append(linea + "\n");
                }
            } catch (IOException ex) {
                area.setText("Error al leer archivo o no existe.");
            }

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, scroll, "Usuarios Registrados", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ahora sí abre la ventana de registro del empleado
        btnAgregarDatos.addActionListener(e -> new RegistroEmpleado());

        // Acción del botón Salir
        btnSalir.addActionListener(e -> {
            dispose(); // Cierra esta ventana
            new VentanaPrincipal(); // Vuelve al menú principal
        });

        setVisible(true);
    }
}
