class EditTaskCommand implements Command {
    private TaskManager taskManager;
    private int taskId;
    private String newTitle;
    private String newDescription;

    public EditTaskCommand(TaskManager taskManager, int taskId, String newTitle, String newDescription) {
        this.taskManager = taskManager;
        this.taskId = taskId;
        this.newTitle = newTitle;
        this.newDescription = newDescription;
    }

    @Override
    public void execute() {
        taskManager.editTask(taskId, newTitle, newDescription);
        System.out.println("Tarea editada.");
    }
}