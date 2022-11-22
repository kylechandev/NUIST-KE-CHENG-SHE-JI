package cn.weiyinfu.rubik.two;

import cn.weiyinfu.rubik.cube.Solver;

/**
 * 虚拟类，用于执行校验操作
 * */
public abstract class TwoSolver implements Solver {
abstract String getAns(String input);

@Override
public String solve(String input) {
    String validateResult = Validator.validate(Mini.from(input).extend().a);
    if (validateResult != null) return validateResult;
    return getAns(input);
}

@Override
public int getN() {
    return 2;
}

}
