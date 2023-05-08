package com.fantank.config;

import com.fantank.interceptor.HandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * Component注解告诉SpringMVC该类是一个SpringIOC容器下管理的类
 * 其实@Controller, @Service, @Repository是@Component的细化
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    WebSocketHandler myWebSocketHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //添加websocket处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(myWebSocketHandler, "/ws").addInterceptors(new HandShakeInterceptor()).setAllowedOrigins("*");

        //添加websocket处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(myWebSocketHandler, "/ws/sockjs").addInterceptors(new HandShakeInterceptor()).setAllowedOrigins("*").withSockJS();
    }
}