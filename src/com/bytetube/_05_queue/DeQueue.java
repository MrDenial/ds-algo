package com.bytetube._05_queue;

import com.bytetube._05_queue.list.LinkedList;
import com.bytetube._05_queue.list.List;

public class DeQueue<E> {
    private List<E> list = new LinkedList<>();
    int size(){// 元素的数量
        return list.size();
    }
    boolean isEmpty(){ // 是否为空
         return list.isEmpty();
    }
     void clear(){//清 空
        list.clear();
    }
    void enQueueRear(E element){
        list.add(element);
    }

    void enQueueFront(E element){
        list.add(0,element);
    }
    E deQueueFront(){// 从队头出队
       return list.remove(0);
    }

    E deQueueRear(){// 从队尾出队
        return list.remove(list.size()-1);
    }

}
