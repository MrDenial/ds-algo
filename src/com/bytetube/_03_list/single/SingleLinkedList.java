package com.bytetube._03_list.single;

import com.bytetube._03_list.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {
        private Node<E> first;

    private static class Node<E>{//内部类  private static
        E element;//值域
        Node<E> next;//指针域
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        size=0;
        first = null;
    }

    /**
     * 通过index找到对应的node对象  linkedlist 内部的迭代器
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;

    }

    @Override
    public E get(int index) {
        return node(index).element;
    }



    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    /**
     * value操作：java赋值操作是从右向左的
     * 指向操作：从左向右看
     * eg.
     * a->b a = b;
     * b->a b = a;
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            first = new Node(element,first);
        }else {
            //普通位置的添加
            Node<E> prev = node(index - 1);
            prev.next = new Node(element, prev.next);
        }
        size++;
    }
    @Override
    public E remove(int index) {
        Node<E>  old = first;
        rangeCheck(index);
        if (index == 0) {
            first = first.next;

        }else {
            //普通位置的删除
            Node<E> prev = node(index-1);
            old = prev.next;
            prev.next = old.next;
        }
        size--;
        return old.element;
    }





    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        }else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    @Override
    public String toString() {
//      //size = 3,[11, 12, 13]
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            builder.append(node.element);
            node = node.next;
            if (i!=size-1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
