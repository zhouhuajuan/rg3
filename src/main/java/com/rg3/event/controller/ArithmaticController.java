package com.rg3.event.controller;

import com.rg3.event.service.ArithmaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            arithmaticService.getQuestionsAndAnswers(number, range, response);
    }
}
