package com.sunrisejay.framework.biz.operationlog.aspect;

import com.sunrisejay.framework.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * API操作日志切面
 * 作用：拦截所有带 @ApiOperationLog 注解的方法，自动记录请求入参、响应出参和执行耗时
 *
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 20:10
 */
@Aspect      // 声明这是一个切面类，由Spring AOP管理
@Slf4j      // Lombok自动生成日志对象log
public class ApiOperationLogAspect {

    /**
     * 定义切入点：拦截所有带 @ApiOperationLog 注解的方法
     * @annotation(...) 表示拦截带有指定注解的方法
     */
    @Pointcut("@annotation(com.sunrisejay.framework.biz.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    /**
     * 环绕通知：在目标方法执行前后执行
     * @param joinPoint 连接点，代表被拦截的方法
     * @return 方法执行结果
     */
    @Around("apiOperationLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        // ============ 1. 请求前：记录开始时间和入参 ============
        long startTime = System.currentTimeMillis();  // 记录请求开始时间

        // 获取目标类名（com.sunrisejay.xxx.UserController）
        String className = joinPoint.getTarget().getClass().getName();
        // 获取目标方法名（saveUser）
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数数组
        Object[] args = joinPoint.getArgs();
        // 将参数数组转换为JSON字符串，方便日志查看
        String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(","));

        // 获取@ApiOperationLog注解中描述的信息
        String description = getApiOperationDescription(joinPoint);

        // 打印请求开始日志
        log.info("======请求开始:[{}] , 入参:{} , 请求类:{} , 请求方法:{} ===========================",
                description, argsJsonStr, className, methodName);

        // ============ 2. 执行目标方法 ============
        Object result = joinPoint.proceed();

        // ============ 3. 请求后：记录执行耗时和响应结果 ============
        long executionTime = System.currentTimeMillis() - startTime;  // 计算执行耗时
        // 将响应结果转换为JSON
        log.info("====== 请求结束:[{}] , 耗时:{}ms , 出参:{} ================================",
                description, executionTime, JsonUtils.toJsonString(result));

        return result;
    }

    /**
     * 获取方法上 @ApiOperationLog 注解的 description 属性
     * @param joinPoint 连接点
     * @return 注解中描述的信息
     */
    private String getApiOperationDescription(ProceedingJoinPoint joinPoint) {
        // 强制转换为 MethodSignature，获取方法签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取被拦截的Method对象
        Method method = signature.getMethod();
        // 通过反射获取方法上的 @ApiOperationLog 注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
        // 返回注解中的 description 属性值
        return apiOperationLog.description();
    }

    /**
     * 将对象转换为JSON字符串的工具方法
     * 使用函数式编程，接收任意Object返回String
     */
    private Function<Object,String> toJsonStr(){
        return JsonUtils::toJsonString;
    }

}
