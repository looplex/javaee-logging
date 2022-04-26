package br.com.looplex.logging.saver;

import br.com.looplex.logging.LogContext;
import br.com.looplex.logging.annotations.LogLevel;

import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
public class LogContextSaver implements ILogContextSaver {

    public LogContext saveData(LogContext logContext, InvocationContext context) {
        logContext.getBody().put("caller", getCaller(context));
        logContext.getBody().put("args", getArgs(context));
        logContext.getBody().put("methodName", getMethodName(context));
        logContext.getBody().put("logLevel", LogLevel.INFO.name());
        return logContext;
    }

    private String getMethodName(InvocationContext context) {
        return context.getMethod().getName();
    }

    private String getArgs(InvocationContext context) {
        return Arrays
                .stream(context.getParameters())
                .map(Object::toString)
                .collect(Collectors.toList()).toString();
    }

    private String getCaller(InvocationContext context) {
        return context.getTarget().getClass().getSimpleName();
    }

    public LogContext saveExceptionData(LogContext logContext, Throwable throwable) {
        logContext.setThrowable(throwable);
        logContext.getBody().put("logLevel", LogLevel.ERROR.name());
        return logContext;
    }


}