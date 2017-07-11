package cn.aezo.test;

import java.util.ArrayList;
import java.util.List;

public class GouLv {
	public static void main(String[] args) throws Exception {
		List<String> urls = new ArrayList<String>();
		urls.add("http://www.baidu.com");
		urls.add("http:/#/www.baidu.com");
		urls.add("http://www#.zxitb.com");
		urls.add("http://www.zxitb.com");
			
		int index;
		for (int i = 0; i < urls.size(); i++) {
			index = -1;
			String url = urls.get(i);
			index = url.indexOf('#');
			if(index != -1){
				System.out.println("有#的url: " + url);
				urls.remove(url);
				i-- ;
			}else {
				System.out.println("没有#的url: " + url);
			}
		}
		
		for (int i = 0; i < urls.size(); i++) { //打印最终结果
			System.out.println("去除#后的url : " + urls.get(i));		
		}
	}
	
}



