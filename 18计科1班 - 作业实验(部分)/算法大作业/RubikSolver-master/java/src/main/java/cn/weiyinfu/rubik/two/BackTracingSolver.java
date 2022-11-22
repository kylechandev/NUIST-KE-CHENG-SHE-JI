package cn.weiyinfu.rubik.two;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 回溯法求解二阶魔方
 */
public class BackTracingSolver extends TwoSolver {
int limit = 19;
boolean vis[];
Mini input;
List<String> ans = new ArrayList<>();

void run(Mini now, String operation) {
    if (now.code == input.code) {
        if (operation.length() < limit) ans.clear();
        limit = operation.length();
        ans.add(new StringBuilder(operation).reverse().toString());
        return;
    }
    if (operation.length() >= limit)
        return;

    for (int i = 0; i < 3; i++) {
        Mini next = now.goReverse(i);
        if (vis[next.code])
            continue;
        vis[next.code] = true;
        run(next, operation + Mini.ops.charAt(i));
        vis[next.code] = false;
    }
}

public List<String> solve(int[][] positionAndState) {
    return go(Mini.from(positionAndState));
}

public String getAns(String question) {
    vis = new boolean[5040 * 729];
    ans.clear();
    limit = 19;
    return go(Mini.from(question)).stream().collect(Collectors.joining("\n"));
}

List<String> go(Mini mini) {
    ans.clear();
    input = mini;
    vis[TableGenerator.aim.code] = true;
    run(TableGenerator.aim, "");
    return ans;
}

}
