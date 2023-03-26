package cn.weiyinfu.rubik;

import java.io.File;

public class Haha {
public static void main(String[] args) {
    File[] a = File.listRoots();
    for (var i : a) {
        System.out.println(i);
    }
}
}
