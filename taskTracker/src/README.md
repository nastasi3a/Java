Task is the simplest element, the base class for Epic and Subtask. It has a name, status, unique id, and description.
Epic extends Task. Epic contains an array of subtasks. The status of Epic is based on the statuses of its subtasks.
Subtask extends Task. Subtask has additional attributes: the name of its Epic, the id of its Epic.
