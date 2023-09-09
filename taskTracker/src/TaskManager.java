import java.util.List;

public interface TaskManager {
    void createTask(String name, String description);

    void addTask(Task task);

    void createEpic(String name, String description);

    void addEpic(Epic epic);

    void createSubtask(int epicId, String name, String description);

    void addSubtask(Subtask subtask);

    Task getTask(final int id);

    Subtask getSubtask(final int id);

    Epic getEpic(final int id);

    void changeTaskStatus(final int taskId, String newStatus);

    void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus);

    void removeEpic(final int epicId);

    void removeById(final int id);

    public List<Task> getHistory();

}
