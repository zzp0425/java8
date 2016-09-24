package com.zzp.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desc
 * Created by zzp
 * on 2016/8/10.9:44
 */
public class GenerateEodData {
    private static String date = "";
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.set(2016, 7, 12, 10, 0, 0);
        for (int jobId = 1007002; jobId < 1007102; jobId++) {
            calendar.add(Calendar.SECOND, 30);
            date = format.format(calendar.getTime());
            tellerStatusSync(jobId++);
            tellerUnUseSync(jobId++);
            cleanFailUreVerify(jobId);
        }

    }

    public static void tellerStatusSync(int jobId) {
        String serviceName = "tellerStatusSync_Eod";
        String recurrencceId = "600";
        soutSql(jobId, serviceName, recurrencceId);

    }

    public static void tellerUnUseSync(int jobId) {
        String serviceName = "tellerStartUsingSetting_Eod";
        String recurrencceId = "601";
        soutSql(jobId, serviceName, recurrencceId);
    }

    public static void cleanFailUreVerify(int jobId) {
        String serviceName = "cleanFailureVerify_Eod";
        String recurrencceId = "602";
        soutSql(jobId, serviceName, recurrencceId);
    }

    private static void soutSql(int jobId, String serviceName, String recurrencceId) {
        System.out.println("insert into job_sandbox(JOB_ID, JOB_NAME, RUN_TIME, POOL_ID, SERVICE_NAME, RUN_AS_USER, RECURRENCE_INFO_ID, RUN_BY_INSTANCE_ID) " +
                "values('" + jobId +"', '" + serviceName + "', '" + date +"', 'pool', '" + serviceName + "', 'system', '" + recurrencceId + "', 'bosent1');");
    }
}
