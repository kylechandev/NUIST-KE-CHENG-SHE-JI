package com.fht.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
public class PassTest {
    //利用shiro的加密来生成密码
    public static void main(String[] args) {
        String pass=hashAlgorith("123456","admin");
        System.out.println(pass);
    }

    public static String hashAlgorith(String pwd,String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
//        System.out.println(ByteSource.Util.bytes(salt));
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
//        System.out.println(result);
        return result;
    }
}