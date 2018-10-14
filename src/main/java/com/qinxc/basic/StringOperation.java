package com.qinxc.basic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qxc
 * @date 2018/9/30.
 */
public class StringOperation {

    public static void main(String[] args) {

//        patternTest();
        parseEngineId();

        try {
            List<Integer> testNullList = null;
            for (Integer testId : testNullList) {
//                System.out.println("Test");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static List<Integer> parseEngineId() {

        String distributionStr1 = "[" +
                "{\"trafficType\":1,\"rangeMin\":\"50\",\"rangeMax\":\"99\",\"engineId\":\"100003\"}," +
                "{\"trafficType\":0,\"rangeMin\":\"0\",\"rangeMax\":\"49\",\"engineId\":\"100010\"}" +
                "]";

        String distributionStr2 = "[{\"trafficType\":0,\"rangeMin\":\"0\",\"rangeMax\":\"100\",\"engineId\":\"100007\"}]";
        String endNum = "[{\"trafficType\":1,\"endNumList\":[1,2,3,4,5,6,7,8,9,0],\"engineId\":\"1\"}]";
        List<Integer> engineList = new ArrayList<>();

        if (!StringUtils.isBlank(distributionStr2)) {
            List<RouterBean> routerBeanList = JSONObject.parseArray(endNum, RouterBean.class);
            for (RouterBean bean : routerBeanList) {
                engineList.add(bean.getEngineId());
            }
            System.out.println(engineList.toString());
            return engineList;
        } else {
            return null;
        }
    }

    public static void patternTest() {
        //正则测试
        Pattern HR_PATTERN = Pattern.compile("HR|hr|人事|人力|招聘|人资");
        String title = "经理";
        Matcher hrMatcher = HR_PATTERN.matcher(title);
//        System.out.println(hrMatcher.find());
        if (hrMatcher.find()) {
            System.out.println(hrMatcher.group());
            System.out.println("======find");
        }

        Pattern pattern = Pattern.compile(":(\\d+)");
        String target = "67236746:9378798";
        Matcher matcher = pattern.matcher(target);
        if (matcher.find() && matcher.groupCount() != 0) {
            System.out.println("Matcher find: " + Integer.parseInt(matcher.group(matcher.groupCount())));
        }

        System.out.println("Boss id : " + Integer.parseInt(target.split(":")[0]));
        System.out.println("Geek id : " + Integer.parseInt(target.split(":")[1]));

    }

}

class RouterBean {
    /**
     * trafficType 0按百分比，1按尾号
     */
    private int trafficType;
    private int rangeMin;
    private int rangeMax;
    private int engineId;
    private List<Integer> endNumList;

    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }

    public int getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(int trafficType) {
        this.trafficType = trafficType;
    }

    public int getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(int rangeMin) {
        this.rangeMin = rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }

    public List<Integer> getEndNumList() {
        return endNumList;
    }

    public void setEndNumList(List<Integer> endNumList) {
        this.endNumList = endNumList;
    }

    @Override
    public String toString() {
        return "RouterBean{" +
                "trafficType=" + trafficType +
                ", rangeMin=" + rangeMin +
                ", rangeMax=" + rangeMax +
                ", engineId=" + engineId +
                ", endNumList=" + endNumList +
                '}';
    }
}




