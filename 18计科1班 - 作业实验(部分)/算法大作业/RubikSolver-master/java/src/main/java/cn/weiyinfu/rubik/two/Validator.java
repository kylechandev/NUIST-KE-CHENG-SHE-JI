package cn.weiyinfu.rubik.two;

/**
 * 二阶魔方是否有解验证器
 */
public class Validator {
//魔方两个方块之间的距离
static int dis(int i, int j) {
    int[] x = {i & 1, (i >> 1) & 1, (i >> 2) & 1};
    int[] y = {j & 1, (j >> 1) & 1, (j >> 2) & 1};
    int s = 0;
    for (int k = 0; k < 3; k++) {
        s += Math.abs(x[k] - y[k]);
    }
    return s;
}

static String validate(int[][] a) {
    boolean used[] = new boolean[8];
    for (int i = 0; i < 8; i++) {
        used[a[0][i]] = true;
    }
    for (int i = 0; i < 8; i++) {
        if (!used[i]) {
            return "缺少小方块";
        }
    }
    //下面校验状态是否有解
    return validateState(a[1]);
}

public static String validateState(int[] a) {
    int[] state = new int[8];
    for (int i = 0; i < 8; i++) state[i] = a[i];
    for (int i = 0; i < 7; i++) {
        over:
        while (state[i] != 0) {
            for (int j = i + 1; j < 8; j++) {
                if (dis(i, j) == 1) {
                    if (state[i] == state[j]) {
                        state[i] = state[j] = 0;
                    } else if (state[j] == 0) {
                        state[j] = 3 - state[j] - state[i];
                        state[i] = 0;
                    } else {
                        state[i] = 0;
                        state[j] = 3 - state[j];
                    }
                    break over;
                }
            }
        }
    }

    if (state[7] != 0) return "魔方状态错误";
    return null;
}

public static void main(String[] args) {
    String s = validateState(new int[]{0,2,2,0,2,0,0,0});
    System.out.println(s);
}
}