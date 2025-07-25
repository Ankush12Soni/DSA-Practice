package Linkedlist;

public class DoublyLL {
    private Node head;

    public void insertFirst(int val) {
        Node node = new Node(val);
        node.next = head;
        node.prev = null;
        if (head != null) head.prev = node;
        head = node;
    }

    public void insertLast(int val) {
        Node node = new Node(val); // Create the new node
        node.next = null; // Its next will be null as it's the last

        if (head == null) {
            // If the list is empty, this new node becomes the head
            node.prev = null; // Its prev is also null
            head = node;
            return; // Exit the method
        }

        // Traverse to the last node
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        // 'last' is now pointing to the original last node
        last.next = node; // Old last node's next points to the new node
        node.prev = last; // New node's prev points to the old last node
    }

    public void display() {
        Node last = null;
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " -> ");
            last = node;
            node = node.next;
        }
        System.out.println("End");

        System.out.println("Print in Reverse");

        while (last != null) {
            System.out.print(last.data + " -> ");
            last = last.prev;

        }
        System.out.println();
    }
}
