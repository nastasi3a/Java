import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest <T extends TaskManager>{

    TaskManager taskManager = new InMemoryTaskManager(Managers.getDefaultHistory());

    @Test
    void createTaskTest() {
        taskManager.createTask("Task1", " "); //id: 0
        Task task = taskManager.getTask(0);

        assertEquals(taskManager.getTask(0), task, "Tasks do not match");
        assertNotNull(taskManager.getTask(0), "Task was not found");
    }

    @Test
    void addTaskTest() {
        Task task = new Task("Task1", " ", 0);
        taskManager.addTask(task); //id: 0
        final Task savedTask = taskManager.getTask(0);

        assertNotNull(savedTask, "Task was not found");
        assertEquals(savedTask, task, "Tasks do not match");
    }

}
