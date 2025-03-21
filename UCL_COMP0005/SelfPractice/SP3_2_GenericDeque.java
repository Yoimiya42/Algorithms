
public class SP3_2_GenericDeque {

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(42);
    }

}

class Deque<T> {

    private static class Node<T> {

        T value;
        Node<T> prev, next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> front, rear;
    private int size;

    public Deque() {
        size = 0;
        front = rear = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);

        if (isEmpty()) {
            front = rear = node;
        } else {
            node.next = front;
            front.prev = node;
            front = node;
        }
        size++;
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            front = rear = node;
        } else {
            rear.next = node;
            node.prev = rear;
            rear = node;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Deque.");
        }
        Node<T> temp = front;
        front = temp.next;

        if (front == null) {
            front = rear = null;
        } else {
            temp.prev = null;
        }
        size--;

        return temp.value;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Deque.");
        }

        Node<T> temp = rear;
        rear = temp.prev;
        if (rear == null) {
            front = rear = null;
        } else {
            rear.next = null;
        }
        size--;

        return temp.value;
    }
}
