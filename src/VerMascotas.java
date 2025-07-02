import javax.swing.*;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;


public class VerMascotas extends JFrame {
    public VerMascotas(String idUsuario) {
        setTitle("Mis Mascotas (PDF)");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        generarPDFMascotas(idUsuario);
    }

    private void generarPDFMascotas(String idUsuario) {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("mis_mascotas.pdf"));
            documento.open();

            com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Mascotas Registradas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6); // columnas
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2.5f, 2.5f, 1.5f, 2f, 1.5f, 3f});

            tabla.addCell("Nombre");
            tabla.addCell("Especie");
            tabla.addCell("Edad");
            tabla.addCell("Vacunas");
            tabla.addCell("Género");
            tabla.addCell("ID Usuario");

            try (BufferedReader br = new BufferedReader(new FileReader("mascotas.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 7 && datos[0].equalsIgnoreCase(idUsuario)) {
                        String nombre = datos[1];
                        String especie = datos[3]; // tipo animal ya está reflejado en especie
                        String edad = datos[4];
                        String vacunas = datos[5];
                        String genero = datos[6];

                        tabla.addCell(nombre);
                        tabla.addCell(especie);
                        tabla.addCell(edad);
                        tabla.addCell(vacunas);
                        tabla.addCell(genero);
                        tabla.addCell(idUsuario);
                    }
                }
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(this, "PDF generado: mis_mascotas.pdf");
            Desktop.getDesktop().open(new File("mis_mascotas.pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e.getMessage());
        }
    }
}
