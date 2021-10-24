package com.rg3.event.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: rg3
 * @description: 四则运算器业务逻辑代码
 * @author: 周华娟，郑宝柔
 * @create: 2021-10-23 01:22
 **/
public interface ArithmaticService {

    public void getQuestionsAndAnswers(Integer number, Integer range, HttpServletResponse response) throws IOException;
}
