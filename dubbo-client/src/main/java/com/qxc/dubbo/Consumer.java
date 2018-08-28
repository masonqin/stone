package com.qxc.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class Consumer {

    public static void main(String[] args) {

        //Application
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-consumer");

        //Registry
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("localhost:2181");
        registry.setProtocol("zookeeper");
//        registry.setGroup("DubboRegistryGroup");

        //Reference
        ReferenceConfig<DemoService> demoServiceRef = new ReferenceConfig<>();
        demoServiceRef.setApplication(application);
        demoServiceRef.setRegistry(registry);
        demoServiceRef.setInterface(DemoService.class);
        demoServiceRef.setVersion("1.0.0");

        DemoService service = demoServiceRef.get();

        while (true) {
            try {
                Thread.sleep(1000);
                String hello = service.sayHello("consumer name"); // call remote method
                System.out.println(hello); // get result
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }
}
