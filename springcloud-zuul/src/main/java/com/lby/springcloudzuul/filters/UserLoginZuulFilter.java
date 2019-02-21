package com.lby.springcloudzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 加入到Spring容器
 * Created by Administrator on 2017/12/6.
 */

@Component
public class UserLoginZuulFilter extends ZuulFilter {
    @Override
    public boolean shouldFilter() {
        // 该过滤器需要执行
        return true;
    }

    @Override
    public Object run() { //编写业务逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        request.setAttribute("message", "message");
        System.out.println("ok");
//        requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
//        requestContext.setResponseStatusCode(401); // 设置响应状态码
        return null;
    }

    @Override
    public String filterType() {
        // 设置过滤器类型为：pre
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 设置执行顺序
        return 0;
    }

}
