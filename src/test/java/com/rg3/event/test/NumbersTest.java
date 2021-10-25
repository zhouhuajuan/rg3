package com.rg3.event.test;

import com.rg3.event.util.NumbersUtil;
import com.rg3.event.util.OperatorUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: rg3
 * @description: 随机生成操作数的测试类
 * @author: 周华娟
 * @create: 2021-10-25 09:21
 **/
public class NumbersTest {

    @Test
    public void testGetNumber(){
        String number = NumbersUtil.getNumber(10);
        System.out.println("随机生成的在0-10范围内的操作数："+number);
    }

    @Test
    public void testGetNumbers(){
        int operatorNum = OperatorUtil.getOperatorNum();
        System.out.println("随机生成的运算符个数："+operatorNum);
        String[] numbers = NumbersUtil.getNumbers(operatorNum, 10);
        for (String s : numbers){
            System.out.println("随机生成的操作数："+s);
        }
    }
}
