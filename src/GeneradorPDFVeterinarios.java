import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.io.*;

public class GeneradorPDFVeterinarios {

    public static void  generarPDF() {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("veterinarios.pdf"));
            documento.open();

            // Título
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Veterinarios Registrados", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            // Tabla con 5 columnas
            PdfPTable tabla = new PdfPTable(5); // nombre, ciudad, id, veterinaria, número
            tabla.addCell("Nombre");
            tabla.addCell("Ciudad");
            tabla.addCell("ID");
            tabla.addCell("Veterinaria");
            tabla.addCell("Número");

            // Leer los datos
            try (BufferedReader br = new BufferedReader(new FileReader("datos_empleados.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 7) {
                        String id = datos[0];
                        String veterinaria = datos[1];
                        String genero = datos[2];
                        String fecha = datos[3];
                        String pais = datos[4];
                        String ciudad = datos[5];
                        String telefono = datos[6];

                        // Buscar el nombre en usuarios.txt
                        String nombre = buscarNombrePorID(id);

                        tabla.addCell(nombre);
                        tabla.addCell(ciudad);
                        tabla.addCell(id);
                        tabla.addCell(veterinaria);
                        tabla.addCell(telefono);
                    }
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "PDF generado: veterinarios.pdf");
            java.awt.Desktop.getDesktop().open(new File("veterinarios.pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }

    private static String buscarNombrePorID(String correo) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2 && datos[1].equalsIgnoreCase(correo)) {
                    return datos[0];
                }
            }
        } catch (IOException e) {
            return "Desconocido";
        }
        return "Desconocido";
    }
}
