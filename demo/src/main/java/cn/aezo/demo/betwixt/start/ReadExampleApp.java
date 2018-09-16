package cn.aezo.demo.betwixt.start;

import org.apache.commons.betwixt.io.BeanReader;

import java.io.StringReader;

/**
 * Created by smalle on 2017/3/23.
 */
public class ReadExampleApp {

    /**
     * 详细请参考WriteExampleApp
     * @param args
     * @throws Exception
     */
    public static final void main(String args[]) throws Exception{
        StringReader xmlReader = new StringReader("<?xml version='1.0' ?><smith><age>25</age><name>James Smith</name></smith>");

        BeanReader beanReader  = new BeanReader();

        // Configure the reader
        beanReader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanReader.getBindingConfiguration().setMapIDs(false);

        beanReader.registerBeanClass("smith", Person.class);

        Person person = (Person) beanReader.parse(xmlReader);

        System.out.println(person); // Person[name='James Smith',age='25']
    }
}
