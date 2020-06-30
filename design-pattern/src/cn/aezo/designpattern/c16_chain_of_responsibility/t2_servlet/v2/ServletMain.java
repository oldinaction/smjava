package cn.aezo.designpattern.c16_chain_of_responsibility.t2_servlet.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smalle
 * @date 2020-06-14 22:29
 */
public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:)，<script>，欢迎996 ";
        Response response = new Response();
        response.str = "response";

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter()).add(new SensitiveFilter());
        chain.doFilter(request, response);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}

class Request {
    String str;
}

class Response {
    String str;
}

interface Filter {
    // 增加了一个FilterChain进行手动控制是否调用下一个拦截器
    void doFilter(Request request, Response response, FilterChain chain);
}

class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]") + "HTMLFilter()";
        chain.doFilter(request, response);
        response.str += "--HTMLFilter()";

    }
}

class SensitiveFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replaceAll("996", "955") + " SensitiveFilter()";
        chain.doFilter(request, response);
        response.str += "--SensitiveFilter()";

    }
}

class FilterChain {
    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if(index == filters.size()) return;
        // 根据下标执行拦截器
        Filter f = filters.get(index);
        index ++;

        f.doFilter(request, response, this);
    }
}