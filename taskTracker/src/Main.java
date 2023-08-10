public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        HistoryManager historyManager = Managers.getDefaultHistory();

        inMemoryTaskManager.createEpic("Epic1", "This epic has 2 subtasks"); //id: 0
        inMemoryTaskManager.createEpic("Epic2", "This epic has 1 subtask"); //id: 1
        inMemoryTaskManager.createTask("Task1", "This is task nr 1"); //id: 2
        inMemoryTaskManager.createTask("Task2", "This is task nr 2"); //id: 3
        inMemoryTaskManager.createSubtaskInEpic(0, "Subtask1", "This is first subtask in epic 1",
                "Epic1"); //id: 4
        inMemoryTaskManager.createSubtaskInEpic(0, "Subtask2", "This is second subtask in epic 1",
                "Epic1"); //id: 5
        inMemoryTaskManager.createSubtaskInEpic(1, "Subtask1", "This is first subtask in epic 2",
                "Epic2"); //id: 6
        inMemoryTaskManager.changeSubtaskStatus(0, 5, "DONE");
        inMemoryTaskManager.getEpic(0);
        inMemoryTaskManager.getEpic(1);
        //System.out.println(inMemoryTaskManager.history());
        inMemoryTaskManager.changeSubtaskStatus(1, 6, "IN_PROGRESS");
        inMemoryTaskManager.removeById(4);
        inMemoryTaskManager.removeEpic(0);
        inMemoryTaskManager.getTask(2);
        inMemoryTaskManager.getEpic(1);
        inMemoryTaskManager.getEpic(0); //Solved: NullPointerException
        inMemoryTaskManager.getTask(3);
        //System.out.println(inMemoryTaskManager.history());
    }
}