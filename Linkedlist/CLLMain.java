package Linkedlist;

public class CLLMain {
    public static void main(String[] args) {
        CircularLL list = new CircularLL();

        System.out.println("--- Testing insertFirst ---");
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(1);
        list.display(); // Expected: 1 -> 2 -> 3 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 3

        System.out.println("\n--- Testing insertLast ---");
        list.insertLast(4);
        list.insertLast(5);
        list.display(); // Expected: 1 -> 2 -> 3 -> 4 -> 5 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 5

        System.out.println("\n--- Testing deleteFirst ---");
        System.out.println("Deleted: " + list.deleteFirst()); // Should delete 1
        list.display(); // Expected: 2 -> 3 -> 4 -> 5 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 4

        System.out.println("Deleted: " + list.deleteFirst()); // Should delete 2
        list.display(); // Expected: 3 -> 4 -> 5 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 3

        System.out.println("\n--- Testing deleteByValue ---");
        System.out.println("Deleting value 4: " + list.deleteByValue(4));
        list.display(); // Expected: 3 -> 5 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 2

        System.out.println("Deleting value 5 (tail): " + list.deleteByValue(5));
        list.display(); // Expected: 3 -> (back to head)
        System.out.println("Size: " + list.getSize()); // Expected: 1

        System.out.println("Deleting value 3 (last remaining node): " + list.deleteByValue(3));
        list.display(); // Expected: empty
        System.out.println("Size: " + list.getSize()); // Expected: 0

        System.out.println("\n--- Testing on an empty list ---");
        list.display();
        try {
            list.deleteFirst();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        list.insertLast(100);
        list.display();
    }
}