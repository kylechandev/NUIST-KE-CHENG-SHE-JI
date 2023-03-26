package cn.weiyinfu.rubik.two.think;

import cn.weiyinfu.rubik.two.Validator;

import java.util.*;

/**
 * 寻找二阶魔方中的守恒量
 */
public class $1ConstantValue {
class Node {
    int[] a = new int[8];
}

Node getInitNode() {
    Node node = new Node();
    for (int i = 0; i < 8; i++) {
        node.a[i] = 0;
    }
    return node;
}

int hash(int[] a) {
    int x = 0;
    for (int i = 0; i < 8; i++) {
        x += a[i] * Math.pow(3, i);
    }
    return x;
}

Node down(Node now) {
    int a[] = {2, 3, 1, 0};
    int ma[] = {1, 0, 2};
    Node no = new Node();
    for (int i = 0; i < 8; i++) no.a[i] = now.a[i];
    for (int i = 0; i < 4; i++) {
        no.a[a[i]] = ma[now.a[a[(i + 1) % 4]]];
    }
    return no;
}

Node back(Node now) {
    int a[] = {2, 0, 4, 6};
    int ma[] = {0, 2, 1};
    Node no = new Node();
    for (int i = 0; i < 8; i++) no.a[i] = now.a[i];
    for (int i = 0; i < 4; i++) {
        no.a[a[i]] = ma[now.a[a[(i + 1) % 4]]];
    }
    return no;
}

Node left(Node now) {
    int a[] = {0, 1, 5, 4};
    int ma[] = {2, 1, 0};
    Node no = new Node();
    for (int i = 0; i < 8; i++) no.a[i] = now.a[i];
    for (int i = 0; i < 4; i++) {
        no.a[a[i]] = ma[now.a[a[(i + 1) % 4]]];
    }
    return no;
}

Node go(Node now, int op) {
    if (op == 0) return left(now);
    else if (op == 1) return down(now);
    else return back(now);
}

int[] parse(int code) {
    int[] cnt = new int[8];
    for (int i = 0; i < 8; i++) {
        cnt[i] = (int) (code / Math.pow(3, i)) % 3;
    }
    return cnt;
}

String tos(int code) {
    StringBuilder s = new StringBuilder(8);
    for (int i = 0; i < 8; i++) {
        s.append((int) ((code / Math.pow(3, i)) % 3));
    }
    return s.toString();
}

Set<Integer> visited = new TreeSet<>();

$1ConstantValue() {
    Queue<Node> q = new LinkedList<>();
    Node init = getInitNode();
    q.add(init);
    visited.add(hash(init.a));
    while (!q.isEmpty()) {
        Node now = q.poll();
        for (int op = 0; op < 3; op++) {
            Node next = go(now, op);
            int code = hash(next.a);
            if (!visited.contains(code)) {
                q.add(next);
                visited.add(code);
            }
        }
        visited.add(hash(now.a));
    }
    //下面证明只看其中6位可以推出第七位
    Set<Integer> halfSet = new TreeSet<>();
    for (int i : visited) {
        halfSet.add(i / 3);
    }
    System.out.println(halfSet.size());
    System.out.println("==============");
    for (int i : visited) {
        System.out.println(tos(i));
        if (Validator.validateState(parse(i)) != null) {
            throw new RuntimeException("validator is wrong " + tos(i));
        }
    }
}

public static void main(String[] args) {
    new $1ConstantValue();
}
}
