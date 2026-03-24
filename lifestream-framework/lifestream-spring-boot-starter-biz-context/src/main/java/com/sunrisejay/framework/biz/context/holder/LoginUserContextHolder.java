package com.sunrisejay.framework.biz.context.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.sunrisejay.framework.common.constant.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

public class LoginUserContextHolder {

    // 初始化一个 ThreadLocal 变量
    private static final ThreadLocal<Map<String, Object>> LOGIN_USER_CONTEXT_THREAD_LOCAL
            = TransmittableThreadLocal.withInitial(HashMap::new);


    public static void setUserId(Object userId){
        if(userId instanceof String){
            LOGIN_USER_CONTEXT_THREAD_LOCAL.get().put(GlobalConstants.USER_ID, Long.parseLong((String) userId));
        } else {
            LOGIN_USER_CONTEXT_THREAD_LOCAL.get().put(GlobalConstants.USER_ID,userId);
        }
    }

    public static Long getUserId(){
        if(LOGIN_USER_CONTEXT_THREAD_LOCAL.get().containsKey(GlobalConstants.USER_ID)){
            return (Long)LOGIN_USER_CONTEXT_THREAD_LOCAL.get().get(GlobalConstants.USER_ID);
        }
        return null;
    }

    /**
     * 删除 ThreadLocal
     */
    public static void remove() {
        LOGIN_USER_CONTEXT_THREAD_LOCAL.remove();
    }

}