package Linkedlist;

public class Main82 {
    
    public static void main(String[] args) {
        ListNode82 head = new ListNode82(1);
        head.next = new ListNode82(2);
        head.next.next = new ListNode82(3);
        head.next.next.next = new ListNode82(3);
        head.next.next.next.next = new ListNode82(4);
        head.next.next.next.next.next = new ListNode82(4);
        head.next.next.next.next.next.next = new ListNode82(5);
        head.next.next.next.next.next.next.next = new ListNode82(6);



        Solution82LL solution = new Solution82LL();
        
        System.out.println("Original list:");
        printList(head);

        ListNode82 result = solution.deleteDuplicates(head);
        
        System.out.println("\nList after removing duplicates:");
        printList(result);
    }

    public static void printList(ListNode82 node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}

class ListNode82 {
    int val;
    ListNode82 next;
    
    ListNode82() {}
    
    ListNode82(int val) { 
        this.val = val; 
    }
    
    ListNode82(int val, ListNode82 next) { 
        this.val = val; 
        this.next = next; 
    }
}

class Solution82LL {
    public ListNode82 deleteDuplicates(ListNode82 head) {
        if (head == null) {
            return null;
        }

        ListNode82 dummy = new ListNode82(0, head);
        
        ListNode82 prev = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            
            head = head.next;
        }

        return dummy.next;
    }
}