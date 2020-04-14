package com.bytetube._09_Set;

import com.bytetube._09_Set.Times.Task;
import com.bytetube._09_Set.file.FileInfo;
import com.bytetube._09_Set.file.Files;
import com.bytetube._09_Set.set.ListSet;
import com.bytetube._09_Set.set.Set;
import com.bytetube._09_Set.set.Set.Visitor;
import com.bytetube._09_Set.set.TreeSet;

public class Main {

    static void test1() {

//        Set<Integer> listSet = new ListSet<>();
//        listSet.add(10);
//        listSet.add(11);
//        listSet.add(11);
//        listSet.add(12);
//        listSet.add(10);
//
//		listSet.traversal(new Visitor<Integer>() {
//			@Override
//			public boolean visit(Integer element) {
//				System.out.println(element);
//				return false;
//			}
//		});
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(7);
        treeSet.add(11);
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(9);

        treeSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }


    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }

    static void test2() {
        FileInfo fileInfo = Files.read("/Users/dalluo/Desktop/src/java/util",
                new String[]{"java"});

        System.out.println("number of files：" + fileInfo.getFiles());
        System.out.println("lines of code：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("number of words：" + words.length);

		Times.test("ListSet", new Task() {
			public void execute() {
				testSet(new ListSet<>(), words);
			}
		});

        Times.test("TreeSet", new Task() {
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });

    }

    public static void main(String[] args) {
        test2();
    }

}
