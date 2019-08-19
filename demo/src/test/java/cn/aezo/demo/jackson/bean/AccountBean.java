package cn.aezo.demo.jackson.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

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

    @JsonProperty("my_hobbies")
    @JacksonXmlElementWrapper(useWrapping = false) // 对于List/数组等字段, 默认解析成xml的时候会多加一层包裹, 此注解可去除
    private List<HobbyBean> hobbies;

    // public AccountBean() {}

    // public AccountBean(int id, String name, String email, String address, List<HobbyBean> hobbies) {
    //     super();
    //     this.id = id;
    //     this.name = name;
    //     this.email = email;
    //     this.address = address;
    //     this.hobbies = hobbies;
    // }

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

    public List<HobbyBean> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyBean> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return this.name + "#" + this.id + "#" + this.address + "#" + this.hobbies + "#" + this.email;
    }
}
