package com.rg3.event.util;

import com.rg3.event.exception.NegativeNumberException;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @program: rg3
 * @description: 生成题目的工具类
 * @author: 周华娟
 * @create: 2021-10-23 21:03
 **/
public class ExerciseUtil {

    //创建题目
    public static String getExercise(int numberSize){
        //获取到运算符的数组
        String[] operators = OperatorUtil.getOperators();
        //运算符的个数
        int operatorNum = operators.length;
        //获取到对应数量的操作数的数组
        String[] numbers = NumbersUtil.getNumbers(operatorNum, numberSize);

        String[] randomOperators = returnRandomList(operators);
        String[] randomNumbers = returnRandomList(numbers);

        Random random = new Random();

        String exercise = null;
        if (operatorNum == 1){
            //如果运算符是1个，不需要加括号
            exercise = randomNumbers[0]+" "+operators[0]+" "+randomNumbers[1]+" =";
        }

        //如果运算符是2个
        if (operatorNum == 2){
            //第一种情况：没有括号
            String result1 = randomNumbers[0]+" "+randomOperators[0]+" "
                    +randomNumbers[1]+" "+randomOperators[1]+" "+randomNumbers[2]+" =";

            //第二种情况：有一个小括号
            String result2 = randomNumbers[0]+" "+randomOperators[0]+" ("+randomNumbers[1]+
                    " "+randomOperators[1]+" "+randomNumbers[2]+") =";
            String result3 = "("+randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]
                    +") "+randomOperators[1]+" "+randomNumbers[2]+" =";

            //随机抽一种情况，赋值给exercise返回
            String[] result = {result1,result2,result3};
            int anInt = random.nextInt(3);
            exercise = result[anInt];
        }

        //如果运算符是3个
        if (operatorNum == 3){
            //第一种情况：没有括号
            String result1 = randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]+" "
                    +randomOperators[1]+" " +randomNumbers[2]+" "+randomOperators[2]+" "
                    +randomNumbers[3]+" =";
            //第二种情况：有一个小括号
            String result2 = "("+randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]+") "
                    +randomOperators[1]+" " +randomNumbers[2]+" "+randomOperators[2]+" "
                    +randomNumbers[3]+" =";
            String result3 = randomNumbers[0]+" "+randomOperators[0]+" ("+randomNumbers[1]+" "
                    +randomOperators[1]+" "+randomNumbers[2]+") "+randomOperators[2]+" "
                    +randomNumbers[3]+" =";
            String result4 = randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]+" "
                    +randomOperators[1]+" ("+randomNumbers[2]+" "+randomOperators[2]+" "
                    +randomNumbers[3]+") =";
            //第三种情况：有两个小括号
            String result5 = "("+randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]+") "
                    +randomOperators[1]+" ("+randomNumbers[2]+" "+randomOperators[2]+" "
                    +randomNumbers[3]+") =";
            String result6 = "(("+randomNumbers[0]+" "+randomOperators[0]+" "+randomNumbers[1]+") "
                    +randomOperators[1]+" "+randomNumbers[2]+") "+randomOperators[2]+" "
                    +randomNumbers[3]+" =";
            String result7 = "("+randomNumbers[0]+" "+randomOperators[0]+" ("+randomNumbers[1]+" "
                    +randomOperators[1]+" "+randomNumbers[2]+")) "+randomOperators[2]+" "
                    +randomNumbers[3]+" =";
            String result8 = randomNumbers[0]+" "+randomOperators[0]+" (("+randomNumbers[1]+" "
                    +randomOperators[1]+" "+randomNumbers[2]+") "+randomOperators[2]+" "
                    +randomNumbers[3]+") =";
            String result9 = randomNumbers[0]+" "+randomOperators[0]+" ("+randomNumbers[1]+" "
                    +randomOperators[1]+" ("+randomNumbers[2]+" "+randomOperators[2]+" "
                    +randomNumbers[3]+")) =";

            //随机抽一种情况，赋值给exercise返回
            String[] result = {result1,result2,result3,result4,result5,result6,result7,result8,result9};
            int anInt = random.nextInt(9);
            exercise = result[anInt];
        }
        return exercise;
    }

    //传入一个数组，返回一个随机不重复抽取的数组
    public static String[] returnRandomList(String[] arr){
        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        String[] result = new String[arr.length];

        for (int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }

        while (!list.isEmpty()){
            int i = random.nextInt(list.size());
            if (list.contains(list.get(i))){
                list1.add(list.get(i));
                list.remove(list.get(i));
            }
        }

        for (int i=0;i<list1.size();i++){
            result[i]=list1.get(i);
        }
        return result;
    }

    //创建题目集
    public static List<String> getExerciseList(int totalNum,int numberSize){
        List<String> list = new ArrayList<>();
        //创建totalNum数量的题目集合
        while (list.size() != totalNum){
            String exercise = getExercise(numberSize);
            String calculate = null;
            try{
                calculate = ArithmaticUtil.calculate(exercise);
            }catch (NegativeNumberException e){
                System.out.println(e);
            }

            if (calculate != null){
                list.add(exercise);
            }
        }

        //查重
        return list;
    }

    @Test
    public void test(){
    }
}
