package org.example.weatherforecast.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration // 1. 声明这是一个配置类
public class RestConfig {

    @Bean // 2. 将方法的返回值交给 Spring 管理
    public RestTemplate restTemplate() {
        // 使用 Apache HttpClient 5 构造请求工厂，它能自动处理 GZIP
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        return new RestTemplate(factory);
    }
}