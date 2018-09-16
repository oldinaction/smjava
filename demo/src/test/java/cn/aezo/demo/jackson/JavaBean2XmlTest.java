package cn.aezo.demo.jackson;

import cn.aezo.utils.base.MiscU;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by smalle on 2017/3/24.
 */
public class JavaBean2XmlTest {

    @Test
    public void writeObject2Xml() {
        XmlMapper xml = new XmlMapper();

        try {
            StringWriter sw = new StringWriter();

            //javaBean转换成xml
            AccountBean bean = new AccountBean();
            bean.setId(1);
            bean.setName("smalle");
            bean.setAddress("china");
            bean.setHobbys(MiscU.Instance.toList(new HobbyBean("game", 1)));

            // xml.writeValue(sw, bean); // 直接写出
            // 默认美化后写出
            xml.writerWithDefaultPrettyPrinter().writeValue(sw, bean);
            System.out.println(sw.toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // @JsonNaming(MyPropertyNamingStrategy.class)
    // @JsonRootName(value = "root") // 当此bean为根节点时，此bean对应节点名称
    // @JsonIgnoreProperties({"id"}) // 忽略的字段，以逗号分隔
    // @JsonPropertyOrder({ "name", "id" }) // 生成json对应key的顺序
    public class AccountBean {
        private int id;
        // @JsonProperty("name") // value为生成的xml标签名
        private String name;
        private String email;
        private String address;
        @JsonProperty("my_hobbys")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<HobbyBean> hobbys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<HobbyBean> getHobbys() {
            return hobbys;
        }

        public void setHobbys(List<HobbyBean> hobbys) {
            this.hobbys = hobbys;
        }

        @Override
        public String toString() {
            return this.name + "#" + this.id + "#" + this.address + "#" + this.hobbys + "#" + this.email;
        }
    }

    @JsonNaming(MyPropertyNamingStrategy.class)
    public class HobbyBean {
        private String hobby;
        private int index;

        public HobbyBean() {
        }

        public HobbyBean(String hobby, int index) {
            super();
            this.hobby = hobby;
            this.index = index;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return this.hobby;
        }
    }

    public static class MyPropertyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
        @Override
        public String translate(String input) {
            if (input == null) return input;
            return input.toUpperCase();
        }

    }
}
