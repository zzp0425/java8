package com.zzp.verifyData.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc
 * Created by zzp
 * on 2016/8/16.15:16
 */
public class Constants {

    public static final String QUERY_FLAG = "serviceLog";

    public enum MediumVar {
        COURTER("0201", "柜面"),
        ATM("0314", "ATM"),
        UNION("0319", "银联"),
        IC("0320", "IC卡"),
        TELBANK("0315", "电话银行"),
        CAMPUS("0317", "校园一卡通"),
        IBCL_FINANCIAL("0321", "兴业理财门户"),
        IBCL_COURTER("0318", "兴业柜面通"),
        EBANK("0316", "网银");
        private String medium;
        private String desc;
        MediumVar(String medium, String desc){
            this.medium = medium;
            this.desc = desc;
        }
        public String getMedium(){

            return this.medium;
        }

        public static String getDesc(String meidum) {
            Map<String, String> vars = getMediumDesc();

            return vars.get(meidum);
        }

        private String getDesc() {
            return this.desc;
        }

        public static Map<String, String> getMediumDesc() {
            Map<String, String> map = new HashMap<>();
            for (MediumVar mediumVar : MediumVar.values()) {
                map.put(mediumVar.getMedium(), mediumVar.getDesc());
            }
            return map;
        }

    }



    public enum RegStr {
        VERIF_IN("(?:medium|cipherType|branchId|globalSeqNo|tranTellerNo|cipher|voucherId|tranTime|tranSeqNo):S=\\w{0,32}"),
        //VERIF_OUT("message:S=(\\W{0,}|\\w{0,})(?=,\\w{0,}:=)"),
        VERIF_OUT("(?:message):S=(\\W{0,}|\\S{0,})(?=,parameterList)"),
        SIXF_IN("(?:PP0030|PP0020|PP0120|PP070A|PP1070|PP0150|PP060C|PP0891):S=\\w{0,32}"),
        SIXF_OUT("(?:returnMsg):S=(\\W{0,}|\\S{0,})(?=,backendSeqNo)"),
        DELIM(":S=");

        private String reg;
        RegStr(String reg) {
            this.reg = reg;
        }

        public String getReg() {
            return this.reg;
        }
    }


    public static void main(String[] args) {
        String info = "{outputParameters:HM={SvcBody:HM={PP124A:S=,PP0643:S=,PP0642:S=,PP1249:S=,PP1244:S=,PP0410:S=帐号1部分冻结或止付,PP124E:S=,PP0641:S=,PP124F:S=,PP1245:S=},RspSvcHeader:HM={returnCode:S=KCA7,tranDate:S=20160104,returnMsg:S=取款金额超限\\,帐户可用余额不足,backendSeqNo:S=746622}},ME015012304562708311:HM={message:S=success,code:S=success,type:S=S},returnCode:S=S0000,inputParameters:HM={SvcBody:HM={PP0030:S=E0003,PP0040:S=0000000000055000,PP0623:=,PP0020:S=6221359003100000311,PP0120:S=746622,PP0621:=,PP1221:S=0,PP0607:S=0003,PP070A:S=821B3EF9A678EE62EA7C6A0B962882D8,PP0881:S=0000000000000000,PP0070:S=20160819172118,PP0891:S=9999014020010112,PP0892:S=9999014020010111,PP1070:S=947,PP070M:S=1,PP060Z:=,PP0360:S=996266666666666604=1561560000000000000003976999236000002070000000000000000000000=000000000000=00,PP0350:S=6221359003100000311=000020168430539,PP076A:S=1,PP060C:S=Y},ReqSvcHeader:HM={tranSeqNo:S=746622,tranDate:S=20160818,branchId:S=9999,tranTellerNo:S=UPAY}},ME015012304410308310:HM={flag:S=4,PP0020:S=6221359003100000311,dateString:S=17223867,medium:S=0319},publicParamters:HM={FOREGROUND_SEQUENCE_NUMBER:S=746622,SYSTEM_ID:S=,TRAD_DATE:S=20160818,CHANNEL_ID:S=,TELLER_ID:S=UPAY,ORGANIZATION_ID:S=9999,SERVICE_TYPE:S=01,SERVICE_NAME:S=bipE0003,BACKGROUND_SEQUENCE_NUMBER:S=00000000000000000000000019996442,GLOBAL_SEQUENCE_NUMBER:S=,DOMAIN_ID:S=},ME015012306200608353:HM={message:S=success,code:S=success,type:S=S}}";
        String reg = "(?:returnMsg):S=(\\W{0,}|\\S{0,})(?=,backendSeqNo)";

        //info = info.replaceAll("\\\\", "");
        System.out.println(info);

        Matcher matcher = Pattern.compile(reg).matcher(info);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
