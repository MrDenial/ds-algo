package com.bytetube._03_list;

import com.bytetube._03_list.single.SingleLinkedList;

/**
 * java jdk LinkedList--->doubly list
 */
public class LinkedList<E>  extends AbstractList<E>{
    private Node<E> first;
    private Node<E> last;

    private static class Node<E>{
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev,E element,  Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        size=0;
        first=last = null;
    }


    /**
     * 通过index找到对应的node对象  linkedlist 内部的迭代器
     * @param index
     * @return
     */
    private Node<E> node(int index){
        rangeCheck(index);

        if (index < (size>>1)) {//left part
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {//tail insert
            Node<E> prev = last;
            Node newLast = new Node(prev, element, null);
            if (size == 0) {//first unit insert
                first = newLast;
                last = newLast;
            } else {
                prev.next = newLast;
                last = newLast;
            }
        } else {
            //普通插入
            Node<E> next = node(index);
            Node<E> prev = next.prev;//node(index-1);
            Node node = new Node(prev, element, next);
            next.prev = node;
            if (index == 0) {//头插
                first = node;
            }
            prev.next = node;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;//null
        Node<E> next = node.next;//null
        //1.普通
        //2.头删（size==0）
        //3.尾删
        if (index == 0) {
            first = next;
        }else {
            //普通删除
            prev.next = next;
        }
        //删除尾节点
        if (next == null) {
            last = prev;
        }else {
            //普通删除
            next.prev = prev;
        }

        size--;

        return node.element;
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

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element==null ) {
                    return i;
                }
                node = node.next;
            }
        }

        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (element.equals(node.element) ) {
                return i;
            }
            node = node.next;
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
