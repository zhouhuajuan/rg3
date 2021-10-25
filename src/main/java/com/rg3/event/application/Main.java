package com.rg3.event.application;

import com.rg3.event.util.ArithmaticUtil;
import com.rg3.event.util.ExerciseUtil;
import com.rg3.event.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zbr
 */
public class Main {

//    @Test
//    public void test(){
//        String[] args = new String[4];
//        args[0] = "-e";
//        args[1] = "target/Exercises.txt";
//        args[2] = "-a";
//        args[3] = "target/Answers.txt";
//        args[0] = "-n";
//        args[1] = "1000";
//        args[2] = "-r";
//        args[3] = "10";
//        main(args);
//    }
    public static void main(String[] args) {
        int length = args.length;


        if (length == 4) {
            if (("-n".equals(args[0]) && "-r".equals(args[2])) || ("-r".equals(args[0]) && "-n".equals(args[2]))) {
                int number = 0;
                int range = 0;
                //匹配参数数值是否为数字
                if (args[1].matches("^[0-9]*$") && args[3].matches("^[0-9]*$")) {
                    if (Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[3]) > 0) {
                        if ("-n".equals(args[0])) {
                            number = Integer.parseInt(args[1]);
                            range = Integer.parseInt(args[3]);
                        } else {
                            number = Integer.parseInt(args[1]);
                            range = Integer.parseInt(args[3]);
                        }
                        List<String> exerciseList = ExerciseUtil.getExerciseList(number, range);
                        StringBuilder exerciseString = new StringBuilder();
                        StringBuilder answerString = new StringBuilder();

                        //for(String exercise : exerciseList) {
                        for (int i = 0; i < exerciseList.size(); i++) {
                            String res = ArithmaticUtil.calculate(exerciseList.get(i));
                            //int index = exerciseList.indexOf(exercise) + 1;

                            exerciseString.append(i + 1).append(".");
                            exerciseString.append(exerciseList.get(i)).append("\n");

                            answerString.append(i + 1).append(".");
                            answerString.append(res).append("\n");

                        }
                        FileUtil.downloadFile("Exercises.txt", exerciseString.toString());
                        FileUtil.downloadFile("Answers.txt", answerString.toString());
                    } else{
                        System.out.println("参数数值要大于0");
                        System.exit(0);
                    }
                } else {
                        System.out.println("参数数值需为整数");
                        System.exit(0);
                    }
                }else if (("-e".equals(args[0]) && "-a".equals(args[2])) || ("-e".equals(args[2]) && "-a".equals(args[0]))) {
                String answerPath = null;
                String exercisePath = null;

                if ("-e".equals(args[0])) {
                    answerPath = args[3];
                    exercisePath = args[1];
                } else {
                    answerPath = args[1];
                    exercisePath = args[3];
                }

                List<String> exerciseList = FileUtil.readFile(exercisePath);
                List<String> answerList = FileUtil.readFile(answerPath);
                List<Integer> correctList = new ArrayList<>();
                List<Integer> wrongList = new ArrayList<>();

                //for(String exercise : exerciseList){
                for (int i = 0; i < exerciseList.size(); i++) {
                    String correctAnswer = ArithmaticUtil.calculate(exerciseList.get(i));
                    //int index = exerciseList.indexOf(exercise);
                    if (correctAnswer.equals(answerList.get(i))) {
                        correctList.add(i + 1);
                    } else {
                        wrongList.add(i + 1);
                    }
                }

                StringBuilder correctString = new StringBuilder("Correct:");
                correctString.append(correctList.size()).append("(");
                StringBuilder wrongString = new StringBuilder("Wrong:");
                wrongString.append(wrongList.size()).append("(");

                for (Integer i : correctList) {
                    correctString.append(i);
                    if (correctList.indexOf(i) != correctList.size() - 1) {
                        correctString.append(",");
                    }
                }
                correctString.append(")");

                for (Integer i : wrongList) {
                    wrongString.append(i);
                    if (wrongList.indexOf(i) != wrongList.size() - 1) {
                        wrongString.append(",");
                    }
                }
                wrongString.append(")");

                FileUtil.downloadFile("Grade.txt", correctString.toString() + "\n" + wrongString.toString());
            } else {
                    System.out.println("参数不合法");
                    System.exit(0);
                }

            } else {
                System.out.println("参数不合法");
                System.exit(0);
            }
        }

    }
