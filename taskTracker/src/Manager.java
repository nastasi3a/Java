import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Manager {
    int availableId = 0;
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;


    void showAllSubtasksInAllEpics() {
        for (Epic epic : epics.values()) {
            for (Subtask subtask: epic.subtasks) {
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
