package cn.weiyinfu.rubik.cube;

import java.util.ArrayList;

public class OperationList extends ArrayList<Operation> {
public OperationList(String s) {
    if (!s.matches("[左下后\\d\\s]*")) throw new RuntimeException("error operation string:" + s);
    int i = 0;
    while (i < s.length()) {
        if (String.valueOf(s.charAt(i)).matches("\\s")) {
            i++;
            continue;
        }

        int type = Operation.parseChar(s.charAt(i));
        if (type != -1) {
            int cnt = 0;
            for (i++; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                cnt = cnt * 10 + (s.charAt(i) - '0');
            }
            if (cnt == 0) cnt = 1;
            this.add(new Operation(type, cnt));
        }
    }
}

public OperationList() {

}

public OperationList reverse() {
    OperationList a = new OperationList();
    for (int i = this.size() - 1; i >= 0; i--) {
        for (int j = 0; j < 3; j++) {
            a.add(this.get(i));
        }
    }
    return a.simple();
}

public OperationList simple() {
    OperationList a = new OperationList();
    for (int i = 0; i < this.size(); ) {
        Operation op = this.get(i);
        int j = i + 1;
        int cnt = 1;
        while (j < this.size() && (op.equals(this.get(j)))) {
            cnt++;
            j++;
        }
        cnt %= 4;
        i = j;
        if (cnt == 0) continue;
        a.addAll(op.repeat(cnt));
    }
    return a;
}

@Override
public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Operation o : this) {
        builder.append(o);
    }
    return builder.toString();
}

public String toFormatString() {
    StringBuilder builder = new StringBuilder();
    int now = 0;
    Operation last = null;
    for (Operation o : this) {
        boolean canSplit = last != null && last.type != o.type;
        if (now > 5 && canSplit) {
            builder.append('\n');
            now = 0;
        }
        builder.append(o);
        now += o.toString().length();
        last = o;
    }
    return builder.toString();
}
}
