public abstract class FileBackedTaskManager extends InMemoryTaskManager {

    FileBackedTaskManager(HistoryManager history) {
        super(history);
        loadFile();
    }

    @Override
    public void createTask(String name, String description) {
        super.createTask(name, description);
        saveFile();
    }

    @Override
    public void createEpic(String name, String description) {
        super.createEpic(name, description);
        saveFile();
    }

    @Override
    public void createSubtaskInEpic(int epicId, String name, String description, String epicName) {
        super.createSubtaskInEpic(epicId, name, description, epicName);
        saveFile();
    }

    @Override
    public void getTask(int id) {
        super.getTask(id);
        saveFile();
    }

    @Override
    public void getSubtask(int id) {
        super.getSubtask(id);
        saveFile();
    }

    @Override
    public void getEpic(int id) {
        super.getEpic(id);
        saveFile();
    }

    @Override
    public void changeSubtaskStatus(int epicId, int subtaskId, String newStatus) {
        super.changeSubtaskStatus(epicId, subtaskId, newStatus);
        saveFile();
    }

    @Override
    public void removeEpic(int epicId) {
        super.removeEpic(epicId);
        saveFile();
    }

    @Override
    public void removeById(int id) {
        super.removeById(id);
        saveFile();
    }

    abstract void loadFile();
    abstract void saveFile();

}
