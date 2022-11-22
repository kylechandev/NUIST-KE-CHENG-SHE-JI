package cn.weiyinfu.rubik.two;

import cn.weiyinfu.rubik.cube.DownAndRight;
import cn.weiyinfu.rubik.cube.FormulaTransformer;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

//两公式求解器

/**
 * 向下移动相当于对整体执行左旋操作=1
 * 向右移动相当于对整体执行下旋操作=2
 */
public class TwoFormulaSolver extends TwoSolver {
final static String swapPosition = "下后下下后左后后左下左后后后左";
final static String swapState = "后后左后下下后后下左后左左左下左下下";
//12条边如何整体移动
final static Map<Integer, Integer> bigMoveMap = new TreeMap<>();
final static int USE_POSITION = 0;
final static int USE_STATE = 1;
final static String rule[] = {swapPosition, swapState};

//两个小块之间的距离
int dis(int x, int y) {
    int[] one = {x & 1, (x >> 1) & 1, (x >> 2) & 1}, two = {y & 1, (y >> 1) & 1, (y >> 2) & 1};
    int s = 0;
    for (int i = 0; i < one.length; i++) {
        s += Math.abs(one[i] - two[i]);
    }
    return s;
}

static {
    buildBigMoveTable();
}

static void buildBigMoveTable() {
    for (int i = 0; i < 24; i++) {
        MiniExtend mini = Mini.from(0).extend();
        for (int operation = DownAndRight.ans[i]; operation > 1; operation >>= 1) {
            mini = mini.go((operation & 1) == 0 ? 1 : 2, true);
        }
        int zero = 0, one = 0;
        for (int j = 0; j < 8; j++) {
            if (mini.a[0][j] == 0) {
                zero = j;
            } else if (mini.a[0][j] == 1) {
                one = j;
            }
        }
        if (zero < one) {
            bigMoveMap.put(zero * 10 + one, DownAndRight.ans[i]);
        }
    }
}

//获取当前应该进行的大操作，应该进行交换的两个方块必然是相邻的
int[] findBigOp(int[][] a) {
    for (int i = 0; i < 8; i++) {
        if (a[0][i] != i) {
            for (int j = i + 1; j < 8; j++) {
                if (dis(a[0][i], a[0][j]) == 1 && dis(a[0][i], i) > dis(a[0][j], i)) {
                    return new int[]{a[0][i], a[0][j], USE_POSITION};
                }
            }
        } else if (a[1][i] != 0) {
            for (int j = i + 1; j < 8; j++) {
                if (dis(a[0][i], a[0][j]) == 1) {
                    return new int[]{a[0][i], a[0][j], USE_STATE};
                }
            }
        }
    }
    return null;
}

public String getAns(MiniExtend a) {
    StringBuilder builder = new StringBuilder();
    while (true) {
        int bigOpTuple[] = findBigOp(a.a);
        if (bigOpTuple == null) break;
        int op = bigMoveMap.get(Math.min(bigOpTuple[0], bigOpTuple[1]) * 10 + Math.max(bigOpTuple[0], bigOpTuple[1]));
        int reverseOp = 1;
        for (int i = op; i > 1; i >>= 1) {
            a = a.go((i & 1) == 0 ? 1 : 2, true);
            reverseOp = reverseOp << 1 | (i & 1);
            builder.append("DR".charAt(i & 1));
        }
        for (char o : rule[bigOpTuple[2]].toCharArray()) {
            a = a.go(Mini.ops.indexOf(o), false);
            builder.append(o);
        }
        for (int i = reverseOp; i > 1; i >>= 1) {
            a = a.goReverse((i & 1) == 0 ? 1 : 2, true);
            for (int j = 0; j < 3; j++) {
                builder.append("DR".charAt(i & 1));
            }
        }
    }
    return FormulaTransformer.eliminateDR(builder.toString(),getN());
}


@Override
public String getAns(String input) {
    MiniExtend a = Mini.from(input).extend();
    return getAns(a);
}

static void averageSteps() {
    Random random = new Random();
    int s = 0;
    int testCase = 1000;
    for (int i = 0; i < testCase; i++) {
        Mini mini = Mini.from(random.nextInt(TableGenerator.a.length));
        String ans = new TwoFormulaSolver().getAns(mini.extend());
        s += ans.length();
    }
    System.out.println(s / testCase);
}

public static void main(String[] args) {
    averageSteps();
}
}
