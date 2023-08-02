public class Subtask extends Task {
    String epicName;
    int epicId;

    Subtask(String name, String description, int id, String epicName, int epicId) {
        super(name, description, id);
        this.epicName = epicName;
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask {Epic:" + epicName + ", Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + "}";
    }

}
