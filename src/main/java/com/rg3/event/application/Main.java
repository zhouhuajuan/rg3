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

    public static void main(String[] args) {
        int length = args.length;


        if(length == 4){
            if(("-n".equals(args[0]) && "-r".equals(args[2])) || ("-r".equals(args[0]) && "-n".equals(args[2]))){
                int number = 0;
                int range = 0;
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
                        List<String> exerciseList = ExerciseUtil.getExerciseList(number, range);
                        StringBuilder exerciseString = new StringBuilder();
                        StringBuilder answerString = new StringBuilder();

                        //for(String exercise : exerciseList) {
                        for(int i=0;i<exerciseList.size();i++) {
                            String res = ArithmaticUtil.calculate(exerciseList.get(i));
                            //int index = exerciseList.indexOf(exercise) + 1;

                            exerciseString.append(i+1).append(".");
                            exerciseString.append(exerciseList.get(i)).append("\n");

                            answerString.append(i+1).append(".");
                            answerString.append(res).append(".");
                        }

                        FileUtil.downloadFile("Exercises.txt", exerciseString.toString());
                        FileUtil.downloadFile("Answers.txt", answerString.toString());
                    } else{
                        System.out.println("参数数值要大于0");
                        System.exit(0);
                    }
                } else if(("-e".equals(args[0]) && "-a".equals(args[2])) || ("-e".equals(args[0]) && "-a".equals(args[2]))){
                    String answerPath = null;
                    String exercisePath = null;

                    if("-e".equals(args[0])){
                        answerPath = args[3];
                        exercisePath = args[1];
                    } else{
                        answerPath = args[1];
                        exercisePath = args[3];
                    }

                    List<String> exerciseList = FileUtil.readFile(exercisePath);
                    List<String> answerList = FileUtil.readFile(answerPath);
                    List<Integer> correctList = new ArrayList<>();
                    List<Integer> wrongList = new ArrayList<>();

                    //for(String exercise : exerciseList){
                    for (int i=0;i<exerciseList.size();i++){
                        String correctAnswer = ArithmaticUtil.calculate(exerciseList.get(i));
                        //int index = exerciseList.indexOf(exercise);
                        if (correctAnswer.equals(answerList.get(i+1))) {
                            correctList.add(i + 1);
                        }else{
                            wrongList.add(i + 1);
                        }
                    }

                    StringBuilder correctString = new StringBuilder("Correct:");
                    correctString.append(correctList.size()).append("(");
                    StringBuilder wrongString = new StringBuilder("Wrong:");
                    wrongString.append(wrongList.size()).append("(");

                    for (Integer i : correctList){
                        correctString.append(i);
                        if(correctList.indexOf(i) != correctList.size() - 1){
                            correctString.append(",");
                        }
                    }
                    correctString.append(")");

                    for(Integer i : wrongList){
                        wrongString.append(i);
                        if(wrongList.indexOf(i) != wrongList.size() - 1){
                            wrongString.append(",");
                        }
                    }
                    wrongString.append(")");

                    FileUtil.downloadFile("Grade.txt", correctString.toString() + "\n" + wrongString.toString());
                } else{
                    System.out.println("参数数值需为整数");
                    System.exit(0);
                }
            } else{
                System.out.println("参数不合法");
                System.exit(0);
            }



        } else{
            System.out.println("参数不合法");
            System.exit(0);
        }
    }
//    @Test
//    public void test(){
//        List<String> exerciseList = new ArrayList<>();
//        exerciseList.add("1 + 2 =");
//        exerciseList.add("3 × 4 =");
//
//        List<String> answerList = new ArrayList<>();
//        answerList.add("3");
//        answerList.add("1");
////        List<String> exerciseList = FileUtil.readFile(exercisePath);
////        List<String> answerList = FileUtil.readFile(answerPath);
//        List<Integer> correctList = new ArrayList<>();
//        List<Integer> wrongList = new ArrayList<>();
//
//        for(String exercise : exerciseList){
//            String correctAnswer = ArithmaticUtil.calculate(exercise);
//            int index = exerciseList.indexOf(exercise);
//            if (correctAnswer.equals(answerList.get(index))) {
//                correctList.add(index + 1);
//            }else{
//                wrongList.add(index + 1);
//            }
//        }
//
//        StringBuilder correctString = new StringBuilder("Correct:");
//        correctString.append(correctList.size()).append("(");
//        StringBuilder wrongString = new StringBuilder("Wrong:");
//        wrongString.append(wrongList.size()).append("(");
//
//        for (Integer i : correctList){
//            correctString.append(i);
//            if(correctList.indexOf(i) != correctList.size() - 1){
//                correctString.append(",");
//            }
//        }
//        correctString.append(")");
//
//        for(Integer i : wrongList){
//            wrongString.append(i);
//            if(wrongList.indexOf(i) != wrongList.size() - 1){
//                wrongString.append(",");
//            }
//        }
//        wrongString.append(")");
//
//        FileUtil.downloadFile("Grade.txt", correctString.toString() + "\n" + wrongString.toString());
//
//    }

}
