import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Trabajador gestor = new Trabajador();

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 1) {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Correo: ");
                String correo = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contraseña = scanner.nextLine();
                System.out.print("Tipo (normal/empleado): ");
                String tipo = scanner.nextLine();

                gestor.registrarUsuario(nombre, correo, contraseña, tipo);

            } else if (opcion == 2) {
                System.out.print("Correo: ");
                String correo = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contraseña = scanner.nextLine();

                gestor.iniciarSesion(correo, contraseña);

            } else if (opcion == 3) {
                System.out.println("Hasta pronto.");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
