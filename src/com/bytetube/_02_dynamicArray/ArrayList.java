package com.bytetube._02_dynamicArray;

import java.util.Arrays;


@SuppressWarnings("unchecked")
public class ArrayList<E> {

    private E[] elements;//arraylist底层封装的数组

    private int size;//当前数组中的有效元素个数

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capacity){//-2 10
        capacity = (capacity<DEFAULT_CAPACITY)?DEFAULT_CAPACITY:capacity;
        elements = (E[]) new Object[capacity];
    }

    public ArrayList(){//capacity = 10
        this(DEFAULT_CAPACITY);
    }

    /**
     * clear all elements
     */
    public void clear() {
//        for (int i = 0; i < size; i++) {
//            elements[i] = null;
//        }
        size = 0;
    }

    /**
     * size of the container
     * @return
     */
    public int size() { return size;}

    /**
     * container is empty or not
     * @return
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * add element to tail
     * @param element
     */
    public void add(E element) {
        add(size,element);
    }

    /**
     * insert element in certain index
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size+1);
        for (int i = size; i >index ; i--) {
            elements[i] = elements[i-1];
        }
        elements[index] = element;
        size++;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity>=size+1) return;

        //每次扩容扩大1.5倍
        int newCapacity = oldCapacity+(oldCapacity>>1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;//address
    }

    private void rangeCheckForAdd(int index) {
        if (index <0 || index>size) {
            outBounds(index);
        }
    }

    private void outBounds(int index) {
        throw new IndexOutOfBoundsException("index:"+index+",size:"+size);
    }

    /**
     * remove element in certain index
     * @param index
     * @return previous element
     */
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];

        for (int i = index+1; i < size; i++) {
            elements[i-1]  = elements[i];//覆盖
        }
        elements[--size] = null;
        return old;
    }

    private void rangeCheck(int index) {
        if (index <0 || index>=size) {
            outBounds(index);
        }
    }


    /**
     *get certain element according to index
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * set element in certain index
     * @param index
     * @param element
     * @return previous element
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }
    /**
     * index of certain element
     * @param element
     * @return
     */
    public int indexOf(E element) {//返回第一个是这个元素的下标
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }

            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
            return ELEMENT_NOT_FOUND;
        }



    /**
     * whether contain certain element in container
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element)!= ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
//      //size = 3,[11, 12, 13]
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            builder.append(elements[i]);
            if (i!=size-1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}

