public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new FileBackedTaskManager(Managers.getDefaultHistory());

        taskManager.changeSubtaskStatus(0, 5, "DONE");
        System.out.println(taskManager.getSubtask(4));
        taskManager.changeSubtaskStatus(1, 6, "NEW");
        System.out.println(taskManager.getHistory());
        taskManager.changeSubtaskStatus(1, 6, "IN_PROGRESS");
        taskManager.removeById(4);
        taskManager.removeEpic(0);
        System.out.println(taskManager.getHistory());
    }
}
