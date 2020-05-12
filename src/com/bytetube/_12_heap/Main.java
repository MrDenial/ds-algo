package com.bytetube._12_heap;

import com.bytetube._12_heap.heap.BinaryHeap;
import com.bytetube._12_heap.printer.BinaryTrees;

import java.util.Comparator;

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

	static void test2() {
		Integer[] arr = {68,72,43,50,38,10,90,65};
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});

		BinaryTrees.println(binaryHeap);
	}

	static void test3() {
		BinaryHeap<Integer> minHeap = new BinaryHeap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		int k = 3;
		Integer[] arr = {68,72,43,50,38,10,90,65};
		for (int i = 0; i < arr.length; i++) {
			//先把数据中的前k个数加入到heap中
			if (minHeap.size() < k) {
				minHeap.add(arr[i]);
			}else if (arr[i]>minHeap.get()){
				minHeap.replace(arr[i]);
			}
		}
		BinaryTrees.println(minHeap);

	}



	public static void main(String[] args) {
		//test1();
		//test2();
		test3();
	}

}
