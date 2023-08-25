import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileBackedTaskManager extends InMemoryTaskManager {

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
    public void createSubtaskInEpic(int epicId, String name, String description) {
        super.createSubtaskInEpic(epicId, name, description);
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

    //incomplete implementation
    void loadFile(){
        System.out.println("load");
    }
    void saveFile() {
        try (Writer fileWriter = new FileWriter("taskTracker.csv", false)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < availableId; i++) {
                Task task = getById(i);
                if (task != null) {
                    String className = null;
                    if(task.getClass() == Subtask.class) className = "Subtask";
                    else if(task.getClass() == Epic.class) className = "Epic";
                    else if(task.getClass() == Task.class) className = "Task";
                    stringBuilder.append(task.id).append(";").append(className).append(";").append(task.name)
                            .append(";").append(task.description).append(";").append(task.status.superToString());
                    if (task.getClass() == Subtask.class) stringBuilder.append(";").append(((Subtask) task).epicId);
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("\n");
            stringBuilder.append(((InMemoryHistoryManager) history).getStringWithId());
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
