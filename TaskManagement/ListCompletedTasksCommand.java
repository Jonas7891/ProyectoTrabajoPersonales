class ListCompletedTasksCommand implements Command {
    private TaskManager taskManager;

    public ListCompletedTasksCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Tareas Completadas ---");
        Iterator iterator = taskManager.getCompletedTaskIterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
        }
    }
}
