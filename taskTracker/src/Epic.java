import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasks;

    Epic(String name, String description, int id) {
        super(name, description, id);
    }

    void checkEpicStatus(){
        boolean newTaskStatus = true;
        boolean doneTaskStatus = true;

        if (subtasks == null) {
            return;
        }

        for (Subtask task :subtasks) {
            if (!task.status.equals("NEW")) {
                newTaskStatus = false;
                break;
            }
        }

        for (Subtask task :subtasks) {
            if (!task.status.equals("DONE")) {
                doneTaskStatus = false;
                break;
            }
        }

        if (doneTaskStatus) status = "DONE";
        else if (newTaskStatus) status = "NEW";
        else status = "IN_PROGRESS";

    }
}
