package com.mcw.rpc.server.api.request;

import java.io.Serializable;
import java.util.Arrays;

public class RequestInfo implements Serializable {
    private static final long serialVersionUID = -6443430915290891961L;

    private String className;
    private String methodName;
    private Class[] paramsType;
    private Object[] params;

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

    public Class[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramsType=" + Arrays.toString(paramsType) +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
