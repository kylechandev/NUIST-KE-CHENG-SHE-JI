package cn.weiyinfu.rubik;

import cn.weiyinfu.rubik.cube.Cube;
import junit.framework.TestCase;
import cn.weiyinfu.rubik.three.min2phase.Min2PhaseSolver;
import cn.weiyinfu.rubik.two.TableSolver;

public class TestCube extends TestCase {
public void testMiniCube() {
    Cube c = new Cube(2);
    c.go("左下后下后下左");
    TableSolver solver = new TableSolver();
    String state = c.toString();
    state = state.replaceAll("\\s+", "");
    String reverseOp = solver.getAns(state);
    System.out.println(reverseOp);
    c.go(reverseOp);
    System.out.println(c.toString());
}

public void testRubikCube() {
    Cube c = new Cube(3);
    c.go("左2左后后左下后");
    c.go("后后后下下下左左左后后后后后后左左左左2左2左2");
    System.out.println(c.toString());
}

public void testRubikCube2() {
    Cube c = new Cube(3);
    c.go("左2左后2后左下后");
    Min2PhaseSolver solver = new Min2PhaseSolver();
    var op = solver.solve(c.toString().replaceAll("\\s", ""));
    c.go(op);
    System.out.println(c.toString());
}

public void testWhole() {
    //测试整体移动
    Cube c = new Cube(3);
    c.go("左2左2左2左2");
    c.go("下2下2下2下2");
    c.go("后2后2后2后2");
    System.out.println(c);
}

public void testComplex2() {
    Cube c = new Cube(2);
    c.go("左下2");
    /*
     * tableSolver输出的结果是正则化之后的魔方，不是正则化的魔方不能直接操作
     * */
    TableSolver solver = new TableSolver();
    String solution = solver.solve(c.toString());
    System.out.println(solution);
    c.go(solution);
    System.out.println(c);
}

public void testComplex() {
    Cube c = new Cube(2);
    c.go("左下2");
    TableSolver solver = new TableSolver();
    Cube anoter = c.regular();
    String solution = solver.solve(anoter.toString());
    System.out.println(solution);
    anoter.go(solution);
    System.out.println(anoter);
}

}
