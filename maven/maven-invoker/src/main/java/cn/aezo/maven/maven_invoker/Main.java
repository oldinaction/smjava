package cn.aezo.maven.maven_invoker;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationOutputHandler;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * dependencies.txt 内容为
     *
     * The following files have been resolved:
     *    org.codehaus.plexus:plexus-utils:jar:3.0.20:compile:/Users/smalle/.m2/repository/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.jar
     *    org.codehaus.plexus:plexus-component-annotations:jar:1.6:compile:/Users/smalle/.m2/repository/org/codehaus/plexus/plexus-component-annotations/1.6/plexus-component-annotations-1.6.jar
     *    org.apache.maven.shared:maven-invoker:jar:2.2:compile:/Users/smalle/.m2/repository/org/apache/maven/shared/maven-invoker/2.2/maven-invoker-2.2.jar
     */
    public static void main(String[] args) throws Exception {
        String destFile = "/Users/smalle/gitwork/github/smjava/maven/maven-invoker/dependencies.txt";
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("/Users/smalle/gitwork/github/smjava/maven/maven-invoker/pom.xml"));
        request.setGoals(Arrays.asList("dependency:list"));
        Properties properties = new Properties();
        properties.setProperty("outputFile", destFile); // 将输出重定向到此文件
        properties.setProperty("outputAbsoluteArtifactFilename", "true"); // 输出包含jar包路径
        properties.setProperty("includeScope", "runtime"); // only runtime (scope compile + runtime)
        // if only interested in scope runtime, you may replace with excludeScope = compile
        request.setProperties(properties);

        Invoker invoker = new DefaultInvoker();
        // the Maven home can be omitted if the "maven.home" system property is set
        invoker.setMavenHome(new File("/Users/smalle/software/apache-maven-3.8.4"));
        //invoker.setOutputHandler(null); // not interested in Maven output itself
        invoker.setOutputHandler(s -> System.out.println(s)); // 打印编译过程(重定向到此文件中的内容不会打印)
        InvocationResult result = invoker.execute(request);
        if (result.getExitCode() != 0) {
            throw new IllegalStateException("Build failed.");
        }

        // 读取上述文件打印依赖。末尾可能回出现 (optional)
        // org.springframework:spring-jdbc:jar:5.1.2.RELEASE:compile:/Users/smalle/.m2/repository/org/springframework/spring-jdbc/5.1.2.RELEASE/spring-jdbc-5.1.2.RELEASE.jar (optional)
        Pattern pattern = Pattern.compile("(.*?):(.*?):(.*?):(.*?):(?:compile|runtime):(.*)");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(destFile))) {
            System.out.println("read file...");
            // 文件写入可能有间隔，在此处等待
            while (!"The following files have been resolved:".equals(reader.readLine()));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // org.springframework:spring-jdbc:jar:5.1.2.RELEASE:compile:/Users/smalle/.m2/repository/org/springframework/spring-jdbc/5.1.2.RELEASE/spring-jdbc-5.1.2.RELEASE.jar
                    System.out.print(matcher.group(6));
                }
            }
        }
    }
}
