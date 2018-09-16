package cn.aezo.demo.jsonlib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by smalle on 2017/12/30.
 */
public class JsonU {
    /**
     * 将josn字符串转成Map（此json字符串可解析为Map, 如果子项有List会自动解析为List）
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJsonStr2Map(String jsonStr) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List list = parseJsonStr2List(v.toString());
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * 将json字符串转成List（此json字符串可解析为List）
     * @param jsonStr
     * @return
     */
    public static List parseJsonStr2List(String jsonStr) throws Exception {
        List list = new ArrayList();
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()) {
            Object obj = it.next();
            if(obj instanceof JSONObject) {
                list.add(parseJsonStr2Map(obj.toString()));
            } else {
                list.add(obj.toString());
            }
        }

        return list;
    }

    /**
     * 将Map转成json字符串
     * @param map
     * @return
     */
    public static String parseMap2JsonStr(Map<String, Object> map) {
        return JSONObject.fromObject(map).toString();
    }

    /**
     * 通过HTTP获取JSON数据（List）
     * @param url
     * @return
     */
    public static List<Map<String, Object>> getListByUrl(String url) {
        try {
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJsonStr2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
