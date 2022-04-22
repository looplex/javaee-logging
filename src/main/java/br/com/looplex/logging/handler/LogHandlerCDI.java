package br.com.looplex.logging.handler;

import br.com.looplex.logging.LogContext;
import br.com.looplex.logging.annotations.Loggable;
import br.com.looplex.logging.saver.ILogContextSaver;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Loggable
@Interceptor
public class LogHandlerCDI {

    @Inject
    private ILogHandler logHandler;
    @Inject
    private ILogContextSaver logContextSaver;

    @AroundInvoke
    public Object handle(InvocationContext context) throws Exception {
        LogContext logContext = new LogContext();
        try {
            logContextSaver.saveData(logContext, context);
            return context.proceed();
        } catch (Throwable throwable) {
            logContextSaver.saveExceptionData(logContext, throwable);
            throw throwable;
        } finally {
            logHandler.handle(logContext);
        }
    }

}