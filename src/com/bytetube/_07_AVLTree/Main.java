package com.bytetube._07_AVLTree;


import com.bytetube._07_AVLTree.printer.BinaryTrees;
import com.bytetube._07_AVLTree.tree.AVLTree;

@SuppressWarnings("unused")
public class Main {

	static void test1() {
		Integer data[] = new Integer[]{
				67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
		};

		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);

			System.out.println("====================================================================================================================");
			BinaryTrees.println(avl);
		}

		avl.remove(68);
		BinaryTrees.println(avl);
		avl.remove(76);
		BinaryTrees.println(avl);

	}

	public static void main(String[] args) {

		test1();
		
	}
}
