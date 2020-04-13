package com.bytetube._07_AVLTree.tree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {

	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}


	private static class AVLNode<E> extends Node<E>{
		int height = 1;
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}


		public int balanceFactor(){//Node--->AVLNode
			int leftHeight = left == null?0:((AVLNode<E>)left).height;
			int rightHeight = right == null?0:((AVLNode<E>)right).height;
			return leftHeight-rightHeight;
		}
		//to do
		public Node tallerChild() {
			int leftHeight = left == null?0:((AVLNode<E>)left).height;
			int rightHeight = right == null?0:((AVLNode<E>)right).height;
			if (leftHeight > rightHeight) {
				return left;
			}
			if (leftHeight < rightHeight) {
				return right;
			}
			return this.isLeftChild()?left:right;
		}

		public void updateHeight() {
			int leftHeight = left == null?0:((AVLNode<E>)left).height;
			int rightHeight = right == null?0:((AVLNode<E>)right).height;
			height = Math.max(leftHeight,rightHeight)+1;
		}

		@Override
		public String toString() {
		String parentS = "null";
			if (parent != null) {
				parentS = parent.element.toString();

			}

			return element+"_p("+parentS+")_h("+height+")";

		}


	}

	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	}

	public void afterAdd(Node<E> node){
		while ((node = node.parent)!=null){//root
			if (isBalanced(node)) {//如果平衡
				updateHeight(node);//更新高度
			}else {
				reBalcane2(node);
				break;
			}

		}
	}

	private boolean isBalanced(Node<E> node) {

		return Math.abs(((AVLNode<E>)node).balanceFactor())<=1;
	}

	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}

	private void reBalcane(Node<E> grand) {
		Node parent =  ((AVLNode<E>)grand).tallerChild();
		Node node =  ((AVLNode<E>)parent).tallerChild();
		if (parent.isLeftChild()) {//L
			if (node.isLeftChild()) {//LL
				rotateRight(grand);
			}else {//LR
				rotateLeft(parent);
				rotateRight(grand);
			}

		}else {//R
			if (node.isLeftChild()) {//RL
				rotateRight(parent);
				rotateLeft(grand);
			}else {//RR
				rotateLeft(grand);
			}

		}

	}

	private void rotateLeft(Node grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;//T1
		grand.right = child;
		parent.left = grand;

		afterRotate(grand,parent,child);

	}

	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;//T1
		grand.left = child;
		parent.right = grand;

		afterRotate(grand,parent,child);
	}

	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		//更新parent
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		}else if (grand.isRightChild()){
			grand.parent.right = parent;
		}else {//grand root
			root = parent;
		}

		//更新child parent
		if (child != null) {
			child.parent = grand;
		}

		//update grand parent
		grand.parent = parent;
		//更新g，p的高度（有先后顺序）
		updateHeight(grand);
		updateHeight(parent);
	}

	private void reBalcane2(Node<E> grand) {
		Node parent =  ((AVLNode<E>)grand).tallerChild();
		Node node =  ((AVLNode<E>)parent).tallerChild();
		if (parent.isLeftChild()) {//L
			if (node.isLeftChild()) {//LL
				rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
			}else {//LR
				rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
			}

		}else {//R
			if (node.isLeftChild()) {//RL
				rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
			}else {//RR
				rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
			}

		}

	}
	private void rotate(
			Node<E> r,//子树的根节点
			Node<E> a,
			Node<E> b,
			Node<E> c,
			Node<E> d,
			Node<E> e,
			Node<E> f,
			Node<E> g){

		//更新d
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		}
		else if (r.isRightChild()) {
			r.parent.right = d;
		}else {
			root = d;
		}

		//更新d的left a-b-c
		b.left = a;
		if (a != null) {
			a.parent = b;
		}

		b.right = c;
		if (c != null) {
			c.parent = b;
		}

		updateHeight(b);

		//更新d的right e-f-g
		f.left = e;
		if (e != null) {
			e.parent = f;
		}

		f.right = g;
		if (g != null) {
			g.parent = f;
		}

		updateHeight(f);


		//更新d b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;

		updateHeight(d);


	}

	public void afterRemove(Node<E> node){//afterAdd()非常相似
		while ((node = node.parent)!=null){
			//if balance update height
			if(isBalanced(node)){
				updateHeight(node);
			}else {
				reBalcane(node);
			}

		}

	}

}
