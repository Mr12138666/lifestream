package com.sunrisejay.lifestream.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.sunrisejay.framework.common.constant.GlobalConstants.USER_ID;

@Component
@Slf4j
public class AddUserId2HeaderFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==================> TokenConvertFilter");
        Long userId = null;
        try{
            userId = StpUtil.getLoginIdAsLong();
        }catch (Exception e){
            return  chain.filter(exchange);
        }

        log.info("## 当前登录用户id:{}",userId);
        Long finalUserId = userId;
        ServerWebExchange newExchange = exchange.mutate()
                .request(builder ->  builder.header(USER_ID,String.valueOf(finalUserId)))
                .build();



		// 将请求传递给过滤器链中的下一个过滤器进行处理。没有对请求进行任何修改。
        return chain.filter(newExchange);
    }
}