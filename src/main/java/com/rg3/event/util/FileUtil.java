package com.rg3.event.util;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zbr
 */
public class FileUtil {


    public static void downloadZip(HttpServletResponse response, String answers, String exercises) throws UnsupportedEncodingException {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("a.zip", StandardCharsets.UTF_8.toString()));


        ZipOutputStream zos = null;
        try {
            OutputStream os = response.getOutputStream();

            zos = new ZipOutputStream(os);

            ZipEntry exerciseEntry = new ZipEntry("Exercises.txt");
            zos.putNextEntry(exerciseEntry);

            zos.write(exercises.getBytes(StandardCharsets.UTF_8));
            zos.flush();

            ZipEntry answerEntry = new ZipEntry("Answers.txt");
            zos.putNextEntry(answerEntry);
            zos.write(answers.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(zos != null) {
                try {
                    zos.finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        zos.flush();



//        os.write(bos.toByteArray());
       // os.flush();
    }

    public static void downloadFile(String fileName, String content){
        File file = new File(fileName);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static List<String> readFile(String filePath)  {
        ByteArrayOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
             bis = new BufferedInputStream(new FileInputStream(filePath));

            bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            int length = 0;
            while ((length = bis.read(buffer)) != -1){
                bos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileToList(bos.toString());
//        return null;
    }

    public static List<String> fileToList(String fileContent){
        List<String> list = new ArrayList<>();
        String[] split = fileContent.split("\n");

        for(String content : split){
            list.add(StrUtil.subBefore(content, "\r", true));
        }
        return list;
    }
    @Test
    public void test(){
//        try {
//            String res = readFile("1.txt");
//            List<String> list = new ArrayList<>();
//            String[] split = res.split("\n");
//            for (String content : split) {
//                list.add(StrUtil.subBefore(content,"\r", true));
//                System.out.println(content);
//                System.out.println();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
