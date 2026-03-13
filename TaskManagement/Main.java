import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = TaskManager.getInstance();
        CommandInvoker invoker = new CommandInvoker();
        Session session = new Session();

        // Mapa para simular una base de datos de usuarios
        Map<String, User> usersDatabase = new HashMap<>();

        // Menú de inicio
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1: // Registro
                    System.out.print("Ingrese un nombre de usuario: ");
                    String username = scanner.nextLine();
                    System.out.print("Ingrese una contraseña: ");
                    String password = scanner.nextLine();
                    if (usersDatabase.containsKey(username)) {
                        System.out.println("El usuario ya existe. Intente con otro nombre.");
                    } else {
                        User newUser = new User(username, password);
                        usersDatabase.put(username, newUser);
                        System.out.println("¡Registro exitoso! Ahora puedes iniciar sesión.");
                    }
                    break;

                case 2: // Iniciar sesión
                    System.out.print("Ingrese su nombre de usuario: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String loginPassword = scanner.nextLine();

                    User user = usersDatabase.get(loginUsername);

                    if (user != null && user.getPassword().equals(loginPassword)) {
                        session.startSession(user);
                        showMenu(scanner, manager, invoker, session); // Mostrar el menú de tareas
                    } else {
                        System.out.println("Usuario o contraseña incorrectos.");
                    }
                    break;

                case 3: // Salir
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    // Menú de tareas después de iniciar sesión
    public static void showMenu(Scanner scanner, TaskManager manager, CommandInvoker invoker, Session session) {
        while (true) {
            if (!session.isLoggedIn()) {
                System.out.println("Debes iniciar sesión para acceder al menú de tareas.");
                return;
            }

            System.out.println("\n--- Menú de Tareas ---");
            System.out.println("1. Crear tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Listar tareas");
            System.out.println("5. Ver tareas pendientes");
            System.out.println("6. Ver tareas completadas");
            System.out.println("7. Cerrar sesión");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    // Crear tarea
                    System.out.print("Ingrese el título de la tarea: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese la fecha de vencimiento (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();
                    Task nuevaTarea = new Task(manager.listTasks().size() + 1, titulo, descripcion, LocalDate.parse(fecha));
                    CreateTaskCommand crearTarea = new CreateTaskCommand(manager, nuevaTarea);
                    invoker.executeCommand(crearTarea);
                    break;

                case 2:
                    // Completar tarea
                    System.out.print("Ingrese el ID de la tarea a completar: ");
                    int idCompletar = scanner.nextInt();
                    CompleteTaskCommand completarTarea = new CompleteTaskCommand(manager, idCompletar);
                    invoker.executeCommand(completarTarea);
                    break;

                case 3:
                    // Eliminar tarea
                    System.out.print("Ingrese el ID de la tarea a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    DeleteTaskCommand eliminarTarea = new DeleteTaskCommand(manager, idEliminar);
                    invoker.executeCommand(eliminarTarea);
                    break;

                case 4:
                    // Listar tareas
                    System.out.println("\n--- Tareas ---");
                    for (Task t : manager.listTasks()) {
                        System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
                    }
                    break;

                case 5:
                    // Ver tareas pendientes
                    System.out.println("\n--- Tareas Pendientes ---");
                    Iterator pendienteIterator = new PendingTaskIterator(manager.listTasks());
                    while (pendienteIterator.hasNext()) {
                        Task t = pendienteIterator.next();
                        System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
                    }
                    break;

                case 6:
                    // Ver tareas completadas
                    System.out.println("\n--- Tareas Completadas ---");
                    Iterator completadaIterator = new CompletedTaskIterator(manager.listTasks());
                    while (completadaIterator.hasNext()) {
                        Task t = completadaIterator.next();
                        System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
                    }
                    break;

                case 7:
                    // Cerrar sesión
                    session.endSession();
                    System.out.println("Sesión cerrada.");
                    return;

                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }
}