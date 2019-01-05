package com.qinxc.minirpc.serde;

import com.alibaba.fastjson.JSON;
import com.qinxc.minirpc.service.CallContract;

/**
 * @author qxc
 * @date 2018/11/30.
 */
public class JSONSerDe implements Serde<CallContract> {

    public byte[] ser(CallContract callContract) {
        return JSON.toJSONBytes(callContract);
    }

    public CallContract deser(byte[] bytes) {
        return JSON.parseObject(bytes, CallContract.class);
    }
}
