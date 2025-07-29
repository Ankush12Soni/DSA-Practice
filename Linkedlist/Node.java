package Linkedlist;

public class Node {
    public int data;
    public Node next;
    public Node prev;

    // Constructor for singly linked list
    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    // Constructor for singly linked list with next
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
        this.prev = null;
    }

    // Constructor for doubly linked list with next and prev
    public Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
