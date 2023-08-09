public interface TaskManager {
    void createTask(String name, String description);

    void createEpic(String name, String description);

    void createSubtaskInEpic(int epicId, String name, String description, String epicName);

    void getTask(final int id);

    void getSubtask(final int id);

    void getEpic(final int id);

    void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus);

    void removeEpic(final int epicId);

    void removeById(final int id);

    String history();

}
