package cn.aezo.demo.jackson;

import cn.aezo.demo.jackson.bean.AccountBean;
import cn.aezo.demo.jackson.bean.HobbyBean;
import cn.aezo.utils.base.MiscU;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by smalle on 2017/3/24.
 */
public class JavaBean2XmlTest {

    @Test
    public void writeObject2Xml() throws IOException {
        XmlMapper xml = new XmlMapper();

        StringWriter sw = new StringWriter();

        //javaBean转换成xml
        AccountBean bean = new AccountBean();
        bean.setId(1);
        bean.setName("smalle");
        bean.setAddress("china");
        bean.setHobbies(MiscU.Instance.toList(new HobbyBean("game", 1)));

        // xml.writeValue(sw, bean); // 直接写出
        // 默认美化后写出
        xml.writerWithDefaultPrettyPrinter().writeValue(sw, bean);
        System.out.println(sw.toString());
    }

    // 注意：xml转Bean对应的Bean不能是内部类
    @Test
    public void writeXml2Object() throws Exception {
        String xmlStr = "<AccountBean><id>1</id><name>smalle</name><email/><address>china</address><my_hobbies><HOBBY>game</HOBBY><INDEX>1</INDEX></my_hobbies></AccountBean>";
        XmlMapper xmlMapper = new XmlMapper();

        AccountBean generate = xmlMapper.readValue(xmlStr, AccountBean.class);
        System.out.println("generate:" + generate.toString());

        System.out.println(JacksonUtils.xmlToBean2(xmlStr, AccountBean.class));
    }
}
