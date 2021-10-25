package com.rg3.event.test;

import com.rg3.event.exception.EnterIsNullException;
import com.rg3.event.exception.EnterIsWrongException;
import com.rg3.event.util.ExerciseUtil;
import com.rg3.event.util.NumbersUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: rg3
 * @description: 创建题目的测试类
 * @author: 周华娟
 * @create: 2021-10-25 10:36
 **/
public class ExerciseTest {

    //测试将一个数组打乱排序
    @Test
    public void testReturnRandomList(){
        String[] numbers = NumbersUtil.getNumbers(3, 10);
        for (String s:numbers){
            System.out.println("打乱前："+s);
        }
        String[] randomOperators = ExerciseUtil.returnRandomList(numbers);
        for (String s:randomOperators){
            System.out.println("打乱后："+s);
        }
    }

    //测试能否正常生成一个四则运算题
    @Test
    public void testGetExercise(){
        String exercise = ExerciseUtil.getExercise(10);
        System.out.println("随机生成范围在10以内的题目："+exercise);
    }

    //测试能否正常生成题目集
    @Test
    public void testGetExerciseList(){
        List<String> exerciseList = ExerciseUtil.getExerciseList(5, 10);
        for (int i =0;i<exerciseList.size();i++){
            System.out.println("第"+i+"题："+exerciseList.get(i));
        }
    }

    //测试输入为空
    @Test
    public void testEnterIsNull(){
        try {
            List<String> exerciseList = ExerciseUtil.getExerciseList(2, 0);
            throw new EnterIsNullException("输入不能为空也不能为0");
        }catch (EnterIsNullException e){
            System.out.println(e);
        }
    }

    //测试输入为0或者负数或者是字符
    @Test
    public void testEnterIsWrong(){
        int totalNum = -1;
        int numberSize = 0;
        String number = "a";
        String range = "b";
        try {
            if (totalNum <= 0 || numberSize <= 0){
                throw new EnterIsWrongException("输入不能是0或者负数");
            }
        }catch (EnterIsWrongException e){
            System.out.println(e);
        }

        try {
            Pattern pattern = Pattern.compile("^-?[0-9]+");
            boolean matches = pattern.matcher(number).matches();
            boolean matches1 = pattern.matcher(range).matches();
            if (!matches || !matches1){
                throw new EnterIsWrongException("输入只能是数字，不能是其他字符");
            }
        }catch (EnterIsWrongException e){
            System.out.println(e);
        }

    }

    //测试输入为0或者负数或者是字符
    @Test
    public void testEnterIsWrong1(){
        try {
            List<String> exerciseList = ExerciseUtil.getExerciseList(10, -2);
        }catch (EnterIsWrongException e){
            System.out.println(e);
        }

    }
}
