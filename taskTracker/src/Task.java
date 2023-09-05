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

    public static String toString(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        String className = null;
        if(task.getClass() == Subtask.class) className = "Subtask";
        else if(task.getClass() == Epic.class) className = "Epic";
        else if(task.getClass() == Task.class) className = "Task";
        stringBuilder.append(task.id).append(";").append(className).append(";").append(task.name)
                .append(";").append(task.description).append(";").append(task.status.superToString());
        if (task.getClass() == Subtask.class) stringBuilder.append(";").append(((Subtask) task).epicId);
        stringBuilder.append("\n");

        return stringBuilder.toString();
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
