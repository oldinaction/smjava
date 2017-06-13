package config;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class ConfigReader {
	
	public static void readConfig() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read("config/cfg.xml");
		Element game=doc.getRootElement();
		Element frame=game.element("frame");
		List<Element> layers=frame.elements("layer");
		for(Element layer:layers){
			System.out.print(layer.attributeValue("class")+",");
			System.out.print(layer.attributeValue("x")+",");
			System.out.print(layer.attributeValue("y")+",");
			System.out.print(layer.attributeValue("w")+",");
			System.out.print(layer.attributeValue("h"));
			System.out.println();
		}
	}

public static void main(String[] args) throws Exception	{
	
	readConfig();
	
}


}
