public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // No cycle possible in an empty list or single-node list
        }

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Moves one step
            fast = fast.next.next;  // Moves two steps
            
            if (slow == fast) {     // Cycle detected
                break;
            }
        }

        // No cycle detected
        if (fast == null || fast.next == null) {
            return null;
        }

        // Step 2: Find cycle start
        slow = head; // Reset slow pointer to head
        while (slow != fast) {  
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Start of the cycle
    }
}
