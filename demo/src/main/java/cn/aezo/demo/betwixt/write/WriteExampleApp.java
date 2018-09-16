package cn.aezo.demo.betwixt.write;

import cn.aezo.utils.base.MiscU;
import org.apache.commons.betwixt.io.BeanWriter;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by smalle on 2017/3/23.
 */
public class WriteExampleApp {

    /**
     * 转换javaBean为xml数据
     * 默认先读取要解析的javaBean(Person)所在目录是否有对应的.betwixt文件(Person.betwixt)，如果有则按照此模板解析，否则默认解析(如example目录)
     */
    public static final void main(String [] args) throws Exception {
        StringWriter outputWriter = new StringWriter();

        outputWriter.write("<?xml version='1.0' encoding='UTF-8' ?>\n");

        BeanWriter beanWriter = new BeanWriter(outputWriter);

        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();

        List<String> list = MiscU.Instance.toList("game", "music");
        Map<String, Object> map = MiscU.Instance.toMap("hair", "blank");
        beanWriter.write(new Person("Smalle", 18, list, map));

        System.out.println(outputWriter.toString());

        outputWriter.close();

    }

    /*
     <?xml version='1.0' encoding='UTF-8' ?>
     <一个Person对象 这个配置文件中定义的值="1.0" String类型对象_姓名="Smalle">
         <int类型对象_年龄>18</int类型对象_年龄>
         <ArrayList类型对象_爱好>
             <ArrayList中的值>game</ArrayList中的值>
             <ArrayList中的值>music</ArrayList中的值>
         </ArrayList类型对象_爱好>
         <Map类型对象_特征>
             <Map中的值>
                 <key>hair</key>
                 <value>blank</value>
             </Map中的值>
         </Map类型对象_特征>
     </一个Person对象>
     */

}
