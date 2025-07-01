import java.util.ArrayList;

public class Trabajador {
    private ArrayList<Usuario> usuarios;

    public Trabajador() {
        usuarios = new ArrayList<>();
    }

    public boolean correoValido(String correo) {
        return correo.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean correoExiste(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarUsuario(String nombre, String correo, String contraseña, String tipo) {
        if (!correoValido(correo)) {
            System.out.println("El correo no tiene un formato válido.");
            return false;
        }

        if (correoExiste(correo)) {
            System.out.println("Ya existe un usuario con ese correo.");
            return false;
        }

        if (correo.toLowerCase().endsWith("@veterinaria.co.com")) {
            tipo = "empleado";
        } else {
            tipo = "normal";
        }

        usuarios.add(new Usuario(nombre, correo, contraseña, tipo));
        System.out.println("Usuario registrado correctamente.");
        return true;
    }

    public Usuario iniciarSesion(String correo, String contraseña) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo) && u.verificarContraseña(contraseña)) {
                System.out.println("Bienvenido " + u.getNombre() + " (" + u.getTipo() + ")");
                return u;
            }
        }
        System.out.println("Correo o contraseña incorrectos.");
        return null;
    }
}
