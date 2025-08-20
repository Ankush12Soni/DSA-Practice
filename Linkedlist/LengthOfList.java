package Linkedlist;

public class LengthOfList {
    // Method to find the length of cycle in a linked list
    public int findCycleLength(ListNodeLOL head) {
        ListNodeLOL slow = head;
        ListNodeLOL fast = head;

        // Phase 1: Detect if cycle exists using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Phase 2: Find the length of the cycle
                ListNodeLOL temp = slow;
                int length = 0;

                do {
                    temp = temp.next;
                    length++;
                } while (temp != slow);

                return length;
            }
        }

        // No cycle found
        return 0;
    }
}

// Node class
class ListNodeLOL {
    int val;
    ListNodeLOL next;

    ListNodeLOL(int val) {
        this.val = val;
    }

    ListNodeLOL(int val, ListNodeLOL next) {
        this.val = val;
        this.next = next;
    }
}

// Driver class
class Driver {
    public static void main(String[] args) {
        // Create nodes
        ListNodeLOL node1 = new ListNodeLOL(1);
        ListNodeLOL node2 = new ListNodeLOL(2);
        ListNodeLOL node3 = new ListNodeLOL(3);
        ListNodeLOL node4 = new ListNodeLOL(4);
        ListNodeLOL node5 = new ListNodeLOL(5);

        // Connect nodes: 1 -> 2 -> 3 -> 4 -> 5
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Create a cycle: 5 -> 3
        node5.next = node3;

        // Test the cycle length
        LengthOfList obj = new LengthOfList();
        int cycleLength = obj.findCycleLength(node1);

        if (cycleLength > 0) {
            System.out.println("Cycle detected with length: " + cycleLength);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
