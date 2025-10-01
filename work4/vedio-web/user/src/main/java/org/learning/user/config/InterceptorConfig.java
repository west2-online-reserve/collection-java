package org.learning.user.config;

import lombok.extern.slf4j.Slf4j;
import org.learning.user.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
@ComponentScan("org.learning.user.interceptor")
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors...");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/register");
    }
}
