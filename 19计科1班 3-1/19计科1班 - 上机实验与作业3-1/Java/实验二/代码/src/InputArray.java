/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class InputArray {
    public static void main(String[] args) {
        int [] a={100,200,300};
        System.out.println(""+a.length);
        System.out.println(a);
        int b[][]={{1},{1,1},{1,2,1},{1,3,3,1},{1,4,6,4,1}};
        System.out.println(""+b.length);
        System.out.println(""+b[4][2]);
        b[4]=a;
        System.out.println(b[4][2]);
        
    }
    
}
