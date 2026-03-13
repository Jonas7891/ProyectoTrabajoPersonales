class DeleteTaskCommand implements Command {
    private TaskManager taskManager;
    private int taskId;

    public DeleteTaskCommand(TaskManager taskManager, int taskId) {
        this.taskManager = taskManager;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        taskManager.removeTask(taskId);
        System.out.println("Tarea eliminada.");
    }
}
