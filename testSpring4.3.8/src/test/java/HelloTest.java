import cn.aezo.spring4.Hello;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by smalle on 2017/6/1.
 */
public class HelloTest {
    private Hello hello;

    @Before
    public void  before() {
        System.out.println("before==>");

        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // retrieve configured instance
        hello = context.getBean("myBean", Hello.class);
    }

    @After
    public void  after() {
        System.out.println("after==>");
    }

    @Test
    public void  hello() {
        System.out.println("hello==>");

        hello.hello();
    }
}
