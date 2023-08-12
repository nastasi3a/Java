import java.util.List;
import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    List<Task> history;
    InMemoryHistoryManager() {
        history = new ArrayList<Task>();
    }
    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public void remove(int id) {
        history.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

}
