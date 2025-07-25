package Linkedlist;

public class DllMain {
    public static void main(String[] args) {
        DoublyLL list = new DoublyLL();
        list.insertFirst(5);
        list.insertFirst(7);
        list.insertFirst(8);
        list.insertFirst(9);
        list.display();

        list.insertLast(25);
        System.out.println("inserting at Last");
        list.display();
    }
}
