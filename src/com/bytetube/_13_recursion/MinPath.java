package com.bytetube._13_recursion;

public class MinPath {
    public static void main(String[] args) {
        int[][] matrix = {{3,1,0,2},{4,3,2,1},{5,2,1,0}};
        System.out.println(process(matrix,0,0));
    }

    public static int process(int[][] matrix,int i,int j){
        if (i == matrix.length-1 && j== matrix[0].length-1) {
            return  matrix[i][j];
        }

        //在最后一行
        if (i == matrix.length-1) {
            return matrix[i][j]+process(matrix,i,j+1);
        }

        //在最后一列
        if (j == matrix[0].length-1) {
            return matrix[i][j]+process(matrix,i+1,j);
        }

        //普通位置
        int right = process(matrix,i,j+1);

        int down = process(matrix,i+1,j);

        return matrix[i][j] + Math.min(right,down);
    }
}
