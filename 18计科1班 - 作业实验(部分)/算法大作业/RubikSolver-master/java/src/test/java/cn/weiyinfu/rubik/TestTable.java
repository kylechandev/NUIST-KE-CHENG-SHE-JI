package cn.weiyinfu.rubik;

import cn.weiyinfu.rubik.cube.OperationList;
import junit.framework.TestCase;
import cn.weiyinfu.rubik.two.TableSolver;

public class TestTable extends TestCase {
public void testTableSolver() {
    var solver = new TableSolver();
    String ans = solver.solve("bgywygyrorybbwwrgobwogro");
    System.out.println(new OperationList(ans).toFormatString());
}
}
