package com.itbaizhan.travel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中获取用户信息
        HttpSession session = request.getSession();
        Object member = session.getAttribute("member");
        if (null == member){
            request.setAttribute("message","您还未登录!");
//            response.sendRedirect(request.getContextPath()+"/frontdesk/login");
            request.getRequestDispatcher(request.getContextPath()+"/frontdesk/login").forward(request,response);
            return false;
        }
        return true;
    }
}
