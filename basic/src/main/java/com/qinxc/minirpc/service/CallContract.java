package com.qinxc.minirpc.service;


/**
 * @author qxc
 * @date 2018/11/29.
 */
public class CallContract {

    String className;
    String methodName;
    Object[] args;
    Result ret;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Result getRet() {
        return ret;
    }

    public void setRet(Result ret) {
        this.ret = ret;
    }
}
