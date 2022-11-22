package cn.weiyinfu.rubik.two.think;

import cn.weiyinfu.rubik.two.BackTracingSolver;

import java.util.List;

/**
 * 使用回溯法寻找公式
 * 使用tableSolver只能找到一个公式
 * 使用回溯法可以找到多个公式
 */
public class $2FindFormula {
public static void main(String[] args) {
    System.out.println("交换位置不改变状态");
    List<String> swapPostion = new BackTracingSolver().solve(new int[][]{
            {1, 0, 2, 3, 4, 5, 6, 7},
            {0, 0, 0, 0, 0, 0, 0, 0}
    });
    System.out.println(swapPostion.size());
    for (String s : swapPostion) {
        System.out.println(s);
    }
    System.out.println("以下两种操作等价");
    System.out.println("交换状态不改变位置");
    swapPostion = new BackTracingSolver().solve(new int[][]{
            {0, 1, 2, 3, 4, 5, 6, 7},
            {2, 2, 0, 0, 0, 0, 0, 0}}
    );
    System.out.println(swapPostion.size());
    for (String s : swapPostion) {
        System.out.println(s);
    }
    swapPostion = new BackTracingSolver().solve(new int[][]{
            {0, 1, 2, 3, 4, 5, 6, 7},
            {1, 1, 0, 0, 0, 0, 0, 0}}
    );
    System.out.println(swapPostion.size());
    for (String s : swapPostion) {
        System.out.println(s);
    }
}
}
