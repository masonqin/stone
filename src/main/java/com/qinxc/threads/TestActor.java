package com.qinxc.threads;

import akka.actor.UntypedActor;

/**
 * Created by qxc on 2018/7/16.
 */
public class TestActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {

        String msg = (String) message;
        System.out.println(msg);
    }
}
