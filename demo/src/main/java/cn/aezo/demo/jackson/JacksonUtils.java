package cn.aezo.demo.jackson;

import cn.aezo.utils.base.MiscU;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smalle on 2017/3/24.
 */
public class JacksonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{
        Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {
        });
        Map<String,T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String,Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)throws Exception{
        List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map,Class<T> clazz){
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * json string convert to xml string
     */
    public static String json2xml(String jsonStr)throws Exception{
        JsonNode root = objectMapper.readTree(jsonStr);
        String xml = xmlMapper.writeValueAsString(root);
        return xml;
    }

    /**
     * xml string convert to json string
     */
    public static String xml2json(String xml)throws Exception{
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
    }

    /**
     * XML To Object(此Bean不能是内部类)
     *
     * @param xmlPath
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(String xmlPath, Class<T> cls) throws IOException {
        T obj = xmlMapper.readValue(new File(xmlPath), cls);
        return obj;
    }

    public static <T> T xmlToBean2(String xml, Class<T> cls) throws IOException {
        T obj = xmlMapper.readValue(xml, cls);
        return obj;
    }

    /**
     * @param xmlFile
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(File xmlFile, Class<T> cls) throws IOException {
        T obj = xmlMapper.readValue(xmlFile, cls);
        return obj;
    }

    /**
     * @param xmlInputStream
     * @param cls
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T xmlToBean(InputStream xmlInputStream, Class<T> cls) throws IOException {
        T obj = xmlMapper.readValue(xmlInputStream, cls);
        return obj;
    }



    public static void main(String[] args) throws Exception {
        Map<String, Object> map = MiscU.Instance.toMap("a", 1, "b", "bb", "c", MiscU.Instance.toList("DD", "EE"));
        String s = obj2json(map);
        System.out.println("obj2json(map) = " + s);

        System.out.println("json2map(s) = " + json2map(s));

    }
}
