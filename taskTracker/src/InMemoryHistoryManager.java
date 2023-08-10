import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> history;
    InMemoryHistoryManager() {
        history = new ArrayList<>();
    }
    @Override
    public void add(Task task) {
        history.add(task);

    }

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }

}
