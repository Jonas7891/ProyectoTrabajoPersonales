class ListTasksCommand implements Command {
    private TaskManager taskManager;

    public ListTasksCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Tareas ---");
        Iterator iterator = taskManager.getAllTaskIterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            System.out.println("ID: " + t.getId() + ", Título: " + t.getTitle());
        }
    }
}
