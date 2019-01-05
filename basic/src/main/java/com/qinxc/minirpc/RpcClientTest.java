package com.qinxc.minirpc;

import com.qinxc.minirpc.proxy.RpcClientProxy;
import com.qinxc.minirpc.service.DemoService;
import com.qinxc.minirpc.service.DemoServiceClient;
import com.qinxc.minirpc.service.Parameter;
import com.qinxc.minirpc.service.Result;
import com.qinxc.minirpc.socket.*;
import com.zhipin.jinnang.bean.CustomerInfoBean;
import com.zhipin.jinnang.service.DataCenterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author qxc
 * @date 2018/11/30.
 */
public class RpcClientTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        DataCenterService dataCenterService = (DataCenterService) context.getBean("dataCenterService");
        CustomerInfoBean infoBean = dataCenterService.getCustomerInfo(1234);

        String host = "127.0.0.1";
        int port = 8989;
        try {
            DemoService demoService = (DemoService) RpcClientProxy.getProxy(new DemoServiceClient(), new SocketClient(host, port));
            Result ret = demoService.method_one(new Parameter());
            System.out.println("Call result:" + ret.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
