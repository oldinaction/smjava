package cn.aezo.demo.betwixt.start;

import cn.aezo.utils.base.MiscU;
import org.apache.commons.betwixt.io.BeanWriter;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by smalle on 2017/3/23.
 */
public class WriteExampleApp {
    /*
     <?xml version='1.0' encoding='UTF-8' ?>
     <Person>
         <age>18</age>
         <feature>
             <entry>
                 <key>hair</key>
                 <value>blank</value>
             </entry>
         </feature>
         <hobbys>
             <String>game</String>
             <String>music</String>
         </hobbys>
         <name>Smalle</name>
     </Person>
     */

    /**
     * 转换javaBean为xml数据
     */
    public static final void main(String [] args) throws Exception {

        StringWriter outputWriter = new StringWriter();

        outputWriter.write("<?xml version='1.0' encoding='UTF-8' ?>\n");

        BeanWriter beanWriter = new BeanWriter(outputWriter);

        /****************下面是一些属性设定项,都不是必须的**********************/
        // 是否将字段的值以属性的形式展现：false表示以子节点的形式
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);

        // 是否禁止id属性(id属性是betwixt自动添加的，false表示禁止)
        beanWriter.getBindingConfiguration().setMapIDs(false);

        //是否以缩进方式显示
        beanWriter.enablePrettyPrint();

        // 命名映射：可实现 NameMapper 接口
        // 提供 DefaultNameMapper/DecapitalizeNameMapper 默认字段名、HyphenatedNameMapper 连字符、CapitalizeNameMapper 首字母大写、BadCharacterReplacingNMapper 错误字符替换
        // writer.getXMLIntrospector().getConfiguration().setElementNameMapper(new HyphenatedNameMapper(true, "_"));

        //如果是null就写一个空值
        // beanWriter.setWriteEmptyElements(false);

        // 当遇到ArrayList或Map时做一个包装
        beanWriter.getXMLIntrospector().getConfiguration().setWrapCollectionsInElement(true);
        /*********************属性设定项完毕*********************************/

        // 主要需要PersonBean中的getter方法，如果属性类型为集合，则会渲染成子标签
        List<String> list = MiscU.Instance.toList("game", "music");
        Map<String, Object> map = MiscU.Instance.toMap("hair", "blank");

        beanWriter.write(new Person("Smalle", 18, list, map));
        // beanWriter.write("smith", new Person("John Smith", 20, list, map)); // 自定义顶层元素名称为smith

        System.out.println(outputWriter.toString());

        // Betwixt writes fragments not documents so does not automatically close
        // writers or streams.
        // This example will do no more writing so close the writer now.
        outputWriter.close();
    }

}
