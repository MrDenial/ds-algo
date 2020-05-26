package com.bytetube._14_backTracking;

/**
 * 树，图--->结点作为我们研究对象的
 * 皇后问题是以row作为研究对象的
 */
public class Queens {
    public static void main(String[] args) {
            new Queens().placeQueens(4);
    }
    //数组的索引作为行号 数组value作为列号 cols[row] = col; cols[4] = 5表示当前在第四行的第五列摆放了一个皇后
    int[] cols;

    //一共有多少种摆法
    int ways;


    public void placeQueens(int n){
        if (n <1) {
            return;
        }
        cols = new int[n];
        place(0);

        System.out.println(n+"皇后一共有"+ways+"种摆法");

    }

    private void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < cols.length; col++) {
            if (isValid(row,col)) {
                cols[row] = col;
                place(row+1);//回溯
            }
            
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row ; i++) {
            if (cols[i] == col) {//列上有皇后
                System.out.println("["+row+"] [" +col+"]=false");
                return false;
            }
            //对角线上有皇后
            if ((row-i)== Math.abs(col-cols[i])) {
                System.out.println("["+row+"] [" +col+"]=false");
                return false;
            }
        }
        System.out.println("["+row+"] [" +col+"]=true");
        return true;
    }

    public void show(){
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col <cols.length ; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                }else {
                    System.out.print("0 ");
                }
            }
            System.out.println();

        }
        System.out.println("=================");
    }
}
