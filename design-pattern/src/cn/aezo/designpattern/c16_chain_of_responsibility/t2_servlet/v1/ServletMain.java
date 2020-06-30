package cn.aezo.designpattern.c16_chain_of_responsibility.t2_servlet.v1;

/**
 * 此版本拦截器只能是：request: 1-2, response: 1-2
 * 而实际是：request: 1-2, response: 2-1
 *
 * @author smalle
 * @date 2020-06-14 22:25
 */
import java.util.ArrayList;
import java.util.List;

public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:)，<script>，欢迎996!";
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
    boolean doFilter(Request request, Response response);
}

class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("<", "[").replaceAll(">", "]");
        response.str += "--HTMLFilter()";
        return true;
    }
}

class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response) {
        request.str = request.str.replaceAll("996", "955");
        response.str += "--SensitiveFilter()";
        return true;
    }
}

class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter(Request request, Response response) {
        for(Filter f : filters ) {
            f.doFilter(request, response);
        }
        return true;
    }
}
