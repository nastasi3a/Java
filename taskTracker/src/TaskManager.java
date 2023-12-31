import java.util.List;
import java.util.Optional;

public interface TaskManager {
    void createTask(String name, String description);

    void addTask(Task task);

    void createEpic(String name, String description);

    void addEpic(Epic epic);

    void createSubtask(int epicId, String name, String description);

    void addSubtask(Subtask subtask);

    Optional<Task> getTask(final int id);

    Optional <Subtask> getSubtask(final int id);

    Optional <Epic> getEpic(final int id);

    void changeTaskStatus(final int taskId, String newStatus);

    void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus);

    void removeEpic(final int epicId);

    void removeById(final int id);

    public List<Task> getHistory();

}
