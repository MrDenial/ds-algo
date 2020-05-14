package com.bytetube._13_recursion.hanoi;

public class Hanoi {
    public static void main(String[] args) {
        hanoi(4,"A","C","B");
    }

    /**
     * 当n == 1时，直接将盘子从A 移动到C
     * 当n > 1时，可以拆分成3大步骤
     * ① 将n – 1 个盘子从A 移动到B
     * ② 将编号为n 的盘子从A 移动到C
     * ③ 将n – 1 个盘子从B 移动到C
     * 步骤① ③ 明显是个递归调用
     * @param n
     * @param from
     * @param to
     * @param help
     */
    public static void hanoi(int n,String from,String to,String help){
        if (n == 1) {
            System.out.println("move "+1+" from "+from+" to "+to);
        }else {

            hanoi(n - 1, from, help, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            hanoi(n - 1, help, to, from);
        }

    }
}
