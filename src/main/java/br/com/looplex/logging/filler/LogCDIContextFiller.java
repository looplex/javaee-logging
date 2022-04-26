package br.com.looplex.logging.filler;

import br.com.looplex.logging.LogContext;
import br.com.looplex.logging.annotations.LogLevel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.time.Instant;

import static java.util.Objects.nonNull;

@Default
@ApplicationScoped
public class LogCDIContextFiller implements ILogContextFiller {

    public LogContext fill(LogContext logContext) {
        fillThrowableData(logContext);
        logContext.setCaller(logContext.getBody().get("caller"));
        logContext.setMethodName(logContext.getBody().get("methodName"));
        logContext.setArgs(logContext.getBody().get("args"));
        logContext.setLogLevel(LogLevel.valueOf(logContext.getBody().get("logLevel")));
        return logContext;
    }

    private LogContext fillThrowableData(LogContext logContext) {
        Throwable throwable = logContext.getThrowable();
        if(nonNull(throwable)) {
            logContext.setTimestamp(Instant.now().toString());
            logContext.setMessage(throwable.getMessage());
        }
        return logContext;
    }

}
