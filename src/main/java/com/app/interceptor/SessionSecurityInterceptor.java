package com.app.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yujingyang on 2017/4/21.
 */
public class SessionSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String lastRemote = (String) session.getAttribute("lastRemote");
        String remote = request.getRemoteAddr();
        if(lastRemote != null){
            if(!lastRemote.equals(remote)){
                session.invalidate();
                session.setAttribute("lastRemote",remote);//更换remoteAddress 后 清空session 防止攻击
            }
        }
        session.setAttribute("lastRemote",remote);//如果lastRemote为null 设置remote
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
