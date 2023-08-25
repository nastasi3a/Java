public class Task {

    protected String name;
    protected String description;
    protected final Integer id;
    enum taskStatuses {
        NEW, IN_PROGRESS, DONE;

        @Override
        public String toString() {
            return super.toString().toLowerCase().replace('_', ' ');
        }
        public String superToString() {
            return super.toString();
        }
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

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }
}
