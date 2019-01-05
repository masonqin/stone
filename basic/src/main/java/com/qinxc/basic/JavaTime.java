package com.qinxc.basic;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

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

        TestTimestamp test = new TestTimestamp();
        test.setName("aaa");
        test.setTimestamp(new Timestamp(1541390142000L));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", test);
        String jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);

        System.out.print(Timestamp.valueOf(LocalDateTime.now().minusDays(180).toLocalDate().atStartOfDay()));

        int currentSalary = Math.round((10 + 11)/2f);
        System.out.println(currentSalary);
    }

    public static Timestamp parseStringToTimeStamp(String timeStr, String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        return new Timestamp(df.parse(timeStr).getTime());
    }

}

class TestTimestamp {
    String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    Timestamp timestamp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

