package cn.weiyinfu.rubik.two;

public class MiniExtend {
//stateMapArray[op][当前状态]，表示状态会如何变化
final static int[][] stateMapArray = {{0, 2, 1}, {2, 1, 0}, {1, 0, 2}};
int[][] a;

MiniExtend(int[][] a) {
    this.a = a;
}

public MiniExtend goReverse(int op, boolean moveAll) {
    /**
     * op=2,表示下面
     * op=1，表示左面
     * op=0，表示后面
     * 逆时针旋转
     * */
    int pos[] = new int[8];
    int state[] = new int[8];
    for (int i = 0; i < 8; i++) {
        int p = a[0][i];
        int s = a[1][i];
        //只移动op面上的小块
        if (moveAll || (p & (1 << op)) == 0) {//p&(1<<op)表示该小块的位置会被op操作影响
            state[i] = stateMapArray[op][s];//op操作之后的状态
            int op1 = (op + 1) % 3, op2 = (op + 2) % 3;
            pos[i] = p & (1 << op) | (1 - ((p >> op1) & 1) << op2) | ((p >> op2) & 1) << op1;
        } else {
            pos[i] = p;
            state[i] = s;
        }
    }
    return new MiniExtend(new int[][]{pos, state});
}

MiniExtend go(int op, boolean moveAll) {
    return goReverse(op, moveAll).goReverse(op, moveAll).goReverse(op, moveAll);
}

MiniExtend go(String operations) {
    MiniExtend now = this;
    for (char o : operations.toCharArray()) {
        if (o == 'D') {
            now = now.go(1, true);
        } else if (o == 'R') {
            now = now.go(2, true);
        } else {
            now = now.go("后左下".indexOf(o), false);
        }
    }
    return now;
}
}
