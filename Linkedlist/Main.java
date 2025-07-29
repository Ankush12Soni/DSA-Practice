package Linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.printList(); // 10 -> 20 -> 30 -> null

        list.insertAtStart(5);
        list.printList(); // 5 -> 10 -> 20 -> 30 -> null

        list.insertAtPosition(2, 15);
        list.printList(); // 5 -> 10 -> 15 -> 20 -> 30 -> null

        list.insertUsingRec(99, 3); // Insert 99 at index 3 recursively
        list.printList(); // 5 -> 10 -> 15 -> 99 -> 20 -> 30 -> null

        list.deleteFromStart();
        list.printList(); // 10 -> 15 -> 99 -> 20 -> 30 -> null

        list.deleteFromEnd();
        list.printList(); // 10 -> 15 -> 99 -> 20 -> null

        list.deleteAtPosition(1);
        list.printList(); // 10 -> 99 -> 20 -> null
    }
}
