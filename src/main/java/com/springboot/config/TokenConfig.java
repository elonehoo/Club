package com.springboot.config;

import com.alibaba.fastjson.JSONObject;
import com.springboot.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
​
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
​
/**
 *拦截器(根据token)
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
​
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("====================拦截前操作===================");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        System.out.println("---pre---" + token);
        if (token != null) {
            boolean result = TokenUtil.verify(token);
            System.out.println("token:     " + result);
            if (result) {
                System.out.println("拦截器放行,不拦截");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("msg", "token verify fail");
            json.put("code", "50000");
            response.getWriter().append(json.toJSONString());
            System.out.println("非登录用户, 认证失败，被拦截");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
    ​
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("===================post====================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("===================afterCompletion====================");
    }
}