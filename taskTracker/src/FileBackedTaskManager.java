import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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

    void loadFile()  {
        try {
            String readTaskTrackerFile = Files.readString(Path.of("taskTracker.csv"));
            String[] splitTaskTrackerFile = readTaskTrackerFile.split("\n");

            //don't go through empty line and line with history
            for (int i = 0; i < splitTaskTrackerFile.length-2; i++) {
                String[] line = splitTaskTrackerFile[i].split(";");
                availableId = Integer.parseInt(line[0]);
                switch (line[1]) {
                    case "Epic" -> createEpic(line[2], line[3]);
                    case "Task" -> {
                        createTask(line[2], line[3]);
                        changeTaskStatus(Integer.parseInt(line[0]), line[4]);
                    }
                    case "Subtask" -> {
                        createSubtaskInEpic(Integer.parseInt(line[5]), line[2], line[3]);
                        changeSubtaskStatus(Integer.parseInt(line[5]), Integer.parseInt(line[0]), line[4]);
                    }
                    default -> System.out.println("Something went wrong");
                }
            }

            String[] lineWithHistory = splitTaskTrackerFile[splitTaskTrackerFile.length-1].split(";");
            for (String stringId :lineWithHistory) {
                history.add(getById(Integer.parseInt(stringId)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
