import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VentanaEmpleado extends JFrame {
    private String idEmpleado;

    public VentanaEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;

        setTitle("Panel del Empleado");
        setSize(550, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Fondo personalizado
        PanelConFondo panel = new PanelConFondo();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel lblTitulo = new JLabel("Opciones del Empleado:");
        lblTitulo.setBounds(20, 10, 300, 25);
        panel.add(lblTitulo);

        JButton btnVerUsuarios = new JButton("Ver usuarios");
        btnVerUsuarios.setBounds(50, 50, 150, 30);
        panel.add(btnVerUsuarios);

        JButton btnVerMascotas = new JButton("Ver mascotas");
        btnVerMascotas.setBounds(250, 50, 180, 30);
        panel.add(btnVerMascotas);

        JButton btnAgregarDatos = new JButton("Agregar / Editar datos");
        btnAgregarDatos.setBounds(50, 100, 180, 30);
        panel.add(btnAgregarDatos);

        JButton btnVerPerfil = new JButton("Ver mi perfil");
        btnVerPerfil.setBounds(250, 100, 180, 30);
        panel.add(btnVerPerfil);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 170, 100, 30);
        panel.add(btnSalir);

        //Exportar usuarios a PDF
        btnVerUsuarios.addActionListener(e -> ExportarUsuariosPDF.exportar());

        //Exportar mascotas a PDF
        btnVerMascotas.addActionListener(e -> VerTodasLasMascotasPDF.generar());

        //Agregar o editar datos del empleado
        btnAgregarDatos.addActionListener(e -> registrarOEditarDatos());

        //Mostrar perfil del empleado
        btnVerPerfil.addActionListener(e -> mostrarPerfil());

        // Acción para salir al menú principal
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
                String[] datos = linea.split(",");
                if (datos.length > 0 && datos[0].equalsIgnoreCase(idEmpleado)) {
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
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[0].equalsIgnoreCase(idEmpleado)) {
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

    // Clase interna para panel con imagen de fondo
    class PanelConFondo extends JPanel {
        private Image fondo;

        public PanelConFondo() {
            try {
                fondo = new ImageIcon(getClass().getResource("/FondoE.png")).getImage(); // asegúrate del path
            } catch (Exception e) {
                System.out.println("No se encontró FondoE.png");
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

