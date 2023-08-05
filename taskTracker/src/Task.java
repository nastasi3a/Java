public class Task {

    protected String name;
    protected String description;
    protected final int id;
    enum taskStatuses {
        NEW, IN_PROGRESS, DONE
    }
    protected taskStatuses status;

    Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.status = taskStatuses.NEW;
        this.id = id;
    }

    void changeTaskStatus(String status) {
        if (status.equals("DONE")) this.status = taskStatuses.DONE;
        else if (status.equals("IN_PROGRESS")) this.status = taskStatuses.IN_PROGRESS;
        else this.status = taskStatuses.NEW;
    }

    int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task { Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + " }";
    }
}
