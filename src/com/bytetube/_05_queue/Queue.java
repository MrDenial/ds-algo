package com.bytetube._05_queue;

import com.bytetube._05_queue.list.LinkedList;
import com.bytetube._05_queue.list.List;

public class Queue<E> {
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

    public void enQueue(E element){
        list.add(element);
    }

    public E deQueue(){
        return list.remove(0);
    }

    public E front(){
        return list.get(0);
    }

}
