package com.bytetube._12_heap.heap;

import com.bytetube._12_heap.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 二叉堆（最大堆）
 * @author dal
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;

	public BinaryHeap(E[] elements,Comparator<E> comparator) {
		super(comparator);
		//this.elements = elements;
		if (elements == null || elements.length==0) {
			this.elements = (E[])new Object[DEFAULT_CAPACITY];
		}else {
			size = elements.length;
			int capacity = Math.max(elements.length,DEFAULT_CAPACITY);
			this.elements = (E[])new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}

	public BinaryHeap(E[] elements) {
		this(elements,null);
	}
	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}

	public BinaryHeap(){
		this(null,null);
	}



	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size=0;
	}

	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("heap is empty!");
		}
	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity>=capacity) return;

		//每次扩容扩大1.5倍
		int newCapacity = oldCapacity+(oldCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;//address
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element can not be null!");
		}
	}

	private void siftUp(int index) {//上滤
		E e = elements[index];//80
		while (index>0){
			int pIndex = (index-1)>>1;
			E p = elements[pIndex];//43
			//比较
			if (compare(e,p)<=0) return;

			//swap 交换80 和 43
			E temp = elements[index];
			elements[index] = elements[pIndex];
			elements[pIndex] = temp;

			//重新赋值index
			index = pIndex;

		}
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size+1);
		elements[size++] = element;
		siftUp(size-1);

	}

	@Override
	public E remove() {
		emptyCheck();
		int lastIndex = --size;
		E root = elements[0];
		elements[0]= elements[lastIndex];
		elements[lastIndex] = null;
		siftDown(0);

		return root;
	}

	private void siftDown(int index) {
		E element = elements[index];
		int half = size>>1;

		//必须保证index位置上是非叶子结点
		while (index<half){
			//index只有2种可能
			//1。只有左孩子
			//2。左右孩子都有
			//默认左孩子跟index结点比较
			int childIndex = (index<<1)+1;
			E child = elements[childIndex];
			//右孩子
			int rightIndex = childIndex+1;
			//从左右孩子种选择出较大的孩子
			if (rightIndex<size && compare(elements[rightIndex],child) >0) {
				child = elements[childIndex=rightIndex];
			}
			//左右孩子都比parent小
			if(compare(element,child)>=0) break;

			elements[index] = child;

			//重置index的值
			index = childIndex;
		}
		elements[index] = element;
	}

	@Override
	public  E replace( E element) {
		elementNotNullCheck(element);
		E root = null;
		//case 1 heap is empty
		if (size == 0) {
			elements[0] = element;
			size++;
		}else {//case 2  heap is not empty
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}

		return root;
	}


	private void heapify(){
		//自上而下的上滤  O(nlogn)
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

		//自下而上的下滤 O(n)
		for (int i = (size>>1)-1; i >=0 ; i--) {
			siftDown(i);
		}
	}


























	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}


}
