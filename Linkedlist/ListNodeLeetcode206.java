package Linkedlist;

class ListNodeLeetcode206 {
    int val;
    ListNodeLeetcode206 next;

    ListNodeLeetcode206(int val) {
        this.val = val;
        this.next = null;
    }
}

class Solution {
    public ListNodeLeetcode206 reverseList(ListNodeLeetcode206 head) {
        ListNodeLeetcode206 prev = null;
        ListNodeLeetcode206 curr = head;

        while (curr != null) {
            ListNodeLeetcode206 nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    // Utility function to print the linked list
    public void printList(ListNodeLeetcode206 head) {
        ListNodeLeetcode206 temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    // Utility function to create a list from array
    public ListNodeLeetcode206 createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNodeLeetcode206 head = new ListNodeLeetcode206(arr[0]);
        ListNodeLeetcode206 current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNodeLeetcode206(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] values = {1, 2, 3, 4, 5};
        ListNodeLeetcode206 head = sol.createList(values);

        System.out.print("Original List: ");
        sol.printList(head);

        ListNodeLeetcode206 reversedHead = sol.reverseList(head);

        System.out.print("Reversed List: ");
        sol.printList(reversedHead);
    }
}
