Class Task is the simplest element, and the base class for Epic and Subtask. A task has a name, a status, a unique id, and a description.

Class Epic extends class Task. Epic contains an array of subtasks. The status of Epic is based on the statuses of its subtasks. 

Class Subtask extends class Task. Subtask has additional attributes: the name of its Epic, the id of its Epic.

Interface TaskManager encompasses method declarations that delineate the program's main functionalities.

Class InMemoryTaskManager implements the "TaskManager" interface and stores all data in memory.

Class FileBackedTaskManager extends InMemoryTaskManager with methods to load data from .csv file and to save data in .csv file.

Interface HistoryManager encompasses method declarations that delineate the history's main functionalities.

Class InMemoryHistoryManager implements the "HistoryManager" interface.

Classes LinkedList and Node are my own implementation of linked list.

Class Managers have static method to return object of TaskManager.

EpicTest contains tests for Epic methods.

TaskManagerTest contains tests for TaskManager methods.
