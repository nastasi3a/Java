public class Task {
    String name;
    String description;
    int id;
    String status;
    Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.status = "NEW";
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task {Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + "}";
    }
}
