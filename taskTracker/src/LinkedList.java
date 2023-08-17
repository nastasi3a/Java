import java.util.ArrayList;

public class LinkedList<T> {

    Node<T> head;
    Node<T> tail;

    LinkedList(){
        head = null;
        tail = null;
    }

    void linkLast(T data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
        } else {
            Node<T> node = new Node<>(data);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    ArrayList<T> getArrayListOfData() {
        ArrayList<T> arrayListOfData = new ArrayList<>();
        if (head == null) {
            return null;
        }
        Node<T> node = head;
        do {
            arrayListOfData.add(node.getData());
            node = node.next;
        } while (node.next != null);
        arrayListOfData.add(node.getData());
        return arrayListOfData;
    }

    void removeNode(Node<T> node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> prevNode = node.prev;
            Node<T> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    Node<T> getTail() {
        return tail;
    }
}
