package com.gdqt.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by admin on 2017/3/14.
 */
public class SessionInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String uri = request.getRequestURI();
        logger.info("uri={}", uri);
        uri = uri.replaceFirst(request.getContextPath(), "");

        if (!uri.startsWith("/rest/page/")) {
            return true;
        }

        if (request.getSession().getAttribute("USER_SESSION_KEY") == null) {
            // 未登录
            PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
            builder.append("window.top.location.href=\"");
            builder.append(request.getContextPath());
            builder.append("/\";</script>");
            out.print(builder.toString());
            out.close();
            return false;
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
