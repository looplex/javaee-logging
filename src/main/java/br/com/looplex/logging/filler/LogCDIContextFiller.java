package br.com.looplex.logging.filler;

import br.com.looplex.logging.LogContext;
import br.com.looplex.logging.annotations.LogLevel;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;
import java.time.Instant;

import static java.util.Objects.nonNull;

public class LogCDIContextFiller implements ILogContextFiller, Serializable {

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
