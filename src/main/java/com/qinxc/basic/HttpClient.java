package com.qinxc.basic;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by qxc on 2018/7/25.
 */
public class HttpClient {

    public static void main(String[] args) {

        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");

        String strUrl = "http://apptest.winasdaq.com/v/v2.do?code_id=a10009&os_ver=10.3.2&app_pkg=com.soulgame.cw&app_ver=8.3&device_type=1&os_type=2&vendor=Apple&model=iPhone&idfa=D081F4E0-708D-44DF-A6D1-C974BA36B671&mac=3C:D0:F8:2C:99:A2&sw=320&sh=568&ip=220.231.14.153&ot=0&ct=101";
        try {
            URL u = new URL(strUrl);
            try {
                try (InputStream in = new BufferedInputStream(u.openStream())) {
                    InputStreamReader theHTML = new InputStreamReader(in);
                    int c;
                    while ((c = theHTML.read()) != -1) {
                        System.out.print((char) c);
                    }
                }
            } catch (MalformedURLException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static String encodeQueryString(String queryString) {
        StringBuilder sb = new StringBuilder();
        String[] split = queryString.split("&");
        for (String str : split) {
            String[] split1 = str.split("=");
            try {
                sb.append(URLEncoder.encode(split1[0], "utf-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(split1[1], "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append("&");
        }
        String s = sb.toString();
        return s.substring(0,s.length() - 1);
    }

    public static String encodeUrl(String urlStr) {
        int index = urlStr.lastIndexOf("?")+1;
        return urlStr.substring(0, index) + encodeQueryString(urlStr.substring(index));
    }
}
