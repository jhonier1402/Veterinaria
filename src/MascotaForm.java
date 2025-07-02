import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MascotaForm extends JFrame {
    private JTextField nombreField, especieField, edadField;
    private JButton guardarButton;
    private String usuario;

    public MascotaForm(String usuario) {
        this.usuario = usuario;

        setTitle("Registrar Mascota");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        nombreField = new JTextField();
        especieField = new JTextField();
        edadField = new JTextField();
        guardarButton = new JButton("Guardar Mascota");

        add(new JLabel("Nombre de la Mascota:"));
        add(nombreField);
        add(new JLabel("Especie:"));
        add(especieField);
        add(new JLabel("Edad:"));
        add(edadField);
        add(guardarButton);

        guardarButton.addActionListener(e -> guardarMascota());

        setVisible(true);
    }

    private void guardarMascota() {
        String nombre = nombreField.getText().trim();
        String especie = especieField.getText().trim();
        String edad = edadField.getText().trim();

        if (nombre.isEmpty() || especie.isEmpty() || edad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("mascotas.txt", true))) {
            bw.write(usuario + "," + nombre + "," + especie + "," + edad);
            bw.newLine();
            JOptionPane.showMessageDialog(this, "Mascota registrada correctamente.");
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la mascota.");
        }
    }
}
