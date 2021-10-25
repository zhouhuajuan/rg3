package com.rg3.event.test;

import com.rg3.event.util.OperatorUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: rg3
 * @description: 生成运算符测试类
 * @author: 周华娟
 * @create: 2021-10-25 09:14
 **/
public class OperatorTest {

    //测试随机生成运算符的个数
    @Test
    public void testGetOperatorNum(){
        int operatorNum = OperatorUtil.getOperatorNum();
        System.out.println("随机生成的操作符个数："+operatorNum);
    }

    //测试随机生成1-3个运算符
    @Test
    public void testGetOperators(){
        String[] operators = OperatorUtil.getOperators();
        for (String s : operators){
            System.out.println(s);
        }
    }
}
