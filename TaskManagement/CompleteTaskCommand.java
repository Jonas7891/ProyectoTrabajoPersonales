class CompleteTaskCommand implements Command {
    private TaskManager taskManager;
    private int taskId;

    public CompleteTaskCommand(TaskManager taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        taskManager.completeTask(taskId);
        System.out.println("Tarea completada.");
    }
}