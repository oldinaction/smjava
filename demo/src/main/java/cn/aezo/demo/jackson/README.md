## 介绍

1. [http://www.studytrails.com/tag/jackson/](http://www.studytrails.com/tag/jackson/)
2. [http://www.baeldung.com/jackson](http://www.baeldung.com/jackson)
3. [http://zzc1684.iteye.com/blog/2209692 (Zh)](http://zzc1684.iteye.com/blog/2209692)

### dependence

1. 解析json

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson.version}</version>
</dependency>
```

2. 解析xml

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.8.5</version>
</dependency>
```

部分jar说明

- jackson.core 核心
- jackson-annotations 注解通用(xml、json)
- jackson-databind 数据绑定通用(xml、json)
- jackson-module-jaxb-annotations 支持jaxb注解

## 注解

### 序列化注解(javaBean -> json/xml)

- `@JsonAnyGetter`
- `@JsonGetter` 注明该属性的getter方法，否则去默认getter
- `@JsonPropertyOrder({ "name", "id" })` 生成json/xml对应key的顺序
- `@JsonRawValue` 可将一个字段的值('{"attr" : "hello"}')解析成该字段的子节点(默认解析成该字段的值)
- `@JsonValue`
- `@JsonRootName(value = "myroot")` 当此bean为根节点时，可设置此javaBean根节点的名字
- `@JsonSerialize(using = MySerializer.class)` 可将某个字段的值进行格式化, 如传入的日期类型为long型, 在MySerializer(extends JsonSerializer)的方法中可将其转换成常用日期格式.(作用于字段)

### 反序列化注解(json/xml -> javaBean)

- `@JsonCreator`
- `@JacksonInject`
- `@JsonAnySetter`
- `@JsonSetter` 类似`@JsonGetter`
- `@JsonDeserialize` 类似`@JsonSerialize`

### 字段控制注解

- `@JsonIgnoreProperties({"id"})` 忽略的字段，以逗号分隔(作用于类)
- `@JsonIgnore` 忽略该字段(作用于字段)
- `@JsonIgnoreType` 可忽略某个类型的所有属性. 如忽略Father下的Child类型(作用与Child类上), 则Child的所有属性将被忽略
- `@JsonInclude(Include.NON_NULL)` 注解是否包含几种类型的值empty/null/default(作用于类)
- `@JsonAutoDetect`

### 多态类型注解

- `@JsonTypeInfo`
- `@JsonSubTypes`
- `@JsonTypeName`

### 其他注解

- `@JsonNaming(MyPropertyNamingStrategy.class)` 自定义此javaBean序列号时字段的命名策略, 优先级小于`@JsonProperty`
- `@JsonProperty`
    - 作用于字段, `@JsonProperty("NAME")` 表明该字段解析后的xml/json节点名
    - 作用与非默认的get/set方法, 表明此方法为该字段的解析时使用的get/set方法
- `@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")` 可格式化值为日期或者时间的字段
- `@JsonUnwrapped` 不包含某个字段
- `@JsonView` 和方法writerWithView合用, 可指明只解析该视图的字段
- `@JsonManagedReference`
- `@JsonBackReference`
- `@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")` 指明该类序列化是对应的主键生成器
- `@JsonFilter` 表明在解析的时候会用到一个过滤器(可动态过滤显示或不现实某些字段)

### 定制化注解

定义如下注解后，之后即可使用注解`@CustomAnnotation`

```java
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "name", "id" })
public @interface CustomAnnotation {}
```

### Jackson MixIn Annotations

### xml相关注解

- `@JacksonXmlElementWrapper(useWrapping = false)` 对于List/数组等字段, 默认解析成xml的时候会多加一层包裹, 此注解可去除


