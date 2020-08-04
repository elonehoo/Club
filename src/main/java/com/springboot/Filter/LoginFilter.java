package com.springboot.Filter;


import com.alibaba.fastjson.JSONObject;
import com.springboot.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/view/*"})
public class LoginFilter implements Filter {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //校验用户登录的状态
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Filter过滤器跨域处理
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","x-request-with");
        response.setHeader("Access-Control-Allow-Credentials","true");

        //获取Header中的参数
        String token = request.getHeader("token");

        token = token == null ? "" : token;

        //在Redis中是否存在
        Long expire = redisTemplate.getExpire(token);
        if (expire > 0){
            //是登录的状态
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String string = JSONObject.toJSONString(new Result(null, "未登录", 104));

            response.setContentType("json/text;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(string);
        }

    }
}
