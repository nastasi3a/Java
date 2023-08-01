public class Subtask extends Task {
    String epic;
    int epicId;

    Subtask(String name, String description, int id, String epic, int epicId) {
        super(name, description, id);
        this.epic = epic;
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask {Epic:" + epic + ", Name: " + name + ", Description: " + description + ", Status: " + status + ", Id: " + id + "}";
    }

}
