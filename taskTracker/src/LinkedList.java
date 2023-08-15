import java.util.ArrayList;

public class LinkedList {

    Node head;
    Node tail;

    LinkedList(){
        head = null;
        tail = null;
    }

    void linkLast(Task task) {
        if (head == null) {
            head = new Node(task);
            tail = head;
        } else {
            Node node = new Node(task);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    ArrayList<Task> getTasks() {
        ArrayList<Task> arrayListOfTasks = new ArrayList<>();
        if (head == null) {
            return null;
        }
        Node node = head;
        do {
            arrayListOfTasks.add(node.getData());
            node = node.next;
        } while (node.next != null);
        arrayListOfTasks.add(node.getData());
        return arrayListOfTasks;
    }

    void removeNode(Node node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    Node getTail() {
        return tail;
    }
}
