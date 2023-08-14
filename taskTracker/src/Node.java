public class Node {
    Node prev;
    private final Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.prev = null;
        this.next = null;
    }

    Task getData() {
        return task;
    }
}
