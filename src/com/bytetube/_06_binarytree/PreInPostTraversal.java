package com.bytetube._06_binarytree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PreInPostTraversal {

    private static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) { this.value = value;}
    }

    public static void preOrderTraversalRecursion(Node head){
        if (head == null) {
            return;
        }
        System.out.println(head.value+" ");
        preOrderTraversalRecursion(head.left);
        preOrderTraversalRecursion(head.right);

    }

    public static void preOrderTraversalLoop(Node head){
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);

            while (!stack.isEmpty()){
                Node node = stack.pop();
                System.out.print(node.value+" ");
                if (node.right != null) {//有右先压右
                    stack.push(node.right);
                }
                if (node.left != null) {//有左再压左
                    stack.push(node.left);
                }
            }
        }

    }

    public static void InOrderTraversalRecursion(Node head){
        if (head == null) {
            return;
        }
        InOrderTraversalRecursion(head.left);
        System.out.println(head.value+" ");
        InOrderTraversalRecursion(head.right);

    }

    public static void InOrderTraversalLoop(Node head){
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty()|| head!=null){
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    Node node = stack.pop();
                    System.out.print(node.value+" ");
                    head = node.right;


                }

            }






        }
    }

    public static void postOrderTraversalRecursion(Node head){
        if (head == null) {
            return;
        }
        postOrderTraversalRecursion(head.left);
        postOrderTraversalRecursion(head.right);
        System.out.println(head.value+" ");
    }

    public static void postOrderTraversalLoop(Node head){
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                 head = stack1.pop();
                stack2.push(head);
                //System..println(node.value+" ");
                if (head.left != null) {//有右先压右
                    stack1.push(head.left);
                }
                if (head.right != null) {//有左再压左
                    stack1.push(head.right);
                }
            }

            while (!stack2.isEmpty()){
                head = stack2.pop();
                System.out.print(head.value+" ");
            }
        }
    }

    public static void levelOrderTraversalLoop(Node head){
        if (head != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                Node node = queue.poll();
                System.out.print(node.value+" ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }



        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println("preOrderTraversalLoop:");
        preOrderTraversalLoop(head);
        System.out.println();

        System.out.println("InOrderTraversalLoop:");
        InOrderTraversalLoop(head);
        System.out.println();

        System.out.println("postOrderTraversalLoop:");
        postOrderTraversalLoop(head);
        System.out.println();

        System.out.println("levelOrderTraversalLoop:");
        levelOrderTraversalLoop(head);
    }
}
