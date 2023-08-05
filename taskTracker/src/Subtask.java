public class Subtask extends Task {
    private final String epicName;
    private final int epicId;

    Subtask(String name, String description, int id, String epicName, int epicId) {
        super(name, description, id);
        this.epicName = epicName;
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask { Epic: " + epicName + " (id: " + epicId + "), Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + " }";
    }

}
