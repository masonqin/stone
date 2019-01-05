package com.qinxc.basic;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.XGBoost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author qxc
 * @date 2018/10/16.
 */
public class XGboostModel {

    public Logger logger = LoggerFactory.getLogger(XGboostModel.class);
    public Booster model;

    public static void main(String[] args) {
        XGboostModel xGboostModel = new XGboostModel("model/crm.model");
        System.out.println(xGboostModel.toString());
    }

    public XGboostModel(String modelFilePath) {
        try {
            setModelFromByte(new FileInputStream(modelFilePath));
//            logger.info("new XGboostModel success, modelFilePath:{}", modelFilePath);
        } catch (Exception e) {
            String errorMsg = String.format("new XGboostModel has error modelFilePath:%s", modelFilePath);
//            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg);
        }
    }

    private void setModelFromByte(InputStream modelInputStream) {
        try {
            this.model = XGBoost.loadModel(modelInputStream);
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
