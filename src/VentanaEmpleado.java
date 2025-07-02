import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaEmpleado extends JFrame {
    private String idEmpleado;

    public VentanaEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;

        setTitle("Panel del Empleado");
        setSize(500, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("Opciones del Empleado:");
        lblTitulo.setBounds(20, 10, 200, 25);
        add(lblTitulo);

        JButton btnVerUsuarios = new JButton("Ver usuarios");
        btnVerUsuarios.setBounds(50, 50, 150, 30);
        add(btnVerUsuarios);

        JButton btnAgregarDatos = new JButton("Agregar / Editar datos");
        btnAgregarDatos.setBounds(250, 50, 180, 30);
        add(btnAgregarDatos);

        JButton btnVerPerfil = new JButton("Ver mi perfil");
        btnVerPerfil.setBounds(50, 100, 150, 30);
        add(btnVerPerfil);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 150, 100, 30);
        add(btnSalir);

        btnVerUsuarios.addActionListener(e -> {
            ExportarUsuariosPDF.exportar();
        });

        btnAgregarDatos.addActionListener(e -> registrarOEditarDatos());
        btnVerPerfil.addActionListener(e -> mostrarPerfil());
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaPrincipal();
        });

        setVisible(true);
    }

    private void registrarOEditarDatos() {
        boolean yaExiste = false;

        try (BufferedReader br = new BufferedReader(new FileReader("datos_empleados.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idEmpleado)) {
                    yaExiste = true;
                    break;
                }
            }
        } catch (IOException ignored) {}

        if (yaExiste) {
            int opcion = JOptionPane.showConfirmDialog(this, "Ya tienes datos registrados. ¿Deseas editarlos?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (opcion != JOptionPane.YES_OPTION) return;
        }

        new RegistroEmpleado(idEmpleado);
    }

    private void mostrarPerfil() {
        try (BufferedReader br = new BufferedReader(new FileReader("datos_empleados.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idEmpleado)) {
                    String[] datos = linea.split(",");
                    String perfil = "ID: " + datos[0] +
                            "\nVeterinaria: " + datos[1] +
                            "\nGénero: " + datos[2] +
                            "\nFecha de Nacimiento: " + datos[3] +
                            "\nPaís: " + datos[4] +
                            "\nCiudad: " + datos[5];
                    JOptionPane.showMessageDialog(this, perfil, "Mi Perfil", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontraron datos de perfil.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al leer datos del perfil.");
        }
    }
}
