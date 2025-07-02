import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.Desktop;
import java.io.*;

public class VerTodasLasMascotasPDF {
    public static void generar() {
        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("todas_las_mascotas.pdf"));
            doc.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Listado de Todas las Mascotas", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);
            doc.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{4, 3, 3, 3, 3});

            tabla.addCell("ID Usuario");
            tabla.addCell("Nombre");
            tabla.addCell("Animal");
            tabla.addCell("Especie");
            tabla.addCell("Vacunas");

            try (BufferedReader br = new BufferedReader(new FileReader("mascotas.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 7) {
                        String id = datos[0];
                        String nombre = datos[1];
                        String animal = datos[2];
                        String especie = datos[3];
                        String vacunas = datos[5];

                        tabla.addCell(id);
                        tabla.addCell(nombre);
                        tabla.addCell(animal);
                        tabla.addCell(especie);
                        tabla.addCell(vacunas);
                    }
                }
            }

            doc.add(tabla);
            doc.close();

            JOptionPane.showMessageDialog(null, "PDF generado: todas_las_mascotas.pdf");
            Desktop.getDesktop().open(new File("todas_las_mascotas.pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF de mascotas.");
        }
    }
}

