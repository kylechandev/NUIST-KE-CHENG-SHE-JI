package cn.weiyinfu.rubik.cube;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 一阶魔方放正
 * 小正方体整体旋转
 * 一个大图包含24个结点，每个结点和其它点有2个边
 * 每个结点都是等价的
 */
//将一个正方体变成"红心白发蓝右臂"的形式,只执行向下/向右两种操作
public class DownAndRight {
// opsite[i] is the opsite face of the ith face,6个面的对面
static int opsite[] = {4, 3, 5, 1, 0, 2};
// down operation and right operation
//f[op][face]表示face执行op操作之后的结果
static int f[][] = {{5, 1, 0, 3, 2, 4}, {0, 5, 1, 2, 4, 3}};
// 0 means down,1 means right，ans[state]表示一个操作序列
public static int[] ans = new int[24];
static int graph[][] = new int[24][2];

// 打印图结构
static void initGraph() {
    for (int i = 0; i < 24; i++) {
        for (int j = 0; j < 2; j++) {
            int next = goReverse(j, i);
            graph[next][j] = i;
        }
    }
}

static int reload(int x, int y) {
    int cnt = 0;
    for (int i = 0; i < 6; i++) {
        if (i == x || i == opsite[x]) continue;
        if (cnt == y) return i;
        cnt++;
    }
    throw new RuntimeException("impossible " + x + " " + y);
}

static int compress(int x, int y) {
    int cnt = 0;
    for (int i = 0; i < y; i++) {
        if (i == x || i == opsite[x]) continue;
        cnt++;
    }
    return cnt;
}

//0 1 2 3 4 5
//对state执行op操作,state有24种取值,op有两种取值:op=0表示向下，op=1表示向右
static int goReverse(int op, int state) {
    int x = state / 4, y = state % 4;
    y = reload(x, y);
    return state(f[op][x], f[op][y]);
}

static int go(int op, int state) {
    return goReverse(op, goReverse(op, goReverse(op, state)));
}

/**
 * 用x，y两个数字构造一个0~24之间的数值，表示状态
 * <p>
 * 对于红色，有6种选择，红色一旦确定方向，蓝色只有4种方向可以选择
 * 其中不能选择的两种颜色是红色及红色的对面：橙色
 * 所以对剩余的四种方向从大到小排序，y值即表示蓝色对应的方向
 */
static int state(int x, int y) {
    return x * 4 + compress(x, y);
}

static String tos(int state) {
    int x = state / 4, y = state % 4;
    y = reload(x, y);
    return String.format("(%d,%d)", x, y);
}

static void showGraph() {
    initGraph();
    for (int i = 0; i < 24; i++) {
        System.out.printf("%s %s %s\n", tos(i), tos(graph[i][0]), tos(graph[i][1]));
    }
}


//在构造函数中计算全部答案
static {
    Queue<Integer> q = new ArrayDeque<Integer>();
    int initState = state(2, 3);//一开始红色指向2面，蓝色在3面
    ans[initState] = 1;
    q.add(initState);
    while (!q.isEmpty()) {
        int now = q.poll();
        for (int op = 0; op < 2; op++) {
            int next = goReverse(op, now);
            if (ans[next] == 0) {
                ans[next] = ans[now] << 1 | op;
                q.add(next);
            }
        }
    }
}

//化简一个down&right操作序列
public static String simplify(String dr) {
    if (!dr.matches("^[DR]*$")) {
        throw new RuntimeException("error sequence " + dr);
    }
    int now = state(2, 3);
    for (int i = dr.length() - 1; i >= 0; i--) {
        char o = dr.charAt(i);
        now = goReverse("DR".indexOf(o + ""), now);
    }
    int operation = ans[now];
    StringBuilder builder = new StringBuilder();
    while (operation > 1) {
        int o = operation & 1;
        operation >>= 1;
        builder.append("DR".charAt(o));
    }
    return builder.toString();
}

static void printGraph() {
    initGraph();
    showGraph();
}

static String getAns(int state) {
    StringBuilder builder = new StringBuilder();
    for (int operation = ans[state]; operation > 1; operation >>= 1) {
        builder.append((operation & 1) == 0 ? "D" : "R");
    }
    return builder.toString();
}

public static void main(String[] args) {
    System.out.println(simplify("RRR"));
}
}