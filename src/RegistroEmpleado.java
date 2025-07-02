import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class RegistroEmpleado extends JFrame {
    public RegistroEmpleado(String idEmpleado) {
        setTitle("Registro de Datos del Empleado");
        setSize(400, 520);
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

        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) dias[i] = String.valueOf(i + 1);
        JComboBox<String> comboDia = new JComboBox<>(dias);
        comboDia.setBounds(180, 140, 50, 25);
        add(comboDia);

        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        JComboBox<String> comboMes = new JComboBox<>(meses);
        comboMes.setBounds(235, 140, 50, 25);
        add(comboMes);

        String[] anios = new String[131];
        for (int i = 0; i < 131; i++) anios[i] = String.valueOf(1900 + i);
        JComboBox<String> comboAnio = new JComboBox<>(anios);
        comboAnio.setBounds(290, 140, 60, 25);
        add(comboAnio);

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

        JLabel lblNumero = new JLabel("Número de teléfono:");
        lblNumero.setBounds(30, 260, 150, 25);
        add(lblNumero);

        JTextField txtNumero = new JTextField();
        txtNumero.setBounds(180, 260, 170, 25);
        add(txtNumero);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 320, 100, 30);
        add(btnGuardar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(210, 320, 100, 30);
        add(btnSalir);

        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String veterinaria = (String) comboVeterinaria.getSelectedItem();
            String genero = (String) comboGenero.getSelectedItem();
            String fecha = comboDia.getSelectedItem() + "/" + comboMes.getSelectedItem() + "/" + comboAnio.getSelectedItem();
            String pais = (String) comboPais.getSelectedItem();
            String ciudad = (String) comboCiudad.getSelectedItem();
            String numero = txtNumero.getText().trim();

            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            File original = new File("datos_empleados.txt");
            File temporal = new File("temp_empleados.txt");

            try (BufferedReader br = new BufferedReader(new FileReader(original));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(temporal))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.startsWith(id + ",")) {
                        bw.write(linea);
                        bw.newLine();
                    }
                }

                String nuevaLinea = id + "," + veterinaria + "," + genero + "," + fecha + "," + pais + "," + ciudad + "," + numero;
                bw.write(nuevaLinea);
                bw.newLine();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al procesar archivo.");
                return;
            }

            try {
                Files.deleteIfExists(original.toPath());
                Files.move(temporal.toPath(), original.toPath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar los datos.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Datos guardados correctamente:\n" +
                    "ID: " + id + "\n" +
                            "Veterinaria: " + veterinaria + "\n" +
                            "Género: " + genero + "\n" +
                            "Fecha: " + fecha + "\n" +
                            "País: " + pais + "\n" +
                            "Ciudad: " + ciudad + "\n" +
                            "Número: " + numero);

            dispose();
        });

        btnSalir.addActionListener(e -> dispose());

        setVisible(true);
    }
}
