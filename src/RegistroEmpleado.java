import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class RegistroEmpleado extends JFrame {
    public RegistroEmpleado(String idEmpleado) {
        setTitle("Registro de Datos del Empleado");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 20, 150, 25);
        add(lblId);

        JTextField txtId = new JTextField(idEmpleado);
        txtId.setEditable(false);
        txtId.setBounds(180, 20, 170, 25);
        add(txtId);

        JLabel lblVeterinaria = new JLabel("Veterinaria:");
        lblVeterinaria.setBounds(30, 60, 150, 25);
        add(lblVeterinaria);

        String[] veterinarias = {"Petplus", "Clínica Veterinaria Dogtor", "Clínica Veterinaria Vetas"};
        JComboBox<String> comboVeterinaria = new JComboBox<>(veterinarias);
        comboVeterinaria.setBounds(180, 60, 170, 25);
        add(comboVeterinaria);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setBounds(30, 100, 150, 25);
        add(lblGenero);

        String[] generos = {"Hombre", "Mujer", "Otro"};
        JComboBox<String> comboGenero = new JComboBox<>(generos);
        comboGenero.setBounds(180, 100, 170, 25);
        add(comboGenero);

        JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
        lblFechaNac.setBounds(30, 140, 150, 25);
        add(lblFechaNac);

        JTextField txtFechaNac = new JTextField("DD/MM/AAAA");
        txtFechaNac.setBounds(180, 140, 170, 25);
        add(txtFechaNac);

        JLabel lblPais = new JLabel("País de nacimiento:");
        lblPais.setBounds(30, 180, 150, 25);
        add(lblPais);

        String[] paisesLatam = {
                "Colombia", "México", "Venezuela", "Cuba", "Brasil",
                "Argentina", "Chile", "Perú", "Ecuador", "Bolivia",
                "Uruguay", "Paraguay", "Panamá", "Guatemala", "Honduras",
                "El Salvador", "Nicaragua", "Costa Rica", "R. Dominicana"
        };
        JComboBox<String> comboPais = new JComboBox<>(paisesLatam);
        comboPais.setBounds(180, 180, 170, 25);
        add(comboPais);

        JLabel lblCiudad = new JLabel("Ciudad de residencia:");
        lblCiudad.setBounds(30, 220, 150, 25);
        add(lblCiudad);

        String[] ciudadesColombia = {"Bogotá", "Medellín", "Barranquilla", "Cali", "Bucaramanga", "Cartagena"};
        JComboBox<String> comboCiudad = new JComboBox<>(ciudadesColombia);
        comboCiudad.setBounds(180, 220, 170, 25);
        add(comboCiudad);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 280, 100, 30);
        add(btnGuardar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(210, 280, 100, 30);
        add(btnSalir);

        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String veterinaria = (String) comboVeterinaria.getSelectedItem();
            String genero = (String) comboGenero.getSelectedItem();
            String fecha = txtFechaNac.getText().trim();
            String pais = (String) comboPais.getSelectedItem();
            String ciudad = (String) comboCiudad.getSelectedItem();

            if (fecha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            // Eliminar línea anterior si ya existe
            File original = new File("datos_empleados.txt");
            File temporal = new File("temp.txt");

            try (BufferedReader br = new BufferedReader(new FileReader(original));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(temporal))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.startsWith(id + ",")) {
                        bw.write(linea);
                        bw.newLine();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al procesar archivo.");
                return;
            }

            // Guardar nueva línea
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt", true))) {
                String nuevaLinea = id + "," + veterinaria + "," + genero + "," + fecha + "," + pais + "," + ciudad;
                bw.write(nuevaLinea);
                bw.newLine();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos.");
                return;
            }

            // Reemplazar archivo original
            if (!original.delete() || !temporal.renameTo(original)) {
                JOptionPane.showMessageDialog(this, "Error al actualizar datos.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Datos guardados correctamente:\n\n" +
                    "ID: " + id + "\n" +
                    "Veterinaria: " + veterinaria + "\n" +
                    "Género: " + genero + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "País: " + pais + "\n" +
                    "Ciudad: " + ciudad);

            dispose();
        });

        btnSalir.addActionListener(e -> dispose());

        setVisible(true);
    }
}
