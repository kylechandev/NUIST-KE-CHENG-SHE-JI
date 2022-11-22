package cn.weiyinfu.rubik;

import cn.weiyinfu.rubik.cube.Cube;
import cn.weiyinfu.rubik.cube.Solver;
import junit.framework.TestCase;
import cn.weiyinfu.rubik.three.min2phase.Min2PhaseSolver;

public class TestMin2phase extends TestCase {
public void testMin2phase() {
    Solver s = new Min2PhaseSolver();
    String question = "grbwwygywygwwgbyywogbrrrbggooygbbrowroybybrwgborrowoyo";
    System.out.println(question.length());
    String ans = s.solve(question);
    System.out.println(ans);
    Cube cube = new Cube(question).regular();
    cube.go(ans);
    System.out.println(cube);
    assertEquals(cube.regular(), new Cube(3));
}
}
