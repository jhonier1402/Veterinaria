import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class MascotaForm extends JFrame {
    private JTextField nombreField, edadField;
    private JComboBox<String> animalBox, especieBox, vacunaBox, generoBox;
    private JButton guardarButton;
    private String usuario;

    private final Map<String, String[]> especiesPorAnimal = new HashMap<>();

    public MascotaForm(String usuario) {
        this.usuario = usuario;
        setTitle("Registrar Mascota");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 1));

        // Configuración inicial
        nombreField = new JTextField();
        edadField = new JTextField();

        // Mapear animales a sus especies
        especiesPorAnimal.put("Perro", new String[]{"Dálmata", "Pekinés", "Labrador", "Chihuahua", "Bulldog", "Pastor Alemán", "Beagle", "Golden Retriever", "Pug", "Otro"});
        especiesPorAnimal.put("Gato", new String[]{"Siames", "Angora", "Persa", "Maine Coon", "Sphynx", "Bengala", "Abisinio", "Ragdoll", "Bosque de Noruega", "Otro"});
        especiesPorAnimal.put("Ave", new String[]{"Loro", "Canario", "Periquito", "Cacatúa", "Agapornis", "Guacamayo", "Jilguero", "Ninfa", "Tucán", "Otro"});
        especiesPorAnimal.put("Pez", new String[]{"Betta", "Guppy", "Molly", "Tetra", "Pez Ángel", "Disco", "Carpa", "Oscar", "Neón", "Otro"});
        especiesPorAnimal.put("Otro", new String[]{"No registra"});

        // Combo de tipo de animal
        animalBox = new JComboBox<>(new String[]{"Perro", "Gato", "Ave", "Pez", "Otro"});
        especieBox = new JComboBox<>(especiesPorAnimal.get("Perro")); // Inicial

        vacunaBox = new JComboBox<>(new String[]{"Sí", "No"});
        generoBox = new JComboBox<>(new String[]{"Macho", "Hembra", "Otro"});

        // Listener para cambiar especies según animal
        animalBox.addActionListener(e -> {
            String tipo = (String) animalBox.getSelectedItem();
            especieBox.setModel(new DefaultComboBoxModel<>(especiesPorAnimal.get(tipo)));
        });

        guardarButton = new JButton("Guardar Mascota");

        add(new JLabel("Nombre de la Mascota:"));
        add(nombreField);
        add(new JLabel("Tipo de Animal:"));
        add(animalBox);
        add(new JLabel("Especie:"));
        add(especieBox);
        add(new JLabel("Edad:"));
        add(edadField);
        add(new JLabel("¿Tiene vacunas?"));
        add(vacunaBox);
        add(new JLabel("Género:"));
        add(generoBox);
        add(guardarButton);

        guardarButton.addActionListener(e -> guardarMascota());

        setVisible(true);
    }

    private void guardarMascota() {
        String nombre = nombreField.getText().trim();
        String tipoAnimal = (String) animalBox.getSelectedItem();
        String especie = (String) especieBox.getSelectedItem();
        String edad = edadField.getText().trim();
        String vacunas = (String) vacunaBox.getSelectedItem();
        String genero = (String) generoBox.getSelectedItem();

        if (nombre.isEmpty() || edad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("mascotas.txt", true))) {
            bw.write(usuario + "," + nombre + "," + tipoAnimal + "," + especie + "," + edad + "," + vacunas + "," + genero);
            bw.newLine();
            JOptionPane.showMessageDialog(this, "Mascota registrada correctamente.");
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la mascota.");
        }
    }
}
