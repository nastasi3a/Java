public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new FileBackedTaskManager(Managers.getDefaultHistory());

        taskManager.createEpic("Epic1", "This epic has 2 subtasks"); //id: 0
        taskManager.createEpic("Epic2", "This epic has 1 subtask"); //id: 1
        taskManager.createTask("Task1", "This is task nr 1"); //id: 2
        taskManager.createTask("Task2", "This is task nr 2"); //id: 3
        taskManager.createSubtask(0, "Subtask1", "This is first subtask in epic 1"); //id: 4
        taskManager.createSubtask(0, "Subtask2", "This is second subtask in epic 1"); //id: 5
        taskManager.createSubtask(1, "Subtask1", "This is first subtask in epic 2"); //id: 6
        taskManager.changeSubtaskStatus(0, 5, "DONE");
        System.out.println(taskManager.getEpic(0));
        System.out.println(taskManager.getEpic(1));
        System.out.println(taskManager.getSubtask(4));
        System.out.println(taskManager.getHistory());
        taskManager.changeSubtaskStatus(1, 6, "IN_PROGRESS");
        taskManager.removeById(4);
        taskManager.removeEpic(0);
        System.out.println(taskManager.getTask(2));
        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getEpic(1));
        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getEpic(0));
        System.out.println(taskManager.getTask(3));
        System.out.println(taskManager.getSubtask(4));
        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getTask(3));
        System.out.println(taskManager.getHistory());
    }
}
