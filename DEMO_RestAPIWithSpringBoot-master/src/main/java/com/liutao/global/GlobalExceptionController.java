package com.liutao.global;

import com.liutao.util.ResponseBuilder;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionController
{
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Throwable.class, RuntimeException.class})
    public Object defaultErrorHandler(HttpServletRequest request, Exception e)
    {
        log.error("controller exception occured:"+String.valueOf(e));
        return ResponseBuilder.buildFailResponse(String.valueOf(e));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class, NoSuchMethodException.class, NoHandlerFoundException.class})
    public Object defaultNotFoundHandler(HttpServletRequest request, Exception e)
    {
        log.warn("controller not found occured:"+String.valueOf(e));
        return ResponseBuilder.buildFailResponse("error occured: " + String.valueOf(e));
    }
}