package com.liutao.global;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalException implements Thread.UncaughtExceptionHandler
{
    public void uncaughtException(Thread t, Throwable e)
    {
        log.error("Unhandled exception caught!:" + String.valueOf(e));
    }
}