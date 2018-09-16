package cn.aezo.demo.jackson.jsonfilter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * Created by smalle on 2017/3/26.
 */
@JsonFilter("myFilter")
public class BeanWithFilter {
    public int id;
    public String name;

    public BeanWithFilter() {

    }

    public BeanWithFilter(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) throws JsonProcessingException {
        final BeanWithFilter bean = new BeanWithFilter(1, "My bean");

        final FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        final String result = new ObjectMapper().writer(filters).writeValueAsString(bean);

        System.out.println("result = " + result);
    }
}
