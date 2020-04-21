package com.bytetube._00_leetcode.list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 *
 */
public class _237_deleteNode {

    public void deleteNode(ListNode node) {
        if (node == null|| node.next==null)  return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
