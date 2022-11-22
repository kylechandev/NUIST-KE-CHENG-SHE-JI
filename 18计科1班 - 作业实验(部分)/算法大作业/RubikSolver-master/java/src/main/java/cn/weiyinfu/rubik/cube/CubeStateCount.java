package cn.weiyinfu.rubik.cube;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 给一个初始状态，计算魔方有多少种状态
 * */
public class CubeStateCount {
    void push(Cube c, Set<String> se, Queue<Cube> q) {
        var s = c.toCompactString();
        if (se.contains(s)) {
            return;
        }
        se.add(s);
        q.add(c);
    }

    String hash(Cube c) {
        //把cube变成最小字符串
        Set<String> se = new HashSet<>();
        Queue<Cube> q = new LinkedList<>();
        q.add(c);
        while (!q.isEmpty()) {
            Cube now = q.poll();
            var nows = now.toCompactString();
            se.add(nows);
            var down = now.copy();
            var right = now.copy();
            down.go(2, 2);
            right.go(0, 2);
            push(down, se, q);
            push(right, se, q);
        }
        return se.stream().sorted().findFirst().get();
    }

    Queue<Cube> q;
    Set<String> vis;

    void push(Cube x) {
        var xs = hash(x);
        if (vis.contains(xs)) {
            return;
        }
        q.add(x);
        vis.add(xs);
    }

    CubeStateCount() {
//        Cube c = new Cube("wwwwbbbbrrrrbbbbrrrrwwww");
        Cube c = new Cube("wwwwbbbbrrrrooooyyyygggg");
        vis = new HashSet<>();
        q = new LinkedList<>();
        push(c);
        while (!q.isEmpty()) {
            var now = q.poll();
            for (int i = 0; i < 3; i++) {
                var cc = now.copy();
                cc.go(i, 1);
                push(cc);
            }
        }
        System.out.println(vis.size());
    }

    public static void main(String[] args) {
        new CubeStateCount();
    }
}
