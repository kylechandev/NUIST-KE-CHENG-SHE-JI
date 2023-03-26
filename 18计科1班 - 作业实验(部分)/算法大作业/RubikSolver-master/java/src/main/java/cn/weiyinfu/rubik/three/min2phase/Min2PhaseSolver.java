package cn.weiyinfu.rubik.three.min2phase;

import cn.weiyinfu.rubik.cube.Cube;
import cn.weiyinfu.rubik.cube.FormulaTransformer;
import cn.weiyinfu.rubik.cube.OperationList;
import cn.weiyinfu.rubik.cube.Solver;

import java.util.Arrays;

public class Min2PhaseSolver implements Solver {
public String solve12(String q) {
    //使用12种操作的方式求解
    q = new Cube(q).regular().toCompactString();
    String to = "ULFRBD";
    String from = "wgrboy";
    int[] face = {0, 3, 2, 4, 1, 5};
    char[] a = new char[54];
    for (int i = 0; i < q.length(); i++) {
        a[i] = to.charAt(from.indexOf(q.charAt(i)));
    }
    //交换底面和后面
    for (int i = 0; i < 5; i++) {
        char temp = a[45 + i];
        a[45 + i] = a[53 - i];
        a[53 - i] = temp;
    }
    StringBuilder builder = new StringBuilder(54);
    for (int i = 0; i < 6; i++) {
        builder.append(Arrays.copyOfRange(a, face[i] * 9, face[i] * 9 + 9));
    }
    //十二种操作集：B2 L2 U  D2 R2 D' B  R2 U2 R2 L  B2 D2 F2 U2 R2 B2 F
    return new Search().solution(builder.toString(), 21, 10000, 0, 0);
}

@Override
public String solve(String input) {
    var twelveOperation = solve12(input);
    if (twelveOperation.trim().startsWith("Error")) return twelveOperation;
    OperationList operationList = FormulaTransformer.formatRubikOperation(twelveOperation);
    String opStr = FormulaTransformer.eliminateDR(operationList.toString(), this.getN());
    operationList = new OperationList(opStr);
    return operationList.toFormatString();
}

@Override
public int getN() {
    return 3;
}

public static void main(String[] args) {
    Solver s = new Min2PhaseSolver();
//    String question = "grbwwygywygwwgbyywogbrrrbggooygbbrowroybybrwgborrowoyo";
    String question = "bwoywwrwwybggbygwoyrrrobwoybrwrgrbyggyogygwgogoyggbrbb";
    System.out.println(question.length());
    String ans = s.solve(question);
    System.out.println(ans);
    Cube cube = new Cube(question);
    cube.go(ans);
    System.out.println(cube);
}
}
