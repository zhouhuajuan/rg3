package com.rg3.event.utils;

/**
 * @author zbr
 */
public class MathUtils {

    /**
     * 求最大公约数，辗转相除法
     * @param num1 较大的数
     * @param num2 较小的数
     * @return 最大公约数
     */
    public static int gcd(int num1, int num2) {
        int result = 0;

        while(num2 != 0) {
            result = num1 % num2;
            num1 = num2;
            num2 = result;
        }

        return num1;
    }

    /**
     * 求最小公倍数
     * @param num1 参数
     * @param num2 参数
     * @return
     */
    public static int lcm( int num1, int num2) {
        return num1 * num2 / gcd(Math.max(num1, num2),Math.min(num1, num2));
    }
}
