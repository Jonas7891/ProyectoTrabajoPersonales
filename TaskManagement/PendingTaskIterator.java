
import java.util.List;

class PendingTaskIterator implements Iterator {
    private List<Task> tasks;
    private int position;

    public PendingTaskIterator(List<Task> tasks) {
        this.tasks = tasks;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        while (position < tasks.size()) {
            if (!tasks.get(position).isCompleted()) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Task next() {
        if (hasNext()) {
            return tasks.get(position++);
        }
        return null;
    }
}