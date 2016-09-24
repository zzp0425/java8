package com.zzp.verifyData;

import com.zzp.verifyData.constant.Constants;
import com.zzp.verifyData.dao.OperateBipLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Desc
 * Created by zzp
 * on 2016/8/17.9:10
 */
public class Client {

    /**
     * 生成报表
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();

        String[] dates = {"2016-09-12", "2016-09-13", "2016-09-14","2016-09-15","2016-09-16","2016-09-17","2016-09-18"};//new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Stream.of(dates).forEach(client::generate);
    }

    public void generate(String date) {
        System.out.println(date);
        // List<Map<String, Object>> coreDate = OperateBipLog.queryData(date, "");
//        List<Map<String, Object>> natureDate = OperateBipLog.queryData(date, "");
//        coreDate.addAll(natureDate);

        List<Map<String, Object>> coreDateServiceLog = OperateBipLog.queryData(date, Constants.QUERY_FLAG);
//        List<Map<String, Object>> natureDateServiceLog = OperateBipLog.queryData(date, Constants.QUERY_FLAG);
//        coreDateServiceLog.addAll(natureDateServiceLog);

        Stream<Map<String, Object>> infos = coreDateServiceLog.stream();//.filter(map -> map.get("PP070A").equals("4646464646460000"));
        write2File2(infos, date, "PP060C", "PP070A", "4646464646460000");
        // write2File(coreDate.stream(), date, "medium", "message", "success");
    }


    //lambda循环
    public static void write2File(Stream<Map<String, Object>> infos, String date, String... groupingBy) {
        infos.distinct()
             .collect(Collectors.groupingBy(map -> map.get(groupingBy[0])))//按渠道号分组
             .forEach((key, vars) -> {  //key 渠道号   vralue 各渠道的验密信息列表
                 File file = new File("D:\\tools\\down\\log\\" + key + date + ".txt");
                 try (FileOutputStream fos = new FileOutputStream(file)) {
                     createNewFile(file);
                     vars.stream()
                            .collect(Collectors.partitioningBy(medium -> groupingBy[2].equals(medium.get(groupingBy[1]))))//按是否成功分组
                            .forEach((message, maps) -> {    //message ：是否成功的布尔值， maps : 验密信息列表
                              maps.forEach(
                                      field -> { //field 验密信息 map
                                          field.forEach((mediumKey, var) -> { //mediumKey: 验密信息map的key, var 验密信息map的value
                                          try {
                                              var = getString(mediumKey, var, date);
                                              fos.write((var.toString() + "\t").getBytes());
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                  });
                                  try {
                                      fos.write("\r\n".getBytes());
                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              });
                            });

                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             });
    }


    public static void write2File2(Stream<Map<String, Object>> infos, String date, String... groupingBy) {
        infos.distinct()
                .filter(desc -> desc.get("PP1070")!= null
                        && (desc.get("PP1070").equals("930")
                            || desc.get("PP1070").equals("947")
                            || desc.get("PP1070").equals("950")
                            || desc.get("PP1070").equals("960")
                            || desc.get("PP1070").equals("963")
                            || desc.get("PP1070").equals("978")) && groupingBy[2].equals(desc.get(groupingBy[1])))
                .collect(Collectors.groupingBy(map -> map.get(groupingBy[0]), Collectors.groupingBy(map -> map.get("PP1070"))))//按渠道号分组
                .forEach((key, fields) -> {  //key 渠道号   value 各渠道的验密信息列表
                    File file = new File("D:\\tools\\down\\log\\" + key + date + ".txt");

                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        createNewFile(file);
                        fields.forEach((fieldKey, vars) ->
                                vars.stream()
                                .collect(Collectors.partitioningBy(medium -> groupingBy[2].equals(medium.get(groupingBy[1]))))//按是否成功分组
                                .forEach((message, maps) -> {    //message ：是否成功的布尔值， maps : 验密信息列表
                                    maps.forEach(
                                            field -> { //field 验密信息 map
                                                field.forEach((mediumKey, var) -> { //mediumKey: 验密信息map的key, var 验密信息map的value
                                                    try {
                                                        var = getString(mediumKey, var, date);
                                                        fos.write((var.toString() + "\t").getBytes());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                                try {
                                                    fos.write("\r\n".getBytes());
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                }));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }



    private static String getString(String mediumKey, Object var, String date) {
        switch (mediumKey) {
            case "medium" :
                return Constants.MediumVar.getDesc(var.toString());
            case "cipherType" :
                return "1".equals(var) ? "交易密码" : "查询密码";
            case "tranTime" :
                String time = var.toString();
                return date + " " + time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
            case "PP060C" :
                switch (var.toString()) {
                    case "Y":
                        return "银联";
                    case "A":
                        return "ATM";
                    case "I" :
                        return "IC卡";
                    default:
                        return "";
                }
            default :
                return var.toString();
        }
    }

    private static void createNewFile(File file) throws IOException {
        if (!file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }


    public static void main1(String[] args) {
        List<Map<String, Object>> lists = getList();
        lists.stream()
                .collect(Collectors.partitioningBy(desc -> desc.get("PP1070") != null))
                .forEach((bool, infos) ->
                        infos.stream().collect(Collectors.groupingBy(map -> map.get("mediu"), Collectors.groupingBy(map -> map.get("PP1070"))))
                        .forEach((key, info) -> System.out.println(key + "\r\n" + info)));
    }

    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("mediu", "0319");
        map.put("type", "success");
        map.put("PP1070", "978");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("mediu", "0314");
        map1.put("type", "success");
        map1.put("PP1070", "978");


        Map<String, Object> map2 = new HashMap<>();
        map2.put("mediu", "0319");
        map2.put("type", "success");
        map2.put("PP1070", "978");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("mediu", "0320");
        map3.put("type", "success");
        map3.put("PP1070", "978");

        Map<String, Object> map4 = new HashMap<>();
        map4.put("mediu", "0320");
        map4.put("type", "error");
        map4.put("PP1070", "978");

        Map<String, Object> map5 = new HashMap<>();
        map5.put("mediu", "0320");
        map5.put("type", "success");
        map5.put("PP1070", "978");

        Map<String, Object> map6 = new HashMap<>();
        map6.put("mediu", "0319");
        map6.put("type", "success");
        map6.put("PP1070", "999");


        Map<String, Object> map7 = new HashMap<>();
        map7.put("mediu", "0319");
        map7.put("type", "success");
        map7.put("PP107", "999");

        Map<String, Object> map8 = new HashMap<>();
        map8.put("mediu", "0319");
        map8.put("type", "success");
        map8.put("PP1070", "978");

        Map<String, Object> map9 = new HashMap<>();
        map9.put("mediu", "0314");
        map9.put("type", "success");
        map9.put("PP1070", "888");

        lists.add(map);
        lists.add(map1);
        lists.add(map2);
        lists.add(map3);
        lists.add(map4);
        lists.add(map5);
        lists.add(map6);
        lists.add(map7);
        lists.add(map8);
        lists.add(map9);
        return lists;
    }

}
