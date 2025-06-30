import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class LoginRegisterForm extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    public LoginRegisterForm(){
        setTitle("Login / Registro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Iniciar Sesión");
        registerButton = new JButton("Registrarse");
        add(new JLabel("Usuario:"));
        add(usernameField);
        add(new JLabel("Contraseña:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);
        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());
        setVisible(true);
    }
    private void login(){
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (validateUser(user, pass)){
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            dispose();
            new MascotaForm(user);
        }
        else{
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
    private void register(){
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.isEmpty() || pass.isEmpty()){
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }
        if (userExists(user)){
            JOptionPane.showMessageDialog(this, "El usuario ya existe");
            return;
        }
        try (FileWriter fw = new FileWriter("usuarios.txt", true)){
            fw.write(user + "," + pass + "\n");
            JOptionPane.showMessageDialog(this, "Usuario registrado");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    private boolean validateUser(String user, String pass){
        try (Scanner scanner = new Scanner(new File("usuarios.txt"))){
            while (scanner.hasNextLine()){
                String[] datos = scanner.nextLine().split(",");
                if (datos.length == 2 && datos[0].equals(user) && datos[1].equals(pass)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    private boolean userExists(String user){
        try (Scanner scanner = new Scanner(new File("usuarios.txt"))){
            while (scanner.hasNextLine()){
                String[] datos = scanner.nextLine().split(",");
                if (datos.length >= 1 && datos[0].equals(user)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}