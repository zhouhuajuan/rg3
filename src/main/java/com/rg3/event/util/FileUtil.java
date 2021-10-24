package com.rg3.event.util;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
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

//    public static void downloadFile(HttpServletResponse response, List<String> outputList, String fileName) throws IOException {
//        StringBuilder sb = new StringBuilder();
//
//        for(String output : outputList) {
//            sb.append(outputList.indexOf(output) + 1);
//            sb.append(".");
//            sb.append(output);
//            sb.append("\n");
//        }
//        downloadFile(response, sb.toString(), fileName);
//    }
}
