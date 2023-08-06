import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int availableId;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;

    InMemoryTaskManager() {
        availableId = 0;
        tasks = new HashMap<>();
        epics = new HashMap<>();
    }

    @Override
    public void createTask(String name, String description) {
        tasks.put(availableId, new Task(name, description, availableId++));
    }

    @Override
    public void createEpic(String name, String description) {
        epics.put(availableId, new Epic(name, description, availableId++));
    }

    @Override
    public void createSubtaskInEpic(int epicId, String name, String description, String epicName) {
        Epic epic = epics.get(epicId);
        Subtask subtask = new Subtask(name, description, availableId++, epicName, epicId);
        epic.addSubtask(subtask);
    }

    @Override
    public void showSubtasksInEpic(int epicId) {
        Epic epic = epics.get(epicId);
            for (Subtask subtask : epic.getSubtasks()) {
                System.out.println(subtask);
            }

    }

    @Override
    public void showTasks() {
        for (Task task: tasks.values()) {
            System.out.println(task);
        }
    }

    @Override
    public void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus) {
        Epic epic = epics.get(epicId);
        ArrayList<Subtask> subtasks = epic.getSubtasks();
        for (Subtask subtask : subtasks) {
            if (subtask.getId() == subtaskId) {
                subtask.changeTaskStatus(newStatus);
            }
        }
    }

    @Override
    public void removeEpic(final int epicId) {
        epics.remove(epicId);
    }

    @Override
    public void removeById(final int id) {
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

    @Override
    public void getTaskById(final int id) {
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
