public class Subtask extends Task {
    protected final int epicId;

    Subtask(String name, String description, int id, int epicId) {
        super(name, description, id);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask { Epic id: " + epicId + ", Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + " }";
    }

}
