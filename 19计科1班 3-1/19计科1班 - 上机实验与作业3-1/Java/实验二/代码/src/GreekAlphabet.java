/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class GreekAlphabet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int startPosition=0,endPosition=0;
        char cStart='α',cEnd='ω';
        startPosition=(int) cStart;
        endPosition=(int) cEnd;
        System.out.println("希腊字母'α'在unicode表中的位置是："+startPosition);
        System.out.println("希腊字母表：");
        for(int i=startPosition;i<=endPosition;i++){
            char c='\0';
            c=(char) i;
            System.out.print(""+c);
            if((i-startPosition+1)%10==0)
            System.out.println("");
        }
    }
    
}
