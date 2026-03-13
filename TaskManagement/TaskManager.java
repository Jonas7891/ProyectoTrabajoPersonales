
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    // Instancia estática privada para la clase Singleton
    private static TaskManager instance;

    // Atributos
    private List<Task> tasks;
    private List<User> users;
    private List<Workspace> workspaces;
    private List<String> history;

    // Constructor privado para evitar instanciación desde fuera
    private TaskManager() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
        workspaces = new ArrayList<>();
        history = new ArrayList<>();
    }

    // Método público para obtener la única instancia de TaskManager
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    // Métodos para agregar tareas, usuarios, y workspaces
    public void addTask(Task task) {
        tasks.add(task);
        addHistory("Task created: " + task.getTitle());
    }

    public void removeTask(int taskId) {
        Task task = findTask(taskId);
        if (task != null) {
            tasks.remove(task);
            addHistory("Task deleted: " + task.getTitle());
        }
    }

    // Completar tarea en el TaskManager
    public void completeTask(int taskId) {
        Task task = findTask(taskId);
        if (task != null) {
            task.completeTask(); // Llamamos al método de Task para completar la tarea
        }
    }

    // Método para editar tareas
    public void editTask(int taskId, String newTitle, String newDescription) {
        Task task = findTask(taskId);
        if (task != null) {
            task.setTitle(newTitle); // Modificar el título
            task.setDescription(newDescription); // Modificar la descripción
            System.out.println("Tarea editada: " + task.getTitle());
        }
    }

    // Método para encontrar tareas por ID
    private Task findTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addWorkspace(Workspace workspace) {
        workspaces.add(workspace);
    }

    public void addHistory(String action) {
        history.add(action);
    }

    public List<String> getHistory() {
        return history;
    }

    // Métodos para listar tareas
    public List<Task> listTasks() {
        return tasks;
    }

    // Método para obtener iterador de todas las tareas
    public Iterator getAllTaskIterator() {
        return new AllTaskIterator(tasks);
    }

    // Método para obtener iterador de tareas pendientes
    public Iterator getPendingTaskIterator() {
        return new PendingTaskIterator(tasks);
    }

    // Método para obtener iterador de tareas completadas
    public Iterator getCompletedTaskIterator() {
        return new CompletedTaskIterator(tasks);
    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    // Método para obtener la instancia de usuarios
    public List<User> getUsers() {
        return users;
    }
}
