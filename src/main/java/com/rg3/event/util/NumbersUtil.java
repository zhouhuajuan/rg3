package com.rg3.event.util;

import org.junit.Test;
import java.util.Random;

/**
 * @program: rg3
 * @description: 生成随机操作数的工具类
 * @author: 周华娟
 * @create: 2021-10-23 18:47
 **/
public class NumbersUtil {

    //随机生成操作数，整数或者真分数
    public static String getNumber(int numberSize){
        //随机生成操作数
        Random random = new Random();
        //随机生成范围内的整数
        int num1 = random.nextInt(numberSize); //[0,9)
        int num2 = random.nextInt(numberSize)+1; //[1,10)
        //这样num1/num2最小值是0，最大值是9

        String result = null;
        if ((num1 % num2) == 0 || num1==0){
            //说明num1能整数num2，结果一定是一个整数
            result = String.valueOf(num1/num2);
        }else {
            //结果一定是一个分数，若不是真分数需要转为真分数
            if (num1>num2){
                //如果结果大于1说明是假分数，转换形式
                int quotient = num1/num2; //商
                int mo = num1%num2; //余数

                int factor = 0; //最大公因子
                for (int i=2;i<=mo;i++){
                    if (mo%i == 0 && num2%i == 0){
                        //两个数同时求余某个数均为0说明i是二者的公因子，化简
                        //不过公因子可能有好几个，取最大的公因子
                        factor = i;
                    }
                }

                if (factor == 0){
                    result = quotient+"'"+mo+"/"+num2;
                }else {
                    result = quotient+"'"+ mo/factor +"/"+ num2/factor;
                }
            }else {
                //如果结果小于1说明是真分数
                int factor = 0; //最大公因子
                for (int i=2;i<=num1;i++){
                    if (num1%i == 0 && num2%i == 0){
                        //两个数同时求余某个数均为0说明i是二者的公因子，化简
                        //不过公因子可能有好几个，取最大的公因子
                        factor = i;
                    }
                }

                if (factor == 0){
                    result = num1+"/"+num2;
                }else {
                    result = num1/factor +"/"+ num2/factor;
                }
            }
        }
        return result;
    }

    //根据运算符的个数随机生成相应数量的操作数
    public static String[] getNumbers(int operatorNum,int numberSize){
        //定义一个大小为operatorNum+1的String数组，存放操作数
        String[] arr = new String[operatorNum+1];
        for (int i=0;i<=operatorNum;i++){
            String number = getNumber(numberSize);
            arr[i] = number;
        }
        return arr;
    }
}
