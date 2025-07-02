import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import javax.swing.*;

public class GeneradorPDFVeterinarios {

    public static void generarPDF() {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("veterinarios.pdf"));
            documento.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("Veterinarios Disponibles", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("Nombre");
            tabla.addCell("Correo");
            tabla.addCell("Ciudad");
            tabla.addCell("Veterinaria");

            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 4 && datos[3].equalsIgnoreCase("empleado")) {
                        String correo = datos[1];
                        String nombre = datos[0];

                        // Buscar en datos_empleados.txt por más información
                        try (BufferedReader br2 = new BufferedReader(new FileReader("datos_empleados.txt"))) {
                            String lineaEmpleado;
                            while ((lineaEmpleado = br2.readLine()) != null) {
                                String[] emp = lineaEmpleado.split(",");
                                if (emp.length >= 6 && emp[0].equalsIgnoreCase(correo)) {
                                    String ciudad = emp[5];
                                    String veterinaria = emp[1];
                                    tabla.addCell(nombre);
                                    tabla.addCell(correo);
                                    tabla.addCell(ciudad);
                                    tabla.addCell(veterinaria);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            documento.add(tabla);
            documento.close();
            JOptionPane.showMessageDialog(null, "PDF generado: veterinarios.pdf");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF.");
        }
    }
}
