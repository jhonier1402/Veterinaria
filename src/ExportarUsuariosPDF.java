import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.*;

public class ExportarUsuariosPDF {
    public static void exportar() {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("usuarios.pdf"));
            documento.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Listado de Usuarios Registrados", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new int[]{3, 5, 3});

            tabla.addCell("Nombre");
            tabla.addCell("ID");
            tabla.addCell("Grupo");

            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    tabla.addCell(datos[0]); // nombre
                    tabla.addCell(datos[1]); // correo (como ID)
                    tabla.addCell(datos[3]); // tipo (grupo)
                }
            }

            br.close();
            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "PDF generado como 'usuarios.pdf'");
            Desktop.getDesktop().open(new File("usuarios.pdf"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage());
        }
    }
}
