import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RegistroEmpleado extends JFrame {

    public RegistroEmpleado() {
        setTitle("Registro de Datos del Empleado");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 20, 150, 25);
        add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(180, 20, 170, 25);
        add(txtId);

        JLabel lblVeterinaria = new JLabel("Veterinaria:");
        lblVeterinaria.setBounds(30, 60, 150, 25);
        add(lblVeterinaria);

        JTextField txtVeterinaria = new JTextField();
        txtVeterinaria.setBounds(180, 60, 170, 25);
        add(txtVeterinaria);

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

        JTextField txtPais = new JTextField();
        txtPais.setBounds(180, 180, 170, 25);
        add(txtPais);

        JLabel lblCiudad = new JLabel("Ciudad de residencia:");
        lblCiudad.setBounds(30, 220, 150, 25);
        add(lblCiudad);

        JTextField txtCiudad = new JTextField();
        txtCiudad.setBounds(180, 220, 170, 25);
        add(txtCiudad);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 280, 100, 30);
        add(btnGuardar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(210, 280, 100, 30);
        add(btnSalir);

        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String veterinaria = txtVeterinaria.getText().trim();
            String genero = (String) comboGenero.getSelectedItem();
            String fecha = txtFechaNac.getText().trim();
            String pais = txtPais.getText().trim();
            String ciudad = txtCiudad.getText().trim();

            if (id.isEmpty() || veterinaria.isEmpty() || fecha.isEmpty() || pais.isEmpty() || ciudad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos_empleados.txt", true))) {
                bw.write(id + "," + veterinaria + "," + genero + "," + fecha + "," + pais + "," + ciudad);
                bw.newLine();
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos.");
            }
        });

        btnSalir.addActionListener(e -> dispose());

        setVisible(true);
    }
}

