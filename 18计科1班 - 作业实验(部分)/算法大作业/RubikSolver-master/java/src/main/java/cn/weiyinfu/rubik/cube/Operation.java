package cn.weiyinfu.rubik.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 一个操作就是一个（type，cnt）二元组
 */
public class Operation {
int type;
int cnt;

Operation(int type, int cnt) {
    this.type = type;
    this.cnt = cnt;
}

public Operation(String operation) {
    operation = operation.trim();
    type = "后左下".indexOf(operation.charAt(0));
    if (operation.length() == 1) {
        cnt = 1;
    } else {
        cnt = Integer.parseInt(operation.substring(1));
    }
}

static int parseChar(char c) {
    if ("后左下".indexOf(c) != -1) return "后左下".indexOf(c);
    if ("bld".indexOf(Character.toLowerCase(c)) != -1) return "bld".indexOf(Character.toLowerCase(c));
    return -1;
}

@Override
public String toString() {
    String op = String.valueOf("后左下".charAt(this.type));
    if (this.cnt == 1) return op;
    else
        return op + "" + this.cnt;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Operation operation = (Operation) o;
    return type == operation.type &&
            cnt == operation.cnt;
}

List<Operation> repeat(int n) {
    List<Operation> ops = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
        ops.add(this.copy());
    }
    return ops;
}

protected Operation copy() {
    return new Operation(this.type, this.cnt);
}

@Override
public int hashCode() {
    return Objects.hash(type, cnt);
}
}
