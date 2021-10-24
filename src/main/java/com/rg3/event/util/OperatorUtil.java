package com.rg3.event.util;

import java.util.Random;

/**
 * @program: rg3
 * @description: 获取随机的1-3个运算符
 * @author: 周华娟
 * @create: 2021-10-23 11:21
 **/
public class OperatorUtil {

    //随机生成运算符的个数，范围是1-3
    private static int getOperatorNum(){
        Random random = new Random();
        return (random.nextInt(3)+1);
    }

    // 获取运算符
    public static String setOperator(int index) {
        String string = "";
        switch (index) {
            case 0:
                string = "+";
                break;
            case 1:
                string = "-";
                break;
            case 2:
                string = "×";
                break;
            case 3:
                string = "÷";
                break;
        }
        return string;
    }

    //根据运算符的个数，随机生成对应数量的运算符并存放进一个String数组
    public static String[] getOperators(){
        //随机生成运算符的数量
        int operatorNum = getOperatorNum();
        //生成一个大小为operatorNum的数组，存放运算符
        String[] arr = new String[operatorNum];
        for (int i=0;i<operatorNum;i++){
            Random random = new Random();
            int operatorIndex = random.nextInt(4);
            String operator = setOperator(operatorIndex);
            arr[i] = operator;
        }
        return arr;
    }
}
