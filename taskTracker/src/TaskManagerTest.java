import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest <T extends TaskManager>{
    TaskManager taskManager = new InMemoryTaskManager(Managers.getDefaultHistory());
    @Test
    void createTaskTest()
    {
        Task task = new Task("Task1", " ", 0);
        taskManager.addTask(task); //id: 0
        final Task savedTask = taskManager.getTask(0);

        assertNotNull(savedTask, "Task was not found");
        assertEquals(savedTask, task, "Tasks do not match");
    }

}
