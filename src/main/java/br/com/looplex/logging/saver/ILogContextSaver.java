package br.com.looplex.logging.saver;

import br.com.looplex.logging.LogContext;

import javax.interceptor.InvocationContext;

public interface ILogContextSaver {

    LogContext saveData(LogContext logContext, InvocationContext context);
    LogContext saveExceptionData(LogContext logContext, Throwable throwable);

}