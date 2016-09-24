package com.zzp.verifyData.dao;

import com.zzp.verifyData.constant.Constants;
import com.zzp.verifyData.constant.Sql;
import com.zzp.verifyData.db2.DB2Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.zzp.verifyData.constant.Constants.RegStr;
import static com.zzp.verifyData.constant.Constants.MediumVar;

/**
 * Desc
 * Created by zzp
 * on 2016/8/17.9:08
 */
public class OperateBipLog {


    public static List<Map<String, Object>> queryData(String natureDate, String type) {
        List<Map<String, Object>> infos = null;
        String sql = Constants.QUERY_FLAG.equals(type) ? Sql.getSixFSql(natureDate) : Sql.getQuerySql(natureDate);
        try(
                Connection connection = DB2Connection.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet set = statement.executeQuery(sql)) {


            infos = Constants.QUERY_FLAG.equals(type) ?
                    generateData(set, "INIT_CONTEXT",    RegStr.SIXF_IN.getReg(),  "FINAL_CONTEXT",    RegStr.SIXF_OUT.getReg()):
                    generateData(set, "INPUT_PARAMETER", RegStr.VERIF_IN.getReg(), "OUTPUT_PARAMETER", RegStr.VERIF_OUT.getReg());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infos;
    }

    /**
     * 查询当天所有的验密交易的数据
     */
    public static List<Map<String, Object>> generateData(ResultSet set, String... type) throws SQLException {

        String split = RegStr.DELIM.getReg();
        String inputParam, outputParam;
        List<Map<String, Object>> infos = new ArrayList<>();
        while (set.next()) {
            Map<String, Object> info = new HashMap<>();
            inputParam = set.getString(type[0]);
            Matcher matcher = Pattern.compile(type[1]).matcher(inputParam);
            while (matcher.find()) {
                String[] machers = matcher.group().split(split);
                info.put(machers[0], machers.length > 1 ? machers[1] : "");
            }

            outputParam = set.getString(type[2]);

            if (outputParam != null) {
                matcher = Pattern.compile(type[3]).matcher(outputParam.replaceAll("\\\\", ""));
                while (matcher.find()) {
                    info.put(matcher.group().split(split)[0], matcher.group().split(split)[1]);
                }
            }
            if (!"INIT_CONTEXT".equals(type[0])) {
                info.put("service", set.getString("SERVICE"));
            } else {
                info.put("START_DATE", set.getString("START_DATE"));
            }
            infos.add(info);
        }
        return infos;
    }

    public static void putInfo(String param, Map<String, Object> info, Matcher matcher, String reg) {
        matcher = Pattern.compile(reg).matcher(param);
        while (matcher.find()) {
            info.put(matcher.group().split(RegStr.DELIM.getReg())[0], matcher.group().split(RegStr.DELIM.getReg())[1]);
        }
    }

    public static void print(Stream<Map<String, Object>> infos) {
        infos.forEach(map -> {
                    map.forEach((key, value) -> System.out.print(value + "\t"));
                    System.out.println();
                });
    }
}
