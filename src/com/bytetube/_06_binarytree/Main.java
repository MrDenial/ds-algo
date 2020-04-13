package com.bytetube._06_binarytree;

import java.util.Comparator;


import com.bytetube._06_binarytree.printer.BinaryTrees;

public class Main {

    private static class PersonComparator implements Comparator<Person> {
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
        //bst.remove(12);
        System.out.println("=========================================");
        BinaryTrees.println(bst);
        System.out.println(bst.isComplete());
        //bst.remove(7);
//        System.out.println("=========================================");
//        BinaryTrees.println(bst);
//        System.out.println(bst.contains(7));
//        System.out.println(bst.height2());
        //System.out.println(bst.node(12));
    }


  public  static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        bst.preOrder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 2 ? true : false;
            }
        });
        System.out.println();

        bst.InOrder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.PostOrder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return element == 4 ? true : false;
            }
        });
        System.out.println();



    }


    public static void main(String[] args) {

        test2();
    }
}
