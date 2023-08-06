public interface TaskManager {
    void createTask(String name, String description);

    void createEpic(String name, String description);

    void createSubtaskInEpic(int epicId, String name, String description, String epicName);

    void showSubtasksInEpic(int epicId);

    void showTasks();

    void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus);

    void removeEpic(final int epicId);

    void removeById(final int id);

    void getTaskById(final int id);
}
