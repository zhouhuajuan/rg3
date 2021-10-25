package com.rg3.event.controller;

import com.rg3.event.exception.EnterIsNullException;
import com.rg3.event.exception.EnterIsWrongException;
import com.rg3.event.exception.NegativeNumberException;
import com.rg3.event.service.ArithmaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @program: rg3
 * @description: 四则运算器Controller
 * @author: 周华娟，郑宝柔
 * @create: 2021-10-23 01:21
 **/
@Controller
public class ArithmaticController {

    @Autowired
    ArithmaticService arithmaticService;

    @GetMapping("/getQuestionsAndAnswers")
    public void getQuestionsAndAnswers(@RequestParam("number") Integer number, @RequestParam("range")Integer range, HttpServletResponse response) throws IOException {

        if (number == null || range == null){
            throw new EnterIsNullException("输入不能为空");
        }

        String number1 = String.valueOf(number);
        String range1 = String.valueOf(range);
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        boolean matches = pattern.matcher(number1).matches();
        boolean matches1 = pattern.matcher(range1).matches();
        if (!matches || !matches1){
            throw new EnterIsWrongException("输入只能是数字，不能是其他字符");
        }

        if (number <= 0 || range <= 0){
            throw new EnterIsWrongException("输入不能是0或者负数");
        }

        arithmaticService.getQuestionsAndAnswers(number, range, response);
    }
}
