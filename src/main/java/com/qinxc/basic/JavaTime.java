package com.qinxc.basic;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author qxc
 * @date 2018/9/29.
 */
public class JavaTime {

    public static void main(String[] args) {
        //时间相关测试
        Timestamp startTime;
        Timestamp endTime;
        int DURATION = 30;
//        Timestamp followTime = parseStringToTimeStamp("2018/08/29 23:59:59", "yyyy/MM/dd hh:mm:ss");
        Timestamp followTime = null;
        if (followTime != null) {
            startTime = Timestamp.valueOf(followTime.toLocalDateTime().minusDays(DURATION).toLocalDate().atStartOfDay());
            endTime = followTime;
        } else {
            startTime = Timestamp.valueOf(LocalDateTime.now().minusDays(DURATION).toLocalDate().atStartOfDay());
            endTime = Timestamp.valueOf(LocalDateTime.now());
        }
        System.out.println(startTime);
        System.out.println(endTime);
    }

    public static Timestamp parseStringToTimeStamp(String timeStr, String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        return new Timestamp(df.parse(timeStr).getTime());
    }


}
