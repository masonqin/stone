package com.qxc.dubbo;

import com.alibaba.dubbo.config.*;

public class ProviderDubboServer {

    public void init() {

        try {

            ProviderDubboServer providerDubboServer = new ProviderDubboServer();

            //Impl
            DemoService demoService = new DemoServiceImpl();

            //Application
            ApplicationConfig application = new ApplicationConfig();
            application.setName("demo-provider");

            //Registry
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress("localhost:2181");
            registry.setProtocol("zookeeper");
//            registry.setGroup("DubboRegistryGroup");

            //Protocol
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName("dubbo");
            protocol.setPort(20880);
//            protocol.setThreads(200);

            ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<DemoService>();
            demoServiceConfig.setApplication(application);
            demoServiceConfig.setRegistry(registry);
            demoServiceConfig.setProtocol(protocol);
            demoServiceConfig.setInterface(DemoService.class);
            demoServiceConfig.setRef(demoService);
            demoServiceConfig.setVersion("1.0.0");
            demoServiceConfig.export();
            providerDubboServer.start();

            System.out.println("Starting");

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            close();
        }));
    }

    public void close() {
        System.out.println("Shutting");
        RegistryConfig.destroyAll();
        ProtocolConfig.destroyAll();
    }

    public static void main(String[] args) {
        ProviderDubboServer providerDubboServer = new ProviderDubboServer();
        providerDubboServer.init();
    }
}
