package Linkedlist;

public class CircularLL {
    private Node head; // Can also be managed via 'tail.next'
    private Node tail; // Reference to the last node
    private int size; // Optional: Keep track of the size

    public CircularLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null; // Or tail == null
    }

    // ------------------------- INSERTION OPERATIONS -------------------------

    // Insert at the beginning of the list
    public void insertFirst(int val) {
        Node newNode = new Node(val);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // Points to itself
        } else {
            newNode.next = head;
            tail.next = newNode; // Old tail now points to new head
            head = newNode; // New node becomes the head
        }
        size++;
    }

    // Insert at the end of the list
    public void insertLast(int val) {
        Node newNode = new Node(val);
        if (isEmpty()) {
            insertFirst(val); // If empty, inserting last is same as inserting first
        } else {
            newNode.next = head; // New node points to the head
            tail.next = newNode; // Old tail points to the new node
            tail = newNode; // New node becomes the tail
            size++;
        }
    }

    // ------------------------- DELETION OPERATIONS -------------------------

    // Delete the first node
    public int deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot delete from an empty list.");
        }

        int val = head.data; // Value to return

        if (head == tail) { // Only one node in the list
            head = null;
            tail = null;
        } else {
            head = head.next; // Move head to the next node
            tail.next = head; // Old tail (still current tail) now points to new head
        }
        size--;
        return val;
    }

    // Delete a node by its value (first occurrence)
    public int deleteByValue(int valToDelete) {
        if (isEmpty()) {
            throw new RuntimeException("Cannot delete from an empty list.");
        }

        Node node = head;
        // Handle deletion of the head node
        if (node.data == valToDelete) {
            return deleteFirst();
        }

        // Traverse to find the node and its previous node
        Node prev = null;
        do {
            prev = node;
            node = node.next;
            if (node.data == valToDelete) {
                break; // Found the node
            }
        } while (node != head); // Continue until we loop back to head

        if (node.data != valToDelete) { // Element not found in the list
            throw new RuntimeException("Value not found in the list: " + valToDelete);
        }

        // Adjust pointers
        prev.next = node.next;
        if (node == tail) { // If the deleted node was the tail
            tail = prev; // Update tail to the previous node
        }
        size--;
        return valToDelete;
    }


    // ------------------------- DISPLAY OPERATION -------------------------

    // Display the elements of the circular linked list
    public void display() {
        if (isEmpty()) {
            System.out.println("Circular Linked List: empty");
            return;
        }

        Node temp = head;
        System.out.print("Circular Linked List: ");
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head); // Loop until we come back to the head

        System.out.println(" (back to head)"); // Indicate the circular nature
    }

    public int getSize() {
        return size;
    }
}