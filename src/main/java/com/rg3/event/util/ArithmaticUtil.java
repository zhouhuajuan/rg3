package com.rg3.event.util;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author zbr
 * 四则计算器工具类
 */
public class ArithmaticUtil {

    private static Map<String, Integer> operatorMap = new HashMap<>();

    static {
        operatorMap.put("*", 2);
        operatorMap.put("/", 2);
        operatorMap.put("-", 1);
        operatorMap.put("+", 1);
    }
    /**
     * @param expression 表达式
     * @return 四则运算的结果
     */
    public static String calculate(String expression) {

        String exp = StrUtil.subBefore(expression, "=", true);

        List<String> infixExpressionList = getListString(exp);
        List<String> list = parseSuffixExpressionList(infixExpressionList);

        return calculatePostfix(list);
    }

    //中缀表达式的list转成后缀表达式
    private static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();

        //储存中间结果的栈s2
        List<String> s2 = new ArrayList<>();

        //遍历s1
        for (String item : list) {
            //利用正则表达式判断是否含有数字，有则加入s2
            if (item.matches(".*[0-9]{1,}.*")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                //一次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //将( 弹出s1，消除小括号
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到循环中与s1中的新的栈顶元素比较
                while (s1.size() != 0 && operatorMap.getOrDefault(s1.peek(), 0) >= operatorMap.getOrDefault(item, 0)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    //将一个逆波兰表达式依次将数据和运算符放到ArrayList中,而不是直接去取，不然的话要设置一个个index，就太累了
    private static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            //表示是否有 ")"
            String suffix = null;
            //如果ele有多个"("的话，就依次添加到list中
            while (ele.contains("(")) {
                list.add(ele.substring(0, 1));
                ele = ele.substring(1);

            }
            //如果ele有")"，比如说"4))"，那么就需要把4和"))"分隔开
            if (ele.contains(")")) {
                //获取数字
                String num = StrUtil.subBefore(ele, ")", false);
                //获取")"
                suffix = StrUtil.subAfter(ele, num, true);
                ele = num;
            }
            list.add(ele);
            //如果有")"，则需要依次添加到list中
            if (suffix != null) {
                for (char c : suffix.toCharArray()) {
                    list.add(String.valueOf(c));
                }
            }
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    private static String calculatePostfix(List<String> list) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //这里用正则表达式来取出数
            if (item.matches(".*[0-9]{1,}.*")) {
                //入栈
                stack.push(item);

            } else {
                //pop出两个数，并运算,再入栈
                String num2 = stack.pop();
                num2 = toFraction(num2);
                String num1 = stack.pop();
                num1 = toFraction(num1);
                String res = null;

                if ("+".equals(item)) {
                    res = add(num1, num2);
                } else if ("-".equals(item)) {
                    res = minus(num1, num2);
                } else if ("*".equals(item)) {
                    res = multiplication(num1, num2);
                } else if ("/".equals(item)) {
                    res = division(num1, num2);
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据是结果
        return toFormalForm(stack.pop());
    }

    //转化成分数
    private static String toFraction(String num) {
        //判断该分数是否为假分数
        if (num.contains("'")) {
            String[] split = num.split("'");
            int prefix = Integer.parseInt(split[0]);
            String fraction = split[1];
            String[] split1 = fraction.split("/");

            int numerator = Integer.parseInt(split1[0]);
            int denominator = Integer.parseInt(split1[1]);

            int newNumerator = prefix * denominator + numerator;

            num = newNumerator + "/" + denominator;
            //如果是整数，转化为 n/1 分数的形式
        } else if (!num.contains("/")) {
            num = num + "/" + "1";
        }
        return num;
    }

    //转换成作业规定的结果格式
    private static String toFormalForm(String num) {

        String result = null;
        if (!num.contains("/")) {
            return num;
        }

        String[] split = num.split("/");
        int numerator = Integer.parseInt(split[0]);
        int denominator = Integer.parseInt(split[1]);

        //如果分母为1，则为整数，直接返回分子
        if (denominator == 1) {
            result = String.valueOf(numerator);
            //如果分母小于分子，则化为x'y的格式
        } else if (numerator > denominator) {
            int prefix = numerator / denominator;
            int suffixNumerator = numerator - prefix * denominator;

            result = prefix + "'" + suffixNumerator + "/" + denominator;
        }

        return result;
    }

    /**
     * 加法
     * @param num1 被加数
     * @param num2 加数
     * @return
     */
    private static String add(String num1, String num2) {
        String[] fraction1 = num1.split("/");
        String[] fraction2 = num2.split("/");
        String result = null;

        //获取num1的分子
        int numerator1 = Integer.parseInt(fraction1[0]);
        //获取num2的分母
        int denominator1 = Integer.parseInt(fraction1[1]);

        //获取num2的分子
        int numerator2 = Integer.parseInt(fraction2[0]);
        //获取num2的分母
        int denominator2 = Integer.parseInt(fraction2[1]);

        //如果num1和num2都是整数，则直接相加
        if (denominator1 == 1 && denominator2 == 1) {
            result = String.valueOf(numerator1 + numerator2);
        } else {
            //获取分母的最小公倍数
            int denominatorLCM = MathUtil.lcm(denominator1, denominator2);
            //获取相加后的分子
            int addNumerator = numerator1 * denominatorLCM / denominator1 + numerator2 * denominatorLCM / denominator2;

            //获取分子分母的最大公因数
            int fractionGCD = MathUtil.gcd(Math.max(denominatorLCM, addNumerator), Math.min(denominatorLCM, addNumerator));
            //化简
            int newNumerator = addNumerator / fractionGCD;
            int newDenominator = denominatorLCM / fractionGCD;

            result = newNumerator + "/" + newDenominator;
        }
        return result;
    }

    /**
     * 除法
     * @param num1 被除数
     * @param num2 除数
     * @return
     */
    private static String division(String num1, String num2) {
        String[] split = num2.split("/");
        int numerator = Integer.parseInt(split[0]);
        int denominator = Integer.parseInt(split[1]);

        //除法相当于被除数 * 除数的倒数
        String newNum = denominator + "/" + numerator;

        return multiplication(num1, newNum);
    }

    /**
     * 减法
     * @param num1 被减数
     * @param num2 减数
     * @return
     */
    private static String minus(String num1, String num2) {
        //减法转化成加法计算
        return add(num1, "-" + num2);
    }

    /**
     * 乘法
     * @param num1 乘数
     * @param num2 乘数
     * @return
     */
    private static String multiplication(String num1, String num2) {
        String[] fraction1 = num1.split("/");
        String[] fraction2 = num2.split("/");
        String result = null;

        //获取num1的分子
        int numerator1 = Integer.parseInt(fraction1[0]);
        //获取num1的分母
        int denominator1 = Integer.parseInt(fraction1[1]);

        //获取num1的分子
        int numerator2 = Integer.parseInt(fraction2[0]);
        //获取num2的分母
        int denominator2 = Integer.parseInt(fraction2[1]);

        //如果num1和num2都是整数，就直接相乘
        if (denominator1 == 1 && denominator2 == 1) {
            result = String.valueOf(numerator1 * numerator2);
        } else {
            //分子之间相乘
            int mulNumerator = numerator1 * numerator2;
            //分母之间相乘
            int mulDenominator = denominator1 * denominator2;

            //获取最大公倍数
            int gcd = MathUtil.gcd(Math.max(mulDenominator, mulNumerator), Math.min(mulDenominator, mulNumerator));

            //进行化简
            int newNumerator = mulNumerator / gcd;
            int newDenominator = mulDenominator / gcd;

            result = newNumerator + "/" + newDenominator;
        }

        return result;
    }

}

