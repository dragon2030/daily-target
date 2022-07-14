
package com.example.dailyTarget.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志记录切面类
 */
@Component
@Aspect
public class LogAspect {

    //本地异常日志记录对象
    private  static  final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 保存方法开始执行时间
    private final ThreadLocal<Long> tlo = new ThreadLocal<>();

    // 匹配所有方法
//    @Pointcut("(execution(* *(..)))")
    @Pointcut("(execution( * com.example.dailyTarget.controller.*.*(..)))")
    public void pointcut() {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("pointcut()")
    public void beforMethod(JoinPoint joinPoint){
        try {
            //*========控制台输出=========*//
            logger.info("=====前置通知开始=====");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("请求参数:" + Arrays.asList(joinPoint.getArgs()));
            logger.info("=====前置通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoint
     */
    @AfterReturning(value="pointcut()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        try{
            String methodName = joinPoint.getSignature().getName();
            logger.info("=====返回通知开始=====");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("返回结果:" + result);
            logger.info("=====返回通知结束=====");
        }catch(Exception e){
            logger.error("返回通知异常" + e);
        }
    }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value="pointcut()",throwing="e")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception e){
        try {
            /*========控制台输出=========*/
            logger.info("=====异常通知开始=====");
            logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("异常代码:" + e.getClass().getName());
            logger.info("异常信息:" + e.getMessage());
            logger.info("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }
}
