# Repositorio de Información para Veterinarias

Esta aplicación es un sistema de escritorio desarrollado en Java, diseñado para gestionar usuarios, empleados veterinarios y sus respectivas mascotas, ofreciendo una interfaz visual sencilla y funcional tanto para usuarios normales como para empleados de veterinaria.

## Características

### Inicio de sesión y registro:
- Permite a los usuarios y empleados registrarse y acceder con su correo y contraseña.

### Doble rol de acceso:
- **Usuarios normales** pueden registrar, ver y gestionar información de sus mascotas.
- **Empleados (veterinarios)** tienen acceso a funciones administrativas como ver todos los usuarios y mascotas.

### Gestión de mascotas:
- Los usuarios pueden registrar mascotas incluyendo campos como nombre, especie, tipo de animal, género, edad y vacunas.  
  La especie se adapta automáticamente según el tipo de animal.

### Panel de empleados:
- Ver todos los usuarios registrados en formato PDF.
- Ver todas las mascotas registradas en formato PDF.
- Agregar y editar sus propios datos personales.

### Panel de usuarios:
- Consultar su perfil.
- Ver sus mascotas registradas.
- Consultar veterinarios disponibles.
  
## Requisitos
- Java 8 o superior
- IDE 
- Librería iText para generar PDF
  
## Instalación
1. Clona el repositorio o copia los archivos.
2. Abre el proyecto en tu IDE.
3. Asegúrate de tener agregada la librería iText.
4. Ejecuta `Main.java`.
   
**##Estructura general**
-`VentanaPrincipal.java`
- `VentanaLogin.java`
- `VentanaRegistro.java`
- `VentanaEmpleado.java`
- `VentanaUsuario.java`
- `RegistroEmpleado.java`
- `MascotaForm.java`
- `VerMascotas.java`
- `ExportarUsuariosPDF.java`
- `VerTodasLasMascotasPDF.java
  
**Autores**
  Jhonier1402
  San-pachon
