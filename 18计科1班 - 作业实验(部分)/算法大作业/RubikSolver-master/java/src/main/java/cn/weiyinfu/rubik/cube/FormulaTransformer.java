package cn.weiyinfu.rubik.cube;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 操作序列转换器
 * */
public class FormulaTransformer {
static Map<String, Pattern> patternMap = new TreeMap<>();

static Pattern getRegex(String s) {
    Pattern res = patternMap.get(s);
    if (res == null) {
        Pattern pattern = Pattern.compile(s);
        patternMap.put(s, pattern);
        return pattern;
    }
    return res;
}

public static String eliminateDR(String s, int N) {
    //N阶魔方操作序列化简
    //这可真是最精彩的片段
    //8种运算：把整体操作移动到末尾去
    //这个地方必然是一个操作到另一个操作，一个操作不会变成2个操作，所以这里可以通过暴力枚举的方式构建表。
    String[][] rules = new String[][]{
            {"D后(\\d*)", "下$1D"},
            {"D左(\\d*)", "左$1D"},
            {"D下(\\d*)", "后(N-$1)DR"},
            {"R后(\\d*)", "左(N-$1)DDDR"},
            {"R左(\\d*)", "后$1R"},
            {"R下(\\d*)", "下$1R"},
            {"[RD]*$", ""},
            {"[RD]*", "simplify"}//对若干个RD执行化简操作
    };
    //首先把全部左N下N后N替换掉
    s = s.replaceAll("左" + N, "D")
            .replaceAll("下" + N, "R")
            .replaceAll("后" + N, "RDRRR");
    //执行置换操作
    while (s.indexOf('D') != -1 || s.indexOf('R') != -1) {
        for (String[] r : rules) {
            if (r[1].contains("(N-$1)")) {
                s = getRegex(r[0]).matcher(s).replaceAll(res -> {
                    var repeatStr = res.group(1);
                    if (repeatStr.length() > 0) {
                        int repeat = Integer.parseInt(repeatStr);
                        repeatStr = String.valueOf(N - repeat);
                    } else {
                        repeatStr = String.valueOf(N - 1);
                    }
                    return r[1].replace("(N-$1)", repeatStr);
                });
            } else if (r[1].equals("simplify")) {
                s = getRegex(r[0]).matcher(s).replaceAll(res -> DownAndRight.simplify(res.group()));
            } else {
                s = s.replaceAll(r[0], r[1]);
            }
        }
    }
    return s;
}

public static OperationList formatRubikOperation(String twelveOperation) {
    //需要把十二种操作转化成标准操作（6种操作）
    String[] ops = twelveOperation.split("\\s+");
    String[][] operationMapArray = {
            {"R", "左2左3左3左3"},
            {"U", "下2下3下3下3"},
            {"F", "后2后3后3后3"},
            {"L", "左"},
            {"D", "下"},
            {"B", "后"}};
    Map<String, List<Operation>> operationMap = new TreeMap<>();
    for (String[] operationReplace : operationMapArray) {
        List<Operation> op = new OperationList(operationReplace[1]);
        operationMap.put(operationReplace[0], op);
    }
    Pattern pattern = Pattern.compile("^([LRBFUD]'?)(\\d*)$");
    OperationList operationList = new OperationList();
    for (String s : ops) {
        //发现异常操作，立即返回
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) throw new RuntimeException("error twelve operation");
        String op = matcher.group(1);
        int repeat = 1;
        if (matcher.group(2).length() > 0) {
            repeat = Integer.parseInt(matcher.group(2));
        }
        //把逆操作消除掉，只留正操作
        if (op.endsWith("'")) {
            op = op.substring(0, 1);
            repeat *= 3;
            repeat %= 4;
        }
        //把重复次数消掉
        for (int j = 0; j < repeat; j++) {
            operationList.addAll(operationMap.get(op));
        }
    }
    return operationList.simple();
}
}
