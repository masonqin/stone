package com.qinxc.basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author qxc
 * @date 2018/10/11.
 */
public class FileOperation {

    public static FileInputStream inputStream;
    public static BufferedReader bufferedReader;

    public static void main(String[] args) {
        String filePath = "/Users/qxc/Documents/block/BlockFeature_2018-10-09";
        initHandler(filePath);
        String str;
        try {
            while ((str = bufferedReader.readLine()) != null) {
            }
        }catch (Exception e){

        }
        releaseFileHandler();

    }

    public static void initHandler(String filePath) {
        try {
            inputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (Exception e) {
            releaseFileHandler();
        }
    }

    public static void releaseFileHandler() {
        try {
            //close
            if (inputStream != null)
                inputStream.close();
            if (bufferedReader != null)
                bufferedReader.close();

        } catch (Exception e) {
        } finally {
            inputStream = null;
            bufferedReader = null;
        }
    }

}
