public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.createEpic("Epic1", "This epic has 2 subtasks");
        manager.createEpic("Epic2", "This epic has 1 subtask");
        manager.createTask("Task1", "This is task nr 1");
        manager.createTask("Task2", "This is task nr 2");
        manager.addSubtaskToEpic(0, "Subtask 1", "This is first subtask in epic 1", "Epic1");
        manager.addSubtaskToEpic(0, "Subtask 2", "This is second subtask in epic 1", "Epic1");
        manager.addSubtaskToEpic(1, "Subtask 1", "This is first subtask in epic 2", "Epic2");
        //System.out.println();
        manager.showAllSubtasksInAllEpics();
        manager.showAllTasks();
    }
}