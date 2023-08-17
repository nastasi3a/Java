public class Node<T> {
    Node<T> prev;
    private final T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    T getData() {
        return data;
    }
}
