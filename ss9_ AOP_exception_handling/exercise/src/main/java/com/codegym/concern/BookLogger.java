package com.codegym.concern;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDateTime;
@Aspect
public class BookLogger {
    @AfterReturning(pointcut = "execution(public * com.codegym.library.services.BookService.save(..))")
    public void log(){
        System.out.println("[Log 1] -- Trạng thái sách thay đổi vào lúc " + LocalDateTime.now());
    }
}
