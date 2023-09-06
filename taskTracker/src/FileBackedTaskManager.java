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
    public void createSubtask(int epicId, String name, String description) {
        super.createSubtask(epicId, name, description);
        saveFile();
    }

    @Override
    public Task getTask(int id) {
        Task task = super.getTask(id);
        saveFile();
        return task;
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = super.getSubtask(id);
        saveFile();
        return subtask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = super.getEpic(id);
        saveFile();
        return epic;
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

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        saveFile();
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        saveFile();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        saveFile();
    }

    void loadFile()  {
        try {
            String readTaskTrackerFile = Files.readString(Path.of("taskTracker.csv"));
            String[] splitTaskTrackerFile = readTaskTrackerFile.split("\n");

            //don't go through empty line and line with history
            for (int i = 0; i < splitTaskTrackerFile.length-2; i++) {
                createTaskFromString(splitTaskTrackerFile[i]);

            }

            String[] lineWithHistory = splitTaskTrackerFile[splitTaskTrackerFile.length-1].split(";");
            if (!lineWithHistory[0].equals(""))
                for (String stringId : lineWithHistory) {
                    history.add(getById(Integer.parseInt(stringId)));
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void createTaskFromString(String taskString) {
        String[] line = taskString.split(";");
        availableId = Integer.parseInt(line[0]);
        switch (line[1]) {
            case "Epic" -> createEpic(line[2], line[3]);
            case "Task" -> {
                createTask(line[2], line[3]);
                changeTaskStatus(Integer.parseInt(line[0]), line[4]);
            }
            case "Subtask" -> {
                this.createSubtask(Integer.parseInt(line[5]), line[2], line[3]);
                changeSubtaskStatus(Integer.parseInt(line[5]), Integer.parseInt(line[0]), line[4]);
            }
            default ->
                System.out.println("Something went wrong");
        }
    }

    void saveFile() {
        try (Writer fileWriter = new FileWriter("taskTracker.csv", false)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < availableId; i++) {
                Task task = getById(i);
                if (task != null) {
                    stringBuilder.append(Task.toString(task));
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
