package cn.weiyinfu.rubik.two;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

//打表器
public class TableGenerator {
static public int a[] = new int[5040 * 729];

static final Mini aim = Mini.getInitState();


void save() {
    try {
        DataOutputStream cout = new DataOutputStream(Files.newOutputStream(Paths.get("table.data")));
        for (int i = 0; i < a.length; i++) {
            cout.writeInt(a[i]);
        }
        cout.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public TableGenerator() {
    Queue<Mini> q = new ArrayDeque<Mini>();
    q.add(aim);
    a[0] = 1;
    while (!q.isEmpty()) {
        Mini now = q.poll();
        for (int i = 0; i < 3; i++) {
            Mini next = now.goReverse(i);
            if (a[next.code] == 0) {
                q.add(next);
                a[next.code] = a[now.code] * 3 + i;
            }
        }
    }
    save();
}

public static void main(String[] args) {
    System.out.println(System.currentTimeMillis());
    new TableGenerator();
    System.out.println(System.currentTimeMillis());
}
}
