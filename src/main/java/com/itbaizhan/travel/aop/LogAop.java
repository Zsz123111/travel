package com.itbaizhan.travel.aop;

import com.itbaizhan.travel.bean.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    private final static Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("execution(* com.itbaizhan.travel.controller.backstage.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        //记录访问时间
        Date date = new Date();
        request.setAttribute("visitTime",date);
    }

    @After("pointCut()")
    public void doAfter(){
        Log log  = new Log();

        Date visitTime =(Date)request.getAttribute("visitTime");//访问时间
        Date now = new Date();
        int executionTime =(int)(now.getTime() - visitTime.getTime());//访问时长
        String ip = request.getRemoteAddr();//ip
        String url = request.getRequestURI();//访问路径
        //拿到user对象
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User){
            String username = ((User)user).getUsername();
            log.setUsername(username);
        }
        log.setExcutionTime(executionTime);
        log.setUrl(url);
        log.setIp(ip);
        log.setVisitTime(visitTime);

        logger.info(log.toString());

    }
    @AfterThrowing(pointcut = "pointCut()",throwing = "ex")
    public void afterThrowing(Throwable ex){
        Log log  = new Log();

        Date visitTime =(Date)request.getAttribute("visitTime");//访问时间
        Date now = new Date();
        int executionTime =(int)(now.getTime() - visitTime.getTime());//访问时长
        String ip = request.getRemoteAddr();//ip
        String url = request.getRequestURI();//访问路径
        //拿到user对象
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User){
            String username = ((User)user).getUsername();
            log.setUsername(username);
        }
        log.setExcutionTime(executionTime);
        log.setUrl(url);
        log.setIp(ip);
        log.setVisitTime(visitTime);

        String exception = ex.getMessage();
        log.setExceptionMessage(exception);

        logger.info(log.toString());

    }
}
