package Linkedlist;

public class LinkedList {
    Node head;

    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void insertAtPosition(int index, int data) {
        if (index == 0) {
            insertAtStart(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Index out of bounds");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void deleteFromStart() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        head = head.next;
    }

    public void deleteFromEnd() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        current.next = null;
    }

    public void deleteAtPosition(int index) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        Node current = head;

        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current == null || current.next == null) {
            System.out.println("Index out of bounds");
            return;
        }

        current.next = current.next.next;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
