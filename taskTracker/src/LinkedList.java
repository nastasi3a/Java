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
        } while (node != null);
        return arrayListOfData;
    }

    void removeNode(Node<T> node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> prevNode = node.prev;
            Node<T> nextNode = node.next;
            if (prevNode == null) {
                nextNode.prev = null;
                head = nextNode;
            } else if (nextNode == null) {
                prevNode.next = null;
                tail = prevNode;
            } else {
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
        }
    }

    Node<T> getTail() {
        return tail;
    }

    String getIdString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (head == null) {
            return null;
        }
        Node<T> node = head;
        do {
            stringBuilder.append(((Task) node.getData()).id).append(";");
            node = node.next;
        } while (node!= null);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
