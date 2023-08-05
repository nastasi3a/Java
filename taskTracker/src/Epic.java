import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks = new ArrayList<>();

    Epic(String name, String description, int id) {
        super(name, description, id);
    }

    ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
    void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    void checkEpicStatus() {
        boolean newTaskStatus = true;
        boolean doneTaskStatus = true;

        if (subtasks == null) {
            return;
        }

        for (Subtask task : subtasks) {
            if (task.status != taskStatuses.NEW) {
                newTaskStatus = false;
                break;
            }
        }

        for (Subtask task : subtasks) {
            if (task.status != taskStatuses.DONE) {
                doneTaskStatus = false;
                break;
            }
        }

        if (doneTaskStatus) status = taskStatuses.DONE;
        else if (newTaskStatus) status = taskStatuses.NEW;
        else status = taskStatuses.IN_PROGRESS;

    }

    @Override
    public String toString() {
        StringBuilder namesOfSubtasks = new StringBuilder();
        for (Subtask subtask : subtasks) namesOfSubtasks.append(subtask.name).append(" ");
        return "Epic { Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id
                + ", Subtasks: " + namesOfSubtasks + "}";
    }

}