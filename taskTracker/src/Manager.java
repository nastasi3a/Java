import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int availableId;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;

    Manager() {
        availableId = 0;
        tasks = new HashMap<>();
        epics = new HashMap<>();
    }
    void createTask(String name, String description) {
        tasks.put(availableId, new Task(name, description, availableId++));
    }

    void createEpic(String name, String description) {
        epics.put(availableId, new Epic(name, description, availableId++));
    }

    void addSubtaskToEpic(int epicId, String name, String description, String epicName) {
        Epic epic = epics.get(epicId);
        Subtask subtask = new Subtask(name, description, availableId++, epicName, epicId);
        epic.addSubtask(subtask);
    }

    void showAllSubtasksInAllEpics() {
        for (Epic epic : epics.values()) {
            for (Subtask subtask: epic.getSubtasks()) {
                System.out.println(subtask);
            }
        }
    }

    void showAllTasks() {
        for (Task task: tasks.values()) {
            System.out.println(task);
        }
    }

    void changeSubtaskStatus(final int id, String status) {
        for (int epicId : epics.keySet()) {
            Epic epic = epics.get(epicId);
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == id) {
                    subtask.changeTaskStatus(status);
                }
            }
        }
    }

    void removeEpic(final int epicId) {
        epics.remove(epicId);
    }

    void removeById(final int id) {
        epics.remove(id);
        tasks.remove(id);
        for (int epicId: epics.keySet()) {
            Epic epic = epics.get(epicId);
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            subtasks.removeIf(subtask -> subtask.getId() == id);
            epic.setSubtasks(subtasks);
            epic.checkEpicStatus();
        }
    }
    void getTaskById(final int id) {
        if (tasks.containsKey(id)) System.out.println(tasks.get(id));
        if (epics.containsKey(id)) System.out.println(epics.get(id));
        for (Epic epic: epics.values()) {
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == id) System.out.println(subtask);
            }
        }
    }
}
