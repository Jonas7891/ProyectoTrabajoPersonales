import java.util.List;

class AllTaskIterator implements Iterator {
    private List<Task> tasks;
    private int position;

    public AllTaskIterator(List<Task> tasks) {
        this.tasks = tasks;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < tasks.size();
    }

    @Override
    public Task next() {
        if (hasNext()) {
            return tasks.get(position++);
        }
        return null;
    }
}
