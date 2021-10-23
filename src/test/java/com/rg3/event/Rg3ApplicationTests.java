package com.rg3.event;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Rg3ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        String str = "8))";
        System.out.println(str.substring(str.lastIndexOf("8") + 1));
     //   System.out.println(StrUtil.subBefore(str, ")", false));
    }
}
