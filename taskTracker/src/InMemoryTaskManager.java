import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    static InMemoryHistoryManager history = new InMemoryHistoryManager();
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
    public void getTask(final int id) {
        if (tasks.containsKey(id)) {
            System.out.println(tasks.get(id));
            history.add(tasks.get(id));
        } else System.out.println("There is no task with id " + id + ".");
    }

    @Override
    public void getSubtask(final int id) {
        boolean isFound = false;
        for (Epic epic : epics.values()) {
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == id) {
                    System.out.println(subtask);
                    history.add(subtask);
                    isFound = true;
                    break;
                }
            }
            if (isFound) break;
        }
        if (!isFound) System.out.println("There is no subtask with id " + id + ".");
    }

    @Override
    public void getEpic(final int id) {
        if (epics.containsKey(id)) {
            System.out.println(epics.get(id));
            history.add(epics.get(id));
        } else System.out.println("There is no epic with id " + id + ".");
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
        for (int epicId : epics.keySet()) {
            Epic epic = epics.get(epicId);
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            subtasks.removeIf(subtask -> subtask.getId() == id);
            epic.setSubtasks(subtasks);
            epic.checkEpicStatus();
        }
    }
}
