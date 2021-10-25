package com.rg3.event.service.impl;

import com.rg3.event.service.ArithmaticService;
import com.rg3.event.util.ArithmaticUtil;
import com.rg3.event.util.ExerciseUtil;
import com.rg3.event.util.FileUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: rg3
 * @description:
 * @author: 周华娟,郑宝柔
 * @create: 2021-10-23 01:24
 **/
@Service
public class ArithmaticServiceImpl implements ArithmaticService {


    @Override
    public void getQuestionsAndAnswers(Integer number, Integer range, HttpServletResponse response) throws IOException {
        List<String> exerciseList = ExerciseUtil.getExerciseList(number, range);
//        List<String> exerciseList = new ArrayList<>();
//        exerciseList.add("1 + 2 =");
//        exerciseList.add("(3 + 4) * 5 + 1");
        StringBuilder answers  = new StringBuilder();
        StringBuilder exercises  = new StringBuilder();

        //for(String exercise : exerciseList) {
        for (int i=0;i<exerciseList.size();i++){
            String result = ArithmaticUtil.calculate(exerciseList.get(i));
            //int index = exerciseList.indexOf(exerciseList.get(i)) + 1;
            answers.append(i+1);
            answers.append(".");
            answers.append(result);
            answers.append("\n");

            exercises.append(i+1);
            exercises.append(".");
            exercises.append(exerciseList.get(i));
            exercises.append("\n");
        }
        FileUtil.downloadZip(response, answers.toString(), exercises.toString());
    }
}
