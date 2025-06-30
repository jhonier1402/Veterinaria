import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class MascotaForm extends JFrame{
    private JTextField nombreField, especieField, edadField;
    private JButton guardarButton;
    private String usuario;
    public MascotaForm(String usuario){
        this.usuario = usuario;
        setTitle("Registrar Mascota");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
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
    private void guardarMascota(){
        String nombre = nombreField.getText();
        String especie = especieField.getText();
        String edad = edadField.getText();
        if (nombre.isEmpty() || especie.isEmpty() || edad.isEmpty()){
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }
        try (FileWriter fw = new FileWriter("mascotas.txt", true)){
            fw.write(usuario + "," + nombre + "," + especie + "," + edad + "\n");
            JOptionPane.showMessageDialog(this, "Mascota registrada");
            nombreField.setText("");
            especieField.setText("");
            edadField.setText("");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}