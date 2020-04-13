package com.bytetube._04_stack;

import com.bytetube._04_stack.list.ArrayList;
import com.bytetube._04_stack.list.LinkedList;
import com.bytetube._04_stack.list.List;

public class Stack<E> {

    private List<E> list = new LinkedList<>();
    public void clear(){
        list.clear();
    }

    /**
     * size of the container
     * @return
     */
    public int size() {
        return list.size();
    }

    /**
     * container is empty or not
     * @return
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size()-1);
    }

    public E peek(){
        return list.get(list.size()-1);
    }



}
