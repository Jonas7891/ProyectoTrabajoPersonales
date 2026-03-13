class ListPendingTasksCommand implements Command {
    private TaskManager taskManager;

    public ListPendingTasksCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Tareas Pendientes ---");
        Iterator iterator = taskManager.getPendingTaskIterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
        }
    }
}
