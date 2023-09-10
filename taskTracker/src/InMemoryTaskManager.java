import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryTaskManager implements TaskManager {
    HistoryManager history;
    protected int availableId;
    protected final HashMap<Integer, Task> tasks;
    protected final HashMap<Integer, Epic> epics;

    InMemoryTaskManager(HistoryManager history) {
        availableId = 0;
        tasks = new HashMap<>();
        epics = new HashMap<>();
        this.history = history;
    }

    @Override
    public void createTask(String name, String description) {
        tasks.put(availableId, new Task(name, description, availableId++));
    }

    @Override
    public void addTask(Task task) {
        if (task.id<availableId) {
            System.out.println("this id is taken. creating the same task with another id");
            Task newTask = new Task(task.name, task.description, availableId);
            tasks.put(availableId++, newTask);
        } else {
            tasks.put(task.id, task);
            availableId = task.id + 1;
        }
    }

    @Override
    public void createEpic(String name, String description) {
        epics.put(availableId, new Epic(name, description, availableId++));
    }

    @Override public void addEpic(Epic epic) {
        if (epic.id<availableId) {
            System.out.println("this id is taken. creating the same epic with another id");
            Epic newEpic = new Epic(epic.name, epic.description, availableId);
            newEpic.setSubtasks(epic.getSubtasks());
            epics.put(availableId++, newEpic);
        } else {
            tasks.put(epic.id, epic);
            availableId = epic.id + 1;
        }
    }

    @Override
    public void createSubtask(int epicId, String name, String description) {
        Epic epic = epics.get(epicId);
        if (epic !=null) {
            Subtask subtask = new Subtask(name, description, availableId++, epicId);
            epic.addSubtask(subtask);
        } else System.out.println("There is no epic with id " + epicId + ".");
    }

    @Override
    public void addSubtask(Subtask subtask) {
        if(!epics.containsKey(subtask.epicId))
            System.out.println("No epic with this id");
        else {
            if (subtask.id<availableId) {
                System.out.println("this id is taken. creating the same subtask with another id");
                Subtask newSubtask = new Subtask(subtask.name, subtask.description, availableId++, subtask.epicId);
                epics.get(subtask.epicId).addSubtask(newSubtask);
            } else {
                epics.get(subtask.epicId).addSubtask(subtask);
                availableId = subtask.id + 1;
            }
        }

    }

    @Override
    public Optional<Task> getTask(final int id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            history.add(tasks.get(id));
            return Optional.of(task);
        } else System.out.println("There is no task with id " + id + ".");
        return Optional.empty();
    }

    @Override
    public Subtask getSubtask(final int id) {
        for (Epic epic : epics.values()) {
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == id) {
                    history.add(subtask);
                    return subtask;
                }
            }
            System.out.println("There is no subtask with id " + id + ".");
        } return null;
    }

    @Override
    public Epic getEpic(final int id) {
        if (epics.containsKey(id)) {
            history.add(epics.get(id));
            return epics.get(id);
        } else System.out.println("There is no epic with id " + id + ".");
        return null;
    }

    @Override
    public void changeTaskStatus(final int taskId, String newStatus) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.changeTaskStatus(newStatus);
        }
    }

    @Override
    public void changeSubtaskStatus(int epicId, final int subtaskId, String newStatus) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == subtaskId) {
                    subtask.changeTaskStatus(newStatus);
                }
            }
        } else System.out.println("There is no epic with id " + epicId + ".");
    }

    @Override
    public void removeEpic(final int epicId) {
        if (epics.get(epicId) == null) return;
        for (Subtask subtask: epics.get(epicId).getSubtasks()) {
            history.remove(subtask.getId());
        }
        history.remove(epicId);
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
        history.remove(id);
    }

    protected Task getById(final int id) {
        Task task;
        task = epics.get(id);
        if (task != null) {
            return task;
        }
        task = tasks.get(id);
        if (task != null) {
            return task;
        }
        boolean isFound = false;
        for (int epicId : epics.keySet()) {
            Epic epic = epics.get(epicId);
            ArrayList<Subtask> subtasks = epic.getSubtasks();
            for (Subtask subtask : subtasks) {
                if (subtask.getId() == id) {
                    task = subtask;
                    isFound = true;
                    break;
                }
            }
            if (isFound) break;
        }
        return task;
    }

    public List<Task> getHistory() {
        return history.getHistory();
    }

    static String toString(HistoryManager history) {
        return ((InMemoryHistoryManager) history).getStringWithId();
    }

    static List<Integer> fromString(String value) {
        if (value == null || value.equals("") || value.equals("null")) return null;
        List<Integer> list = new ArrayList<>();
        String[] arrayOfStringNumbers = value.split(";");
        for (String stringNumber : arrayOfStringNumbers) {
            try {
                list.add(Integer.parseInt(stringNumber));
            } catch (Exception e) {
                return null;
            }
        }
        return list;
    }

}