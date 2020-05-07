package com.bytetube._12_heap;

import com.bytetube._12_heap.heap.BinaryHeap;
import com.bytetube._12_heap.printer.BinaryTrees;

public class Main {
	
	static void test1() {
		Integer[] arr = new Integer[10];
		BinaryHeap<Integer> heap = new BinaryHeap<>(arr);
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);
		heap.add(10);
		heap.add(90);
		heap.add(65);
		BinaryTrees.println(heap);
		 heap.remove();
		 BinaryTrees.println(heap);

//		System.out.println(heap.replace(70));
//		BinaryTrees.println(heap);
	}
	


	public static void main(String[] args) {
		test1();
	}

}
