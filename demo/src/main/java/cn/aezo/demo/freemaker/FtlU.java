package cn.aezo.demo.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by smalle on 2017/9/2.
 * freeMaker模版引擎demo
 */
public class FtlU {
    /**
     * 根据模板文件输出内容到指定的输出流中(文件中)
     * @param name 模板文件的名称
     * @param path 模板文件的目录: 如ftl与此java文件同目录, 则此处为 "", 基于classpath目录以/开头
     * @param rootMap 模板的数据模型
     * @param outputStream 输出流
     */
    public static void rendToStream(String name, String path, Map<String, Object> rootMap, OutputStream outputStream) throws TemplateException, IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        getTemplate(name, path).process(rootMap, out); // 将模板文件内容以UTF-8编码输出到相应的流中
        if (null != out) {
            out.close();
        }
    }

    public static void rendToStream(String sourceCode, Map<String, Object> rootMap, OutputStream outputStream) throws
            TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");

        Template template = new Template("", sourceCode, cfg);

        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        template.process(rootMap, out);
        if (null != out) {
            out.close();
        }
    }

    /**
     * 根据模板文件输出内容到控制台
     * @param name       模板文件的名称
     * @param pathPrefix 模板文件的目录
     * @param rootMap    模板的数据模型
     */
    public static void rendToConsole(String name, String pathPrefix, Map<String, Object> rootMap) throws
            TemplateException, IOException {
        getTemplate(name, pathPrefix).process(rootMap, new PrintWriter(System.out));
    }

    public static void rendToConsole(String sourceCode, Map<String, Object> rootMap) throws
            TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        Template template = new Template("", sourceCode, cfg);
        template.process(rootMap, new PrintWriter(System.out));
    }

    /**
     * 获取指定目录下的Ftl模板文件
     * @param name 模板文件的名称
     * @param path 模板文件的目录
     */
    public static Template getTemplate(String name, String path) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23); //通过FreeMarker的Configuration对象可以读取ftl文件
        cfg.setClassForTemplateLoading(FtlU.class, path); // 设置模板文件的目录
        cfg.setDefaultEncoding("UTF-8");       //Set the default charset of the template files
        Template temp = cfg.getTemplate(name); //在模板文件目录中寻找名为"name"的模板文件
        return temp; //此时FreeMarker就会到类路径下的"path"文件夹中寻找名为"name"的模板文件
    }

    /**
     * 根据FTL模板初始化目录
     * @param rootDir 根目录
     * @param dirTpl 模板字符串：src----main--------java------------cn.aezo.minionsdemo
     */
    public static void mkdirByTemplate(String rootDir, String dirTpl) {
        List<String> dirList = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();

        // 分割字符串
        int length = dirTpl.length();
        int splitLength = 0;
        String lineDir = "";
        for (int i = 0; i < length; i++) {
            Character c = dirTpl.charAt(i);
            if('-' == c) {
                if(!"".equals(lineDir)) {
                    dirList.add(lineDir);
                    levelList.add(splitLength);
                    splitLength = 0;
                }

                splitLength++;
                lineDir = "";
            } else {
                lineDir += String.valueOf(c);
            }
        }
        dirList.add(lineDir);
        levelList.add(splitLength);

        System.out.println(String.format("FtlU.mkdirByTemplate: dirList = %s, levelList = %s", dirList, levelList));

        // 创建文件夹
        for(int i = 0; i < dirList.size(); i++) {
            String dirItem = dirList.get(i);
            Integer levelItem = levelList.get(i);

            // 获取父路径
            String parentDir = "";
            Set<Integer> parentLevel = new HashSet<>();
            for(int j = i - 1; j >= 0; j--) {
                Integer level = levelList.get(j);
                if(level < levelItem && !parentLevel.contains(level)) {
                    String dirTemp = dirList.get(j);
                    if(dirTemp.contains(".")) {
                        String[] dirTempArr = dirTemp.split("\\.");
                        dirTemp = "";
                        for (String tempItem : dirTempArr) {
                            dirTemp += tempItem + "/";
                        }
                        if(dirTemp.length() > 0) {
                            dirTemp = dirTemp.substring(0, dirTemp.length() - 1);
                        }
                    }

                    parentDir = dirTemp + "/" + parentDir;
                    parentLevel.add(level);
                }
            }

            // 创建
            if(dirItem.contains(".")) {
                String[] dirTempArr = dirItem.split("\\.");
                String dirTemp = "";
                for (String tempItem : dirTempArr) {
                    dirTemp += tempItem + "/";

                    String dir = rootDir + "/" + parentDir + "/" + dirTemp;
                    mkdir(dir);
                }
            } else {
                String dir = rootDir + "/" + parentDir + "/" + dirItem;
                mkdir(dir);
            }
        }
    }

    public static void mkdir(String dirPath) {
        File root = new File(dirPath);
        if(root.exists()) {
            if (!root.isDirectory()) {
                throw new RuntimeException("目录被占用");
            }
        } else {
            root.mkdir();
        }
    }

    /**
     * 测试程序
     * @param args
     */
    public static void main(String[] args) throws IOException, TemplateException {
        // rendToConsole("Hello ${name}", MiscU.Instance.toMap("name", "smalle1"));

        // rendToStream("Hello ${name}", MiscU.Instance.toMap("name", "smalle2"), new FileOutputStream(new File("D://temp/target0.ftl")));

        // rendToConsole("test.ftl", "", MiscU.Instance.toMap("name", "smalle3"));

        // rendToStream("test.ftl", "", MiscU.Instance.toMap("name", "smalle4"), new FileOutputStream(new File("D://temp/target.ftl")));

        // 根据FTL模板初始化目录
        // String rootDir = "d:/temp/smtools";
        // FileU.mkdir(rootDir);
        //
        // File dirFile = FileU.getFileByClasspath("cn/aezo/demo/freemaker/dir.ftl");
        // String dirTpl = FileU.readFileByLines(dirFile.getPath());
        // dirTpl = dirTpl.replaceAll("\\$\\{projectPackageName\\}", "cn.aezo.demo").trim();
        //
        // mkdirByTemplate(rootDir, dirTpl);

    }

}
