package com.rg3.event.test;

import com.rg3.event.exception.FileNotExistsException;
import com.rg3.event.util.FileUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: rg3
 * @description: 测试文件的测试类
 * @author: 周华娟
 * @create: 2021-10-25 13:36
 **/
public class FileTest {

    //测试错误路径的异常
    @Test
    public void filePathIsWrongExceptionTest(){
        try {
            String s = "1 + 2 =";
            FileUtil.downloadFile("F:/a.txt",s);
        }catch (FileNotExistsException e){
            System.out.println(e);
        }
    }
}
