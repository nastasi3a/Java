import java.util.HashMap;

public class Manager {
    protected int availableId = 0;
    protected HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Epic> epics;

    void createTask(String name, String description) {
        tasks.put(availableId, new Task(name, description, availableId++));
    }

    void createEpic(String name, String description) {
        epics.put(availableId, new Epic(name, description, availableId++));
    }

    void addSubtaskToEpic(int epicId, String name, String description, String epicName) {
        Epic epic = epics.get(epicId);
        Subtask subtask = new Subtask(name, description, availableId++, epicName, epicId);
        epic.addTaskToSubtasks(subtask);
    }
    void showAllSubtasksInAllEpics() {
        for (Epic epic : epics.values()) {
            for (Subtask subtask: epic.getSubtasks()) {
                System.out.println(subtask);
            }
        }
    }

    void removeAllSubtasksFromEpic(int epicId) {
        epics.remove(epicId);
    }

    void removeById(int id) {
        epics.remove(id);
        tasks.remove(id);
    }
    void getTaskById(int id) {
        if (tasks.containsKey(id)) System.out.println(tasks.get(id));
        if (epics.containsKey(id)) System.out.println(epics.get(id));
    }
}
