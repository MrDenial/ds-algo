package com.bytetube._00_leetcode.list;

public class _141_hasCycle {
    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast!=null&& fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;//null.next
            if (slow == fast) {//address
                return true;
            }

        }
        return false;

    }
}
