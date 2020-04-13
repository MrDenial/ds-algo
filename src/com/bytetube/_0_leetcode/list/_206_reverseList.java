package com.bytetube._0_leetcode.list;

import com.bytetube._0_leetcode.list.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class _206_reverseList {
    //head->5-4-3-2-1
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null)
            return head;//head.next==null 当前list中只有一个node、还有一种情况，就是我们在做递归操作的时候，来到了最小的单元
        ListNode newHead = reverseList1(head.next);//head.next->4-3-2-1 newHead->1-2-3-4-null
        head.next.next = head;//4->5
        head.next = null;//5->null
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }
}