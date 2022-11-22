
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class CopyArrays {
    public static void main(String[] args) {
        int [] a={1,2,3,4,500,600,700,800};
        int [] b,c,d;
        System.out.println(Arrays.toString(a));
        b=Arrays.copyOf(a,a.length);
        System.out.println(Arrays.toString(b));
        c=Arrays.copyOf(a,4);
        System.out.println(Arrays.toString(c));
        d=Arrays.copyOfRange(a,4,a.length);
        System.out.println(Arrays.toString(d));
        c[c.length-1]=-100;
        d[d.length-1]=-200;
        System.out.println(Arrays.toString(a));
    }
    
}
