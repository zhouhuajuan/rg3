package com.rg3.event.util;

import com.rg3.event.exception.FileNotExistsException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        if (!file.exists()){
            throw new FileNotExistsException("文件路径错误，不存在");
        }
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
}
