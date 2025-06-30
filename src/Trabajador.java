import java.util.ArrayList;
public class Trabajador{
    private ArrayList<Usuario> usuarios;
    public Trabajador() {
        usuarios = new ArrayList<>();
    }
    public boolean registrarUsuario(String nombre, String correo, String contraseña, String tipo){
        for (Usuario u : usuarios){
            if (u.getCorreo().equals(correo)){
                System.out.println("Ya existe un usuario con ese correo.");
                return false;
            }
        }
        usuarios.add(new Usuario(nombre, correo, contraseña, tipo));
        System.out.println("Usuario registrado correctamente.");
        return true;
    }
    public Usuario iniciarSesion(String correo, String contraseña){
        for (Usuario u : usuarios){
            if (u.getCorreo().equals(correo) && u.verificarContraseña(contraseña)){
                System.out.println("Bienvenido " + u.getNombre() + " (" + u.getTipo() + ")");
                return u;
            }
        }
        System.out.println("Correo o contraseña incorrectos.");
        return null;
    }
}