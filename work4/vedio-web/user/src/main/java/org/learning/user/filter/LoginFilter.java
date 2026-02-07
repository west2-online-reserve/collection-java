package org.learning.user.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.learning.user.pojo.User;
import org.learning.user.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
@Slf4j
@WebFilter("/*")
public class LoginFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException{
        HttpServletRequest request=(HttpServletRequest)var1;
        HttpServletResponse response=(HttpServletResponse)var2;
        //获取url路径是否为登录或注册请求
        if(request.getRequestURI().contains("/login")||request.getRequestURI().contains("/logout")||request.getRequestURI().contains("/register")){
            logger.debug("登录或注册请求，直接放行");
            var3.doFilter(request,response);
            return;
        }
        String token=request.getHeader("token");
        //校验token
        if(token==null){
            logger.info("令牌为空,用户未登录");
            response.setStatus(response.SC_UNAUTHORIZED);
            return;
        }
        try{
            JwtUtil.parse(token);

        }catch (Exception e){
            logger.debug("令牌非法");
            response.setStatus(response.SC_UNAUTHORIZED);
            e.printStackTrace();
            return;
        }
        logger.debug("令牌合法,放行");
        var3.doFilter(request,response);

    }
}
