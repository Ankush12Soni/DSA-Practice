package Linkedlist;

class ListNode {
    int val;
    ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeSortedLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Recursive merge
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(", ");
            head = head.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4};
        int[] arr2 = {1, 3, 4};

        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);

        ListNode merged = mergeTwoLists(list1, list2);

        System.out.print("Merged List: ");
        printList(merged); // Output: [1, 1, 2, 3, 4, 4]
    }
}
