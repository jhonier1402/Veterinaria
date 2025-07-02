import javax.swing.*;

public class VentanaMascotas extends JFrame {
    private String idUsuario;

    public VentanaMascotas(String idUsuario) {
        this.idUsuario = idUsuario;

        setTitle("Mis Mascotas");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton btnCrear = new JButton("Crear Mascota");
        btnCrear.setBounds(90, 30, 160, 30);
        add(btnCrear);

        JButton btnVer = new JButton("Ver mis Mascotas");
        btnVer.setBounds(90, 70, 160, 30);
        add(btnVer);

        JButton btnVeterinarios = new JButton("Ver Veterinarios");
        btnVeterinarios.setBounds(90, 110, 160, 30);
        add(btnVeterinarios);

        JButton btnSalir = new JButton("Cerrar");
        btnSalir.setBounds(120, 160, 100, 30);
        add(btnSalir);

        btnCrear.addActionListener(e -> new MascotaForm(idUsuario));
        btnVer.addActionListener(e -> new VerMascotas(idUsuario));
        btnVeterinarios.addActionListener(e -> GeneradorPDFVeterinarios.generarPDF());
        btnSalir.addActionListener(e -> dispose());

        setVisible(true);
    }
}
