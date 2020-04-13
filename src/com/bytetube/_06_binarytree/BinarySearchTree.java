package com.bytetube._06_binarytree;

import com.bytetube._06_binarytree.printer.BinaryTreeInfo;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;


	public BinarySearchTree() {
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

		public boolean isLeaf(){
			return this.left==null && this.right== null;
		}


		@Override
		public String toString() {

			return String.valueOf(this.element);
		}
	}

	public static abstract class Visitor<E>{
		boolean stop;

		public abstract boolean visit(E elment);
	}

	public void preOrder(Visitor<E> visitor){
		if (visitor==null) {
			return;
		}
		preOrder(root,visitor);
	}

	public void preOrder(Node<E> node,Visitor<E> visitor){
		if (node == null||visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);
		preOrder(node.left,visitor);
		preOrder(node.right,visitor);

	}

	public void InOrder(Visitor<E> visitor){
		if (visitor==null) {
			return;
		}
		InOrder(root,visitor);
	}

	public void InOrder(Node<E> node,Visitor<E> visitor){
		if (node == null||visitor.stop) {
			return;
		}
		//System.out.println(node.element);

		InOrder(node.left,visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);
		InOrder(node.right,visitor);

	}

	public void PostOrder(Visitor<E> visitor){
		if (visitor==null) {
			return;
		}
		PostOrder(root,visitor);
	}

	public void PostOrder(Node<E> node,Visitor<E> visitor){
		if (node == null||visitor.stop) {
			return;
		}
		//System.out.println(node.element);
		PostOrder(node.left,visitor);
		PostOrder(node.right,visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visit(node.element);

	}


	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size==0;
	}


	public void clear(){
		root = null;
		size = 0;
	}

	public Node<E> node(E element){
		Node<E> node = root;
		int cmp = 0;
		while (node!=null) {//可以向下寻找
			cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {//right
				node = node.right;
			} else if (cmp < 0) {//left
				node = node.left;
			}
		}

		return null;

	}

	public boolean contains(E element){
		return node(element)!=null;

	}

	public void remove(E element){
		remove(node(element));
	}

	private void remove(Node<E> node){
		if (node == null)  return;
		size--;
		//degree = 2
		if (node.left!= null && node.right!=null) {//degree = 2
			Node<E> s = successor(node);
			node.element = s.element;
			node = s;
		}
		Node<E> replacement = node.left!=null?node.left:node.right;

		//degree = 1
		if (replacement != null) { //node degree = 1
			//update parent
			replacement.parent = node.parent;
			//更新parent child
			if (node.parent == null) {//node degree = 1 node = root
				root = replacement;
			}else if (node == node.parent.left) {
				node.parent.left = replacement;
			}else {
				node.parent.right = replacement;
			}
		}
		//degree = 0
		else if (node.parent == null) {//degree = 0 并且是root
			root = null;
		}else {//node degree=0 not root
			if (node == node.parent.left) {
				node.parent.left = null;
			}else {
				node.parent.right = null;
			}
		}

	}









	public void add(E element){
		elementNotNullCheck(element);
		//1.添加第一个节点
		if (root == null) {
			root = new Node<>(element,null);
			size++;
			return;
		}
		//2.添加的不是第一个节点
		//先找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		while (node!=null) {//找到新节点的插入位置
			 cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {//right
				node = node.right;
			} else if (cmp < 0) {//left
				node = node.left;
			} else {//equals
				node.element = element;
				return;
			}
		}
		Node<E> newNode = new Node<>(element,parent);
		if (cmp > 0) {
			parent.right = newNode;
		}else {
			parent.left = newNode;
		}

		size++;

	}

	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1,e2);
		}

		return ((Comparable<E>)e1).compareTo(e2);//class implements Comparable

	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw  new IllegalArgumentException("element must not be null!");
		}
	}

	public Node<E> predecessor(Node<E> node){
		if (node == null) return null;
		//1.left.right.right.right...
		Node<E> p = node.left;
		while (p!=null){
			while (p.right!=null){
				p = p.right;
			}

			return p;
		}

		//2.
		while (node.parent!=null && node == node.parent.left){
			node = node.parent;
		}
		return node.parent;

	}


	public Node<E> successor(Node<E> node){
		if (node == null) return null;
		//1.right.left.left.left...
		Node<E> p = node.right;
		while (p!=null){
			while (p.left!=null){
				p = p.left;
			}

			return p;
		}
		//2.
		while (node.parent!=null && node == node.parent.right){
			node = node.parent;
		}
		return node.parent;

	}

	public int height(){
		return height(root);
	}

	public int height(Node<E> node){
		if (node == null) {
			return 0;
		}

		 return 1+Math.max(height(node.left),height(node.right));//tail recursion

	}

	public int height2(){
		if (root == null) {
			return 0;
		}

		int height = 0;

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int levelSize = 1;
		while (!queue.isEmpty()){
			Node<E> node = queue.poll();
			levelSize--;

			if (node.left != null) {
				queue.offer(node.left);
			}

			if (node.right != null) {
				queue.offer(node.right);
			}

			if(levelSize==0){
				levelSize = queue.size();
				height++;
			}
		}

		return height;

	}

	public boolean isComplete(){
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;//叶节点模式
		while (!queue.isEmpty()){

			Node node = queue.poll();
			if (leaf && !(node.left==null && node.right== null)) {//开启叶节点模式
				return false;
			}
			if (node.left!=null && node.right!=null) {//case 1
				queue.offer(node.left);
				queue.offer(node.right);
			}

			else if (node.left == null && node.right!=null) {//case 2
				return false;
			}else {
				leaf = true;
			}
		}
			return true;
	}


	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}

	private void toString(Node<E> node, StringBuilder sb, String prefix) {//中序打印
		if (node == null) return;

		toString(node.left, sb, prefix + "L---");
		sb.append(prefix).append(node.element).append("\n");
		toString(node.right, sb, prefix + "R---");
	}
}
