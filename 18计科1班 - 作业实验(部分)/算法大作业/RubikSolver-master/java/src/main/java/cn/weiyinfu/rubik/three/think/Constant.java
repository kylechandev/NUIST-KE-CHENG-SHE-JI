package cn.weiyinfu.rubik.three.think;

import cn.weiyinfu.rubik.cube.Cube;
import cn.weiyinfu.rubik.cube.OperationList;
import cn.weiyinfu.rubik.three.min2phase.Min2PhaseSolver;

import java.util.HashMap;
import java.util.Map;

/*
 * 三阶魔方中的守恒量，只考虑边块
 * 只考虑一个面的边块，一个面上有边块有4个，每个边块有2种状态
 * 位置排列有4!/4种（分母中的4表示旋转等价性），所以总共有6种位置排列方式
 * 一共有16种状态，总共有96种局面
 *
 * 这96种局面并非全部有解，只有24种有解
 *
 * 这24种有两个公式：交换相邻两块的位置
 * */
public class Constant {
Min2PhaseSolver solver = new Min2PhaseSolver();
//使用最左面的4个小块作为研究对象，分别对应上，右，下，左四个小块，每个小块有两种颜色，第一个数值表示外面，第2个数值表示左面
//int[][] four = new int[][]{{3, 10}, {21, 14}, {39, 16}};//四个小块的位置
int[][] four = new int[][]{{3, 10}, {7, 19}, {21, 14}};//一个角对应3个边块，以最前，最上，最左角为例

int fac(int n) {
    if (n == 0) return 1;
    return n * fac(n - 1);
}

void show(int[] a) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < a.length; i++) {
        builder.append(a[i]).append(',');
    }
    System.out.println(builder.toString());
}

int[][] permutation(int n) {
    //生成全排列
    int N = fac(n);
    int[][] a = new int[N][];
    a[0] = new int[n];
    for (int i = 0; i < n; i++) {
        a[0][i] = i;
    }
    for (int i = 1; i < N; i++) {
        a[i] = new int[n];
        int[] b = a[i];
        int[] last = a[i - 1];
        for (int j = 0; j < n; j++) {
            b[j] = last[j];
        }
        int badPos = -1;
        for (int j = n - 1; j > 0; j--) {
            if (b[j] > b[j - 1]) {
                badPos = j - 1;
                break;
            }
        }
        if (badPos == -1) {
            if (i != N - 1) throw new RuntimeException("error");
            break;
        }
        for (int j = badPos; j < n; j++) {
            if (j == n - 1 || b[j + 1] < b[badPos]) {
                int temp = b[j];
                b[j] = b[badPos];
                b[badPos] = temp;
                break;
            }
        }
        int sz = n - badPos;
        for (int j = 0; j < sz / 2; j++) {
            int temp = b[badPos + 1 + j];
            b[badPos + 1 + j] = b[n - 1 - j];
            b[n - 1 - j] = temp;
        }
    }
    return a;
}

void swap(int[] a, int i, int j) {
    //交换数组中的两个值
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

Map<String, String> go() {
    char[] a = new Cube(3).toCompactString().toCharArray();
    int[][] perm = permutation(3);
    Map<String, String> ans = new HashMap<>();
    int solvable = 0;//可解问题的个数
    for (var pos : perm) {
        int[][] fourth = new int[a.length][];
        for (int j = 0; j < 3; j++) {
            fourth[j] = four[pos[j]].clone();
        }
        for (int state = 0; state < 8; state++) {
            for (int j = 0; j < four.length; j++) {
                int reverse = state & (1 << j);
                if (reverse != 0) {
                    swap(fourth[j], 0, 1);
                }
            }
            char[] colors = a.clone();
            for (int i = 0; i < four.length; i++) {
                colors[four[i][0]] = a[fourth[i][0]];
                colors[four[i][1]] = a[fourth[i][1]];
            }
            var q = new String(colors);
            var solution = solver.solve(q);
            if (solution.startsWith("Err")) continue;
            solvable++;
            ans.put(q, solution);
        }
    }
    System.out.println("可解的问题个数" + solvable);
    return ans;
}

Constant() {
    var ans = go();
    System.out.println(ans.size());
    for (var i : ans.entrySet()) {
        var state = i.getKey();
        System.out.println(new Cube(state).toString());
        System.out.println(new OperationList(i.getValue()).toFormatString());
        System.out.println("=========");
    }
}

public static void main(String[] args) {
    new Constant();
}
}
