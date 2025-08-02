package Linkedlist;

// Definition for singly-linked list node
class ListNode83 {
    int val;
    ListNode next;

    ListNode83(int val) {
        this.val = val;
    }
}

// Solution class with method to delete duplicates
class Leetcode83 {
    public ListNode deleteDuplicates(ListNode node) {
        if (node == null) {
            return node;
        }
        ListNode head = node;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
}

// Main class to test the solution
public class Main83 {
    public static void main(String[] args) {
        // Creating a sorted linked list with duplicates: 1 -> 1 -> 2 -> 3 -> 3
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        // Applying the solution
        Leetcode83 solution = new Leetcode83();
        ListNode result = solution.deleteDuplicates(head);

        // Printing the result
        printList(result);
    }

    // Helper method to print linked list
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
    }
}
