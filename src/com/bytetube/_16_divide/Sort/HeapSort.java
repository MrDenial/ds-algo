package com.bytetube._16_divide.Sort;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {10,3,6,4,8,1};
        heapSort(arr);
    }

    /*1.把无序数组重建为一个maxHeap（max，min）
      2.交换堆顶元素和数组的最后一个元素，把当前这个堆顶元素剔除
      3.再大根堆化（再次恢复大根堆），重复2的操作
     */
    public static void heapSort(int[] arr){
    //1.把无序数组重建为一个maxHeap（max，min）
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int size = arr.length;
        //2.交换堆顶元素和数组的最后一个元素，把当前这个堆顶元素剔除
        swap(arr,0, --size);

        //3.再大根堆化（再次恢复大根堆），重复2的操作
        while (size > 0){
            heapify(arr, 0 , size);
            swap(arr,0, --size);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }
    //把无序数组重建为一个maxHeap（max，min）

    /*
     * index
     * parent:index-1/2
     */
    private static void heapInsert(int[] arr, int index) {//sift up
        while (arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }

    }
    //再大根堆化（再次恢复大根堆）
    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size){
            int largest = left + 1 < size && arr[left+1]>arr[left]?left+1:left;
            largest = arr[largest]>arr[index]?largest:index;
            if (largest == index) {
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index*2+1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]  = temp;
    }
}
