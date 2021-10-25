package com.rg3.event.application;

import com.rg3.event.util.ArithmaticUtil;
import com.rg3.event.util.ExerciseUtil;
import com.rg3.event.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author zbr
 */
public class Main {

    public static void main(String[] args) {
        int length = args.length;
        int number = 0;
        int range = 0;

        if(length == 4){
            if(("-n".equals(args[0]) && "-r".equals(args[2])) || ("-r".equals(args[0]) && "-n".equals(args[2]))){
                //匹配参数数值是否为数字
                if(args[1].matches("^[0-9]*$ ") && args[3].matches("^[0-9]*$ ")) {
                    if (Integer.parseInt(args[1]) <= 0 || Integer.parseInt(args[3]) <= 0) {
                        if("-n".equals(args[0])){
                            number = Integer.parseInt(args[0]);
                            range = Integer.parseInt(args[2]);
                        } else{
                            number = Integer.parseInt(args[2]);
                            range = Integer.parseInt(args[0]);
                        }
                    } else{
                        System.out.println("参数数值要大于0");
                        System.exit(0);
                    }
                } else{
                    System.out.println("参数数值需为整数");
                    System.exit(0);
                }
            } else{
                System.out.println("参数不合法");
                System.exit(0);
            }

            List<String> exerciseList = ExerciseUtil.getExerciseList(number, range);
            StringBuilder exerciseString = new StringBuilder();
            StringBuilder answerString = new StringBuilder();

            for(String exercise : exerciseList) {
                String res = ArithmaticUtil.calculate(exercise);
                int index = exerciseList.indexOf(exercise) + 1;

                exerciseString.append(index).append(".");
                exerciseString.append(exercise).append("\n");

                answerString.append(index).append(".");
                answerString.append(res).append(".");
            }

            FileUtil.downloadFile("Exercises.txt", exerciseString.toString());
            FileUtil.downloadFile("Answers.txt", answerString.toString());

        } else{
            System.out.println("参数不合法");
            System.exit(0);
        }
    }


}
