import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    protected LinkedList<Task> history;
    protected HashMap<Integer, Node<Task>> linkToTaskInHistory;
    InMemoryHistoryManager() {
        history = new LinkedList<>();
        linkToTaskInHistory = new HashMap<>();

    }
    @Override
    public void add(Task task) {
        this.remove(task.id);
        history.linkLast(task);
        linkToTaskInHistory.put(task.getId(), history.getTail());
    }

    @Override
    public void remove(int id) {
        Node<Task> node = linkToTaskInHistory.get(id);
        if (node != null) {
            history.removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        return this.history.getArrayListOfData();
    }

    protected String getStringWithId() {
        return history.getIdString();
    }

}
