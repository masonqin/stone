package com.qinxc.basic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

/**
 * @author qxc
 * @date 2019/1/4.
 */
public class CheckTicket {


    public static Logger logger = LoggerFactory.getLogger(CheckTicket.class);
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
    public static final String ACCEPT_ENCODING = "gzip, deflate, br";
    public static final String ACCEPT_LANGUAGE = "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7";
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    public static String date = "2019-02-03";
    public static String fromStation = "BJP";
    public static String toStation = "DLT";


    public static String get(String url) throws Exception {

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Encoding", ACCEPT_ENCODING);
        con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);

        con.getContent();

        String cookieStr = "";
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        for (HttpCookie cookie : cookies) {
            cookieStr += cookie;
        }

        return cookieStr;
    }

    // HTTP GET请求
    private static void sendGet(String cookie) throws Exception {

        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?" +
                "leftTicketDTO.train_date=" + date +
                "&leftTicketDTO.from_station=" + fromStation +
                "&leftTicketDTO.to_station=" + toStation +
                "&purpose_codes=ADULT";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Encoding", ACCEPT_ENCODING);
        con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
        con.setRequestProperty("content-type", CONTENT_TYPE);
        con.setRequestProperty("cookie", cookie);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        parseJsonStr(response.toString());
    }

    public static void main(String[] args) throws Exception {

        String initUrl = "https://kyfw.12306.cn/otn/leftTicket/init";

        String cookie = get(initUrl);

//        while (true) {
        sendGet(cookie);
//            Thread.sleep(3000);

//        }
    }

    /*
        SWZ:  "9_商务座",      cO.swz_num = cK[32] ? cK[32] : "--";
        TZ:   "P_特等座",      cO.tz_num = cK[25] ? cK[25] : "--";
        ZY:   "M_一等座",      cO.zy_num = cK[31] ? cK[31] : "--";
        ZE:   "O_二等座",      cO.ze_num = cK[30] ? cK[30] : "--";
        GR:   "6_高级软卧",    cO.gr_num = cK[21] ? cK[21] : "--";
        RW:   "4_软卧",       cO.rw_num = cK[23] ? cK[23] : "--";
        SRRB: "F_动卧",       cO.srrb_num = cK[33] ? cK[33] : "--";
        YW:   "3_硬卧",       cO.yw_num = cK[28] ? cK[28] : "--";
        RZ:   "2_软座",       cO.rz_num = cK[24] ? cK[24] : "--";
        YZ:   "1_硬座",       cO.yz_num = cK[29] ? cK[29] : "--";
        WZ:   "1_无座",       cO.wz_num = cK[26] ? cK[26] : "--";
        QT:   "H_其他"        cO.qt_num = cK[22] ? cK[22] : "--";
     */

//    cO.gg_num = cK[20] ? cK[20] : "--";
//    cO.yb_num = cK[27] ? cK[27] : "--";


    public static void parseJsonStr(String response) {
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONArray resultArr = jsonObject.getJSONObject("data").getJSONArray("result");

        for (int i = 0; i < resultArr.size(); i++) {
            String item = resultArr.get(i).toString();

            String[] itemArr = item.split("\\|");

            if (!itemArr[0].equals("预定")) {
                logger.info("车次:{},\t出发时间:{},\t到达时间:{},\t历时:{},\t是否可购买:{},\t一等:{},\t二等:{},\t软卧:{},\t硬卧:{},\t硬座:{} \n",
                        itemArr[3], itemArr[8], itemArr[9], itemArr[10], itemArr[11], itemArr[31], itemArr[30], itemArr[23], itemArr[28], itemArr[29]);
            }


        }


    }

}
