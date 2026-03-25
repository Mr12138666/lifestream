package com.sunrisejay.lifestream.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.sunrisejay.framework.common.constant.GlobalConstants.USER_ID;

@Component
@Slf4j
public class AddUserId2HeaderFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        Long userId = null;
        try{
            Object loginId = StpUtil.getLoginId();
            if (loginId != null) {
                userId = Long.parseLong(loginId.toString());
            }
        }catch (Exception e){
            return chain.filter(exchange);
        }

        if (userId != null) {
            Long finalUserId = userId;
            ServerWebExchange newExchange = exchange.mutate()
                    .request(builder -> builder.header(USER_ID, String.valueOf(finalUserId)))
                    .build();
            return chain.filter(newExchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 100;
    }
}