import org.junit.jupiter.api.Test;

class EpicTest {
    @Test
    void checkEpicStatusTest() {
        Epic epic = new Epic("Epic1", "Epic without subtasks", 0);
        System.out.println(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Subtask in Epic1", 1, 0);
        Subtask subtask2 = new Subtask("Subtask2", "Subtask in Epic1", 2, 0);
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);
        System.out.println(epic);

        subtask1.changeTaskStatus("DONE");
        subtask2.changeTaskStatus("DONE");
        System.out.println(epic);

        subtask1.changeTaskStatus("NEW");
        subtask2.changeTaskStatus("DONE");
        System.out.println(epic);

        subtask1.changeTaskStatus("IN_PROGRESS");
        subtask2.changeTaskStatus("IN_PROGRESS");
        System.out.println(epic);

    }

}