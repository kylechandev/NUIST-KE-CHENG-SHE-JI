package cn.weiyinfu.rubik.two;

import cn.weiyinfu.rubik.cube.Cube;

/**
 * 二阶魔方表示法
 */
public class Mini {
public int[] pos;
public int code;
public int state;
static final int[] pow = {1, 3, 9, 27, 81, 243, 729};
static final int[] factorial = {1, 1, 2, 6, 24, 120, 720};
static final String ops = "后左下";

private Mini() {

}

public static Mini getInitState() {
    //二阶魔方的初始状态
    Mini mini = new Mini();
    mini.pos = new int[7];
    for (int i = 0; i < 7; i++) {
        mini.pos[i] = i;
    }
    mini.state = 0;
    mini.code = 0;
    return mini;
}


public static Mini from(int[][] positionAndState) {
    int state = Mini.hashState(positionAndState[1]);
    return from(positionAndState[0], state, hash(positionAndState));
}

public static Mini from(int[] pos, int state, int code) {
    Mini mini = new Mini();
    mini.pos = pos;
    mini.state = state;
    mini.code = code;
    return mini;
}

public static Mini from(int code) {
    return Mini.from(unhash(code));
}

public static Mini from(String colors) {
    Cube cube = new Cube(colors).regular();
    // rywgob，每个颜色对应一种数字
    int value[] = {1, 5, 9, -5, 0, -3};
    //每个面对应的state
    int[] faceState = {2, 1, 0, 1, 2, 0};
    // the color index of 8 position
    int sword[][] = {{18, 6, 20}, {7, 10, 16}, {19, 21, 15}, {11, 14, 17}, {0, 4, 22}, {2, 5, 8},
            {1, 13, 23}, {3, 9, 12}};
    int a[][] = new int[2][8];
    for (int i = 0; i < 8; i++) {
        int id = 0;
        int state = 0;
        for (int j = 0; j < 3; j++) {
            int colorIndex = sword[i][j];
            int face = colorIndex / 4;
            int x = colorIndex % 4 / 2;
            int y = colorIndex % 4 % 2;
            char c = cube.colors[face][x][y];
            int index = "rywgob".indexOf(c);
            id += value[index];
            if (c == 'r' || c == 'o') {
                state = faceState[face];
            }
        }
        a[0][id] = i;
        a[1][id] = state;
    }
    return from(a);
}

//逆时针执行操作
public Mini goReverse(int op) {
    int state = this.state;
    int pos[] = new int[7];
    for (int i = 0; i < 7; i++) {
        int p = this.pos[i];
        //只移动op面上的小块
        if ((p & (1 << op)) == 0) {//p&(1<<op)表示该小块的位置会被op操作影响
            if (i < 6) {//最后一个小块的状态不需要考虑
                int s = (this.state / (int) Math.pow(3, i)) % 3;//求小块当前状态
                int ns = (6 - op - s) % 3;//op操作之后的状态
                state = state - (s - ns) * (int) Math.pow(3, i);//直接更改状态数值
            }
            int op1 = (op + 1) % 3, op2 = (op + 2) % 3;
            pos[i] = p & (1 << op) | (1 - ((p >> op1) & 1) << op2) | ((p >> op2) & 1) << op1;
        } else {
            pos[i] = this.pos[i];
        }
    }
    return from(pos, state, hashPosition(pos) * 729 + state);
}

//顺时针执行操作
public Mini go(int op) {
    return goReverse(op).goReverse(op).goReverse(op);
}

public Mini go(String operation) {
    Mini now = this;
    for (char o : operation.toCharArray()) {
        now = now.go(Mini.ops.indexOf(o));
    }
    return now;
}

int[][] to28() {
    return unhash(code);
}

// 状态哈希
public static int hashState(int[] state) {
    int ans = 0;
    for (int i = 0; i < 6; i++) {
        ans += pow[i] * state[i];
    }
    return ans;
}

public static int[] unhashState(int state) {
    int ans[] = new int[8];
    for (int i = 0; i < 6; i++) {
        ans[i] = state % 3;
        state /= 3;
    }
    ans[7] = 0;
    for (int i = 0; i < 3; i++) {
        ans[6] = i;
        if (Validator.validateState(ans) == null) break;
    }
    return ans;
}

// 位置哈希，全排列散列算法
public static int hashPosition(int[] position) {
    int ans = 0;
    for (int i = 0; i < 7; i++) {
        int k = 0;
        for (int j = i + 1; j < 7; j++) {
            if (position[j] < position[i]) {
                k++;
            }
        }
        ans += factorial[6 - i] * k;
    }
    return ans;
}

public static int[] unhashPosition(int position) {
    int ans[] = new int[8];
    boolean used[] = new boolean[8];
    for (int i = 0; i < 7; i++) {
        int v = position / factorial[6 - i];
        position -= v * factorial[6 - i];
        int cnt = 0;
        for (int j = 0; j <= 8; j++) {
            if (!used[j]) {
                if (cnt == v) {
                    used[j] = true;
                    ans[i] = j;
                    break;
                }
                cnt++;
            }
        }
    }
    ans[7] = 7;
    return ans;
}

public static int hash(int[][] node) {
    return Mini.hashPosition(node[0]) * 729 + Mini.hashState(node[1]);
}

public static int[][] unhash(int code) {
    return new int[][]{unhashPosition(code / 729), unhashState(code % 729)};
}

@Override
public boolean equals(Object obj) {
    Mini cube = (Mini) obj;
    for (int i = 0; i < 7; i++) {
        if (cube.pos[i] != pos[i]) return false;
    }
    if (cube.state == state) return true;
    return false;
}

MiniExtend extend() {
    return new MiniExtend(unhash(this.code));
}

@Override
public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 7; i++) builder.append(pos[i] + " ");
    builder.append("state=" + state);
    return builder.toString();
}


}
