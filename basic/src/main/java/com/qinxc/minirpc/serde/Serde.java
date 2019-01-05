package com.qinxc.minirpc.serde;


/**
 * @author qxc
 * @date 2018/11/30.
 */
public interface Serde<T> {

    byte[] ser(T contract);

    T deser(byte[] bytes);
}
