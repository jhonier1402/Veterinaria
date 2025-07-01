import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Veterinaria - Principal");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton btnRegistro = new JButton("Registrar");
        btnRegistro.setBounds(80, 30, 120, 30);
        add(btnRegistro);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(80, 70, 120, 30);
        add(btnLogin);

        btnRegistro.addActionListener(e -> {
            dispose();
            new VentanaRegistro();
        });

        btnLogin.addActionListener(e -> {
            dispose();
            new VentanaLogin();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}

