import javax.swing.*;
import java.awt.*;
import java.io.*;

public class VerMascotas extends JFrame {
    public VerMascotas(String idUsuario) {
        setTitle("Mis Mascotas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        try (BufferedReader br = new BufferedReader(new FileReader("mascotas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4 && datos[0].equalsIgnoreCase(idUsuario)) {
                    area.append("Nombre: " + datos[1] +
                            ", Especie: " + datos[2] +
                            ", Edad: " + datos[3] + "\n");
                }
            }
        } catch (IOException e) {
            area.setText("Error al leer mascotas.");
        }

        JScrollPane scroll = new JScrollPane(area);
        add(scroll);

        setVisible(true);
    }
}
