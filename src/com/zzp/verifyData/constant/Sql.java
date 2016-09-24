package com.zzp.verifyData.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Desc
 * Created by zzp
 * on 2016/8/17.9:09
 */
public class Sql {

    /**
     * 查询当天所有验密交易
     *
     * @return
     */
    public static String getQuerySql(String natureDate) {
        return  "SELECT INPUT_PARAMETER,OUTPUT_PARAMETER,SERVICE" +
                " FROM ACTIVITY_INSTANCE_START_LOG_A AISLA" +
                " JOIN ACTIVITY_INSTANCE_END_LOG_A AIELA" +
                " ON AISLA.ACTIVITY_INSTANCE_ID = AIELA.ACTIVITY_INSTANCE_ID" +
                " JOIN SERVICE_LOG_A SLA" +
                " ON SLA.PROCESS_INSTANCE_ID = AISLA.PROCESS_INSTANCE_ID" +
                " WHERE AISLA.TRAN_DATE = '" + natureDate + "' AND  AIELA.TRAN_DATE = '" + natureDate + "' AND SLA.TRAN_DATE = '" + natureDate + "'" +
                " AND  AISLA.OPERATION ='verifyCustomerCipher'";
    }

    public static String getSixFSql(String natureDate) {
        return  "SELECT START_DATE,START_TIME,INIT_CONTEXT,END_DATE,END_TIME,FINAL_CONTEXT" +
                " FROM PROCESS_INSTANCE_END_LOG_A PIEL" +
                " JOIN PROCESS_INSTANCE_START_LOG_A PISL" +
                " ON PIEL.PROCESS_INSTANCE_ID = PISL.PROCESS_INSTANCE_ID" +
                " WHERE PIEL.TRAN_DATE = '" + natureDate + "' AND PISL.TRAN_DATE = '" + natureDate + "'" +
                " AND  PISL.PROCESS_DEFINITION_ID IN (" +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0001'," +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0003'," +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0006'," +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0014'," +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0016'," +
                        "'coreProject/com.uisftech.bip.scene.externalSysBank.bipE0018') ";
    }

}