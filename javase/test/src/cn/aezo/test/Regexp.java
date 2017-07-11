package cn.aezo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp {
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<>();
		
		String string = readFileByLines("C:/Users/smalle/Desktop/info.htm");
		
		// 按指定模式在字符串查找
		String expTable = "^(.*)(<TR align=\"center\">(.*?)</TABLE>)(.*)$"; // .*?为匹配的懒惰模式，意思是匹配除换行符外任意东西尽可能少次

		Pattern patternTable = Pattern.compile(expTable);
		Matcher matcherTable = patternTable.matcher(string);
		if (matcherTable.find()) {
			String table = matcherTable.group(3);
			
			String[] trs = table.split("</TR>");
			if(trs.length > 0) {
				for (int i = 1; i < trs.length - 1; i++) {
					String[] tds = trs[i].split("</TD>");
					if(tds.length >= 6) {
						Map<String, Object> map = new HashMap<>();
						map.put("companyNamp", tds[1].replaceAll("[(<TR>)|(TD)|(BR)]", "").trim()); // 公司中文名称
						if(tds[3].indexOf("修改") < 0) {
							map.put("num", tds[3].replaceAll("[(<TR>)|(TD)|(BR)]", "").trim()); // 编码
						} else {
							map.put("num", ""); // 编码
						}
						map.put("telNumber", tds[4].replaceAll("[(<TR>)|(TD)|(BR)]", "").trim()); // 公司电话
						map.put("contact", tds[5].replaceAll("[(<TR>)|(TD)|(BR)]", "").trim()); // 联系人
						list.add(map);
					}
				}
			}
		} else {
			System.out.println("NO MATCH");
		}
		
		System.out.println(list);
	}
	
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
    	StringBuffer stringBuffer = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	stringBuffer.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return stringBuffer.toString();
    }

}
