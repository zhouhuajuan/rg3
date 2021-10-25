package com.rg3.event.test;

import com.rg3.event.exception.NegativeNumberException;
import com.rg3.event.exception.ZeroDivisionException;
import com.rg3.event.util.ArithmaticUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: rg3
 * @description: 运算器的测试类
 * @author: 周华娟
 * @create: 2021-10-25 11:51
 **/
public class ArithmaticTest {

    //测试运算除数为0的题目
    @Test
    public void testZeroDivision(){
        String exercise = "3 ÷ 0 =";
        try {
            String calculate = ArithmaticUtil.calculate(exercise);
        }catch (ZeroDivisionException e){
            System.out.println(e);
        }

    }

    //测试运算过程中有负数的情况
    @Test
    public void testNegativeNumberException(){
        String exercise = "(3 + (1 - 3)) × 4 =";
        try {
            String calculate = ArithmaticUtil.calculate(exercise);
        }catch (NegativeNumberException e){
            System.out.println(e);
        }

    }
}
