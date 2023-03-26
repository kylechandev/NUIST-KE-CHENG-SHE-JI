package cn.weiyinfu.rubik;

import cn.weiyinfu.rubik.cube.Operation;
import cn.weiyinfu.rubik.cube.OperationList;
import junit.framework.TestCase;

import java.util.List;

public class TestOperationList extends TestCase {
public void test() {
    var ops = new OperationList("左2下3 后2后下左");
    System.out.println(ops);
    System.out.println(ops.reverse());
    System.out.println(new OperationList(ops.toString()).toFormatString());
}
public void testSimple(){
    var ops=new OperationList("左左左左左左");
    System.out.println(ops);
    System.out.println(ops.simple());
    System.out.println(ops.reverse());
}
}
