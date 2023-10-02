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

    @Test
    void createEpicTest() {
        taskManager.createEpic("Epic1", "Simple description");
        Optional<Epic> epic = taskManager.getEpic(0);

        assertEquals(taskManager.getEpic(0), epic, "Epics do not match");
        assertNotNull(taskManager.getEpic(0), "Epic was not found");
    }

    @Test
    void addEpicTest() {
        Epic epic = new Epic("Epic1", "Simple description", 0);
        taskManager.addEpic(epic); //id: 0
        final Optional<Epic> savedEpic = taskManager.getEpic(0);

        assertNotNull(savedEpic, "Epic was not found");
        savedEpic.ifPresent(value -> assertEquals(value, epic, "Epics do not match"));
    }

    @Test
    void createSubtaskTest() {
        taskManager.createEpic("Epic1", "Simple description");
        taskManager.createSubtask(0, "Subtask1", "Simple description");
        Optional<Subtask> subtask = taskManager.getSubtask(1);

        assertEquals(taskManager.getSubtask(1), subtask, "Subtasks do not match");
        assertNotNull(taskManager.getSubtask(1), "Subtask was not found");
    }

    @Test
    void addSubtaskTest() {
        Epic epic = new Epic("Epic1", "Simple description", 0);
        Subtask subtask = new Subtask("Subtask1", "Simple description",1, 0);
        taskManager.addEpic(epic); //id: 0
        taskManager.addSubtask(subtask);
        final Optional<Subtask> savedSubtask = taskManager.getSubtask(1);

        assertNotNull(savedSubtask, "Subtask was not found");
        savedSubtask.ifPresent(value -> assertEquals(value, subtask, "Subtasks do not match"));
        System.out.println(savedSubtask);
        Subtask savedSubtaskGotten = savedSubtask.get();
        savedSubtaskGotten.status = Task.taskStatuses.IN_PROGRESS;
        System.out.println(taskManager.getEpic(0));
    }

    @Test
    void getTaskTest() {
        taskManager.createTask("Task", "Simple description"); //id: 0
        Optional<Task> task = taskManager.getTask(0);
        assertEquals(taskManager.getTask(0), task, "Tasks do not match");
        assertNotNull(taskManager.getTask(0), "Task was not found");

        Task task1 = new Task("Task1", "Simple description", 1);
        taskManager.addTask(task1); //id: 1
        final Optional<Task> savedTask = taskManager.getTask(1);
        assertNotNull(savedTask, "Task was not found");
        savedTask.ifPresent(value -> assertEquals(value, task1, "Tasks do not match"));

        assertEquals(taskManager.getTask(2), Optional.empty());
    }

    @Test
    void getSubtaskTest() {
        taskManager.createEpic("Epic1", "Simple description"); //id: 0
        taskManager.createSubtask(0, "Subtask1", "Simple description"); //id: 1
        Optional<Subtask> subtask = taskManager.getSubtask(1);
        assertEquals(taskManager.getSubtask(1), subtask, "Subtasks do not match");
        assertNotNull(taskManager.getSubtask(1), "Subtask was not found");

        Subtask subtask2 = new Subtask("Subtask2", "Simple description",2, 0); //id: 2
        taskManager.addSubtask(subtask2);
        final Optional<Subtask> savedSubtask = taskManager.getSubtask(2);
        assertNotNull(savedSubtask, "Subtask was not found");
        savedSubtask.ifPresent(value -> assertEquals(value, subtask2, "Subtasks do not match"));
        
        assertEquals(taskManager.getSubtask(8), Optional.empty());
    }

    @Test
    void getEpicTest() {
        taskManager.createEpic("Epic", "Simple description"); //id: 0
        Optional<Epic> epic = taskManager.getEpic(0);
        assertEquals(taskManager.getEpic(0), epic, "Epics do not match");
        assertNotNull(taskManager.getEpic(0), "Epic was not found");

        Epic epic1 = new Epic("Epic2", "Simple description", 1); //id: 1
        taskManager.addEpic(epic1);
        final Optional<Epic> savedEpic = taskManager.getEpic(1);
        assertNotNull(savedEpic, "Epic was not found");
        savedEpic.ifPresent(value -> assertEquals(value, epic1, "Epics do not match"));

        taskManager.removeEpic(1);
        assertEquals(taskManager.getEpic(1), Optional.empty());
    }

    @Test
    void changeTaskStatusTest() {
        taskManager.createTask("Task1", "Simple description"); //id: 0
        Task task1 = taskManager.getTask(0).get();
        Task task2 = new Task("Task2", "Simple description", 1); //id: 1
        taskManager.addTask(task2);

        taskManager.changeTaskStatus(0, "DOME");
        assertEquals(taskManager.getTask(0).get(), task1);

        taskManager.changeTaskStatus(1, "IN_PROGRESS");
        assertEquals(taskManager.getTask(1).get(), task2);

        taskManager.removeById(1);
        taskManager.changeTaskStatus(1, "DONE");
    }

    @Test
    void changeSubtaskStatusTest() {
        taskManager.createEpic("Epic1", "Simple description"); //id: 0
        taskManager.createSubtask(0, "Subtask1", "Simple description"); //id: 1
        taskManager.changeSubtaskStatus(0, 1, "DONE");
        assertNotNull(taskManager.getSubtask(1), "Subtask was not found");

        Subtask subtask = new Subtask("Subtask", "Simple description",2, 0); //id: 2
        taskManager.addSubtask(subtask);
        taskManager.changeSubtaskStatus(0, 2, "IN_PROGRESS");
        assertEquals(subtask, taskManager.getSubtask(2).get(), "Subtasks do not match");

        taskManager.changeSubtaskStatus(1, 2, "DONE");
        taskManager.changeSubtaskStatus(0, 9, "DONE");
    }

    @Test
    void removeEpicTest() {
        taskManager.createEpic("Epic1", "Simple description"); //id: 0
        taskManager.createSubtask(0, "Subtask1", "Simple description"); //id: 1
        taskManager.removeEpic(0);
        assertEquals(taskManager.getSubtask(1), Optional.empty());
        assertEquals(taskManager.getEpic(0), Optional.empty());

    }

}
