package cn.weiyinfu.rubik.cube;

import java.util.List;

/**
 * 任意阶魔方的数据模拟
 * 模仿剑形
 * 模拟表象
 */
public class Cube {
//三种旋转操作，每种旋转操作对应一个面
final static int[] operationFaceMap = {5, 1, 4};//三种操作对应的面，后左下
//每次旋转会影响四个面，以被选转面为参考系，这四个面的顺序为：上右下左
final static int[][] rotateMap = {{4, 3, 0, 1}, {0, 2, 4, 5}, {2, 3, 5, 1}};
//颜色常量
final static String COLOR = "wgrbyo";
// opsite[i] is the opsite face of the ith face,6个面的对面
final static int opsite[] = {4, 3, 5, 1, 0, 2};


int N;//魔方的阶数
public char[][][] colors;//剑形表示法，第一维表示六个面，第二维表示x，y

//魔方颜色的位置，是一个(face,x,y)三元组
class Point {
    int face;
    int x, y;

    Point(int face, int x, int y) {
        this.face = face;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", face, x, y);
    }
}

public Cube(int n) {
    this.N = n;
    this.colors = new char[6][N][N];
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                colors[i][j][k] = COLOR.charAt(i);
            }
        }
    }
}

public Cube(String s) {
    StringBuilder ss = new StringBuilder();
    for (char i : s.toCharArray()) if (COLOR.indexOf(i) != -1) ss.append(i);
    s = ss.toString();
    int n = (int) Math.sqrt(s.length() / 6);
    if (n * n * 6 != s.length()) {
        throw new RuntimeException("illegal input");
    }
    this.N = n;
    this.colors = new char[6][N][N];
    int si = 0;
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                this.colors[i][j][k] = s.charAt(si++);
            }
        }
    }
}

void rotateFace(char[][] a, boolean reverse) {
    char[][] b = new char[N][N];
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            b[i][j] = a[i][j];
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (reverse) {
                a[i][j] = b[j][N - 1 - i];
            } else {
                a[i][j] = b[N - 1 - j][i];
            }
        }
    }
}

//将op,cnt,face,x映射为一个剑形表示法中的坐标
//cnt表示从旋转的这个面向外数第几个环
//edge表示face的哪一条边跟中间是邻居
Point map(int op, int cnt, int face, int which) {
    if (op == 0) {
        if (face == 4) {
            return new Point(face, N - 1 - cnt, which);
        } else if (face == 3) {
            return new Point(face, N - 1 - which, N - 1 - cnt);
        } else if (face == 0) {
            return new Point(face, cnt, N - 1 - which);
        } else if (face == 1) {
            return new Point(face, which, cnt);
        } else {
            throw new RuntimeException("impossible");
        }
    } else if (op == 1) {
        return new Point(face, which, cnt);
    } else if (op == 2) {
        if (face == 2 || face == 3) {
            return new Point(face, N - 1 - cnt, which);
        } else if (face == 5) {
            return new Point(face, cnt, N - 1 - which);
        } else if (face == 1) {
            return new Point(face, N - 1 - cnt, which);
        } else {
            throw new RuntimeException("error face " + face);
        }
    } else {
        throw new RuntimeException("error op:" + op);
    }
}

//旋转环
void rotateRing(int op, int cnt) {
    Point[][] ring = new Point[4][N];
    for (int i = 0; i < 4; i++) {
        int face = rotateMap[op][i];
        for (int j = 0; j < N; j++) {
            ring[i][j] = map(op, cnt, face, j);
        }
    }
    char[][] a = new char[4][N];
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < N; j++) {
            a[i][j] = colors[ring[i][j].face][ring[i][j].x][ring[i][j].y];
        }
    }
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < N; j++) {
            colors[ring[i][j].face][ring[i][j].x][ring[i][j].y] = a[(i + 3) % 4][j];
        }
    }
}


//执行一个Operation，op表示方向，cnt表示旋转的层数
void go(int op, int cnt) {
    rotateFace(colors[operationFaceMap[op]], false);
    for (int i = 0; i < cnt; i++) {
        rotateRing(op, i);
    }
    if (cnt == N) {
        rotateFace(colors[opsite[operationFaceMap[op]]], true);
    }
}

public void go(List<Operation> operations) {
    for (Operation o : operations) {
        go(o.type, o.cnt);
    }
}

public void go(String operations) {
    go(new OperationList(operations));
}

Cube copy() {
    Cube cube = new Cube(N);
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                cube.colors[i][j][k] = colors[i][j][k];
            }
        }
    }
    return cube;
}

//把红心白发蓝右臂移动到正确的位置
public Cube regular() {
    //正方体有24种放置方法
    for (int i = 0; i < 24; i++) {
        Cube b = copy();
        for (int j = DownAndRight.ans[i]; j > 1; j >>= 1) {
            if ((j & 1) == 0) {
                b.go(1, b.N);//整体向下
            } else {
                b.go(2, b.N);//整体向右
            }
        }
        if (b.colors[0][b.N - 1][b.N - 1] == 'w' && b.colors[2][0][b.N - 1] == 'r' && b.colors[3][0][0] == 'b') {
            return b;
        }
    }
    throw new RuntimeException("unable to regularize this cube");
}

public String toCompactString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                builder.append(colors[i][j][k]);
            }
        }
    }
    return builder.toString();
}

@Override
public boolean equals(Object obj) {
    if (!(obj instanceof Cube)) return false;
    Cube cube = (Cube) obj;
    if (cube.N != N) return false;
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (cube.colors[i][j][k] != colors[i][j][k])
                    return false;
            }
        }
    }
    return true;
}

@Override
public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                builder.append(colors[i][j][k]);
            }
            builder.append("\n");
        }
        builder.append("\n");
    }
    return builder.toString();
}


}
