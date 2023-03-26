package com.fht;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;

public class PassTest {
    //利用shiro的加密来生成密码
    @Test
    public void pwdTest(){
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //盐值
        String salt = "161164519";
        //原密码
        String pwd = "161164519";
        System.out.println(ByteSource.Util.bytes(salt));
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
        System.out.println(result);
    }
}
