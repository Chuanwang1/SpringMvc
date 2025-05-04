package edu.ynjgy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LCW69
 */
@Aspect
@Component
public class SecurityAspect {

    // 定义切点，拦截需要登录验证的方法
    @Pointcut("@annotation(edu.ynjgy.annotation.RequireLogin)")
    public void requireLogin() {}

    // 环绕通知：检查用户是否已登录
    @Around("requireLogin()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();

        // 检查session中是否有用户信息
        if (session.getAttribute("user") == null) {
            // 未登录，可以返回错误信息或重定向到登录页面
            return "redirect:/login";
        }

        // 已登录，继续执行原方法
        return joinPoint.proceed();
    }
}