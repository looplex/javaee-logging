package br.com.looplex.logging.config;

import br.com.looplex.logging.filler.ILogContextFiller;
import br.com.looplex.logging.formatter.ILogFormatter;
import br.com.looplex.logging.handler.ILogHandler;
import br.com.looplex.logging.handler.LogHandler;
import br.com.looplex.logging.printer.ILogPrinter;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;

public class LogConfig implements Serializable {

    @Inject
    private ILogFormatter logFormatter;
    @Inject
    private ILogPrinter logPrinter;
    @Inject
    private ILogContextFiller logContextFiller;

    @Produces
    public ILogHandler injectLogHandler() {
        return new LogHandler(
                logFormatter,
                logContextFiller,
                logPrinter);
    }

}