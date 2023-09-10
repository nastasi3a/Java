import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest <T extends TaskManager>{

    TaskManager taskManager = new InMemoryTaskManager(Managers.getDefaultHistory());

    @Test
    void createTaskTest() {
        taskManager.createTask("Task1", " "); //id: 0
        Optional<Task> task = taskManager.getTask(0);

        assertEquals(taskManager.getTask(0), task, "Tasks do not match");
        assertNotNull(taskManager.getTask(0), "Task was not found");
    }

    @Test
    void addTaskTest() {
        Task task = new Task("Task1", " ", 0);
        taskManager.addTask(task); //id: 0
        final Optional<Task> savedTask = taskManager.getTask(0);

        assertNotNull(savedTask, "Task was not found");
        savedTask.ifPresent(value -> assertEquals(value, task, "Tasks do not match"));
    }

}
