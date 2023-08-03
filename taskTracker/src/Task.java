import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected String status;
    Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.status = "NEW";
        this.id = id;
    }

    void changeTaskStatus(String status) {
        if (status.equals("DONE")) this.status = "DONE";
        else if (status.equals("IN_PROGRESS")) this.status = "IN_PROGRESS";
        else this.status = "NEW";
    }

    int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task { Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + " }";
    }
}
