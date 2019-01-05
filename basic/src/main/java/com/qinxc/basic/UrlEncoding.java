package com.qinxc.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.scene.SubScene;

import java.net.URLEncoder;

public class UrlEncoding {

    public static String getEncodeUrl() throws Exception {

        String extra = "8XQ8bD8Z%2FZEEyKJK6virjdVRWczF%2FgQxuonrM8OBfRfz9CHGV4fyfiRzrnT9RnFJeNpTP7R%2F%2BRlRc%2BgTqhDmOqq29ho9qbZBCSuFcxJy5S3CNggup%2Bgu7Lbdx3NOvRSU87Xc7GM%2BgA%2BJ8wOoMLcYLP1jo0Ngyf6XS7RPPFpqsgVqVVbSCmjpmbMnoP7P1ynaOddqAqyNIOn1QSjfzAAqmMCOLbLaSxRYUez2Jx2KfqufofDVeEc7uPwKJA0ITmEvrbp%2Fqqa8xCHWOv%2FnHMMpng91ZXF1Zj2yb7sHiktFveYial5WnJGppw8BL6Y%2BBt21sJBoMdl73igtD2ePu3nDfe2mTeN4KAk85BddwEMv68C5nhR%2FRkTLSnHDP39%2FNBDnvDFDh0sOYNhw%2B8LywFjTIFuvIdw2Sqb%2FCcpo4wRiEagDhc%2Fn1N3Chorrd%2B3aNLGyXZe7WQWwdOg7T4%2BxXwc7Dg%2B5%2FaLYB7FEsbHHYaeudyOvl7liRMjrKVe5yw%2BZktjUQsuD%2Bl%2FEHdX4y0%2FQTelQx2Up30wkzy6wcLgydrJRd4kG0m7beqUHy3Rz1L29w1wUjzm%2BN%2FIt2LTMOzOk5Zx4DxKamX61NdwxFRLJf82Ird3l%2BXzmCEcFDD%2BMMVIyGG%2F2rIakcIoI%2F1yUjTF0z67qqjz9iWRNBI41VtuMnio3W6BhFJFvwO5XkZmuiK3RMZAPBY%2FWPFjgU%2B4E7RKIWv4Szohhca94uG5lUuOihROzMK%2BF1aMH%2FzM9AjVfLLBSetmjZy7%2BdXWP0CiVeiHRdj%2FDRvAUEbq4cYbDMpr473ihd2l%2FqTnuoqM928LHI235EPAJwfaERgCdHclGBAquCFkHD2bZO8p0lkC%2BFClCiLfQUsgZ7%2FhLi9OosoubUPVYv2wKKvAuI5e%2BWwo4LKjpZlN4r0mSq8fTrf7QVp8rzQl%2BJNs6LFNbAqZOhZv3yNtzBAxsysnFAXFSzxtfJffSjQwG931%2BpnVHwpy%2FsfeG1QEqs36V94P9rkvB3aDgTjEWgwQpsMo5NfipUrLjlAMkLWXzRdKmbVdOTe%2BLsL4BBAEkfFuOml4%2Fyl2olWYqB2VYiF%2Bm%2BrNkujnwLVUH76OjTC3F8n9lduPvRE87v3IfhuHF%2BJKWZy2rP7IYz1r%2FmUIxpmTWJJFSSx4hTwUubsWHb5b4WRWXW3X5QrrEyS3GbWc427V53soRyELQQkhAN%2BYG3gC%2FZp9X%2BNwb8gPYCBIXAyRzow%3D%3D";

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        node.put("event_type", 4);
        node.put("app_version", "1.1.1");
        node.put("os_version", "1.1.1");
        node.put("os_api", 1);
        node.put("mac", "3C:D0:F8:2C:99:A2");
        node.put("sim_op", -1);
        node.put("width", 1280);
        node.put("height", 720);
        node.put("root", 0);
        node.put("manufacturer", "xiaomi");
        node.put("udid", "");
        node.put("idfa", "");
        node.put("openudid", "");
        node.put("nt", 1);
        node.put("device_model", "5X");
        node.put("device_brand", "xiaomi");
        node.put("device_manufaturer", "xiaomi");
        node.put("language", "zh");
        node.put("build_serial", "a");
        node.put("rom", "a");
        node.put("cpu_abi", "a");
        node.put("display_density", "a");
        node.put("device_id", "a");
        node.put("video_play_time", 12);

        System.out.println("Json node text : " + node.toString());

        return URLEncoder.encode(node.toString(), "UTF-8") + "&extra=" + extra;

    }


    public static void main(String args[]) throws Exception {

        //System.out.println("Encode json:" + getEncodeUrl());
//        String resolution = "1280x720";
//        String w = resolution.toUpperCase();
//        System.out.println(w);
//        System.out.println(Integer.parseInt(w.substring(0,resolution.indexOf("x"))));
//        System.out.println(Integer.parseInt(w.substring(resolution.lastIndexOf("x")+1,resolution.length())));

    }

}
