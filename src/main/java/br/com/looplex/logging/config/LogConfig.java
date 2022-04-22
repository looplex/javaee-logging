package br.com.looplex.logging.config;

import br.com.looplex.logging.filler.ILogContextFiller;
import br.com.looplex.logging.filler.LogCDIContextFiller;
import br.com.looplex.logging.formatter.ILogFormatter;
import br.com.looplex.logging.formatter.LogFormatter;
import br.com.looplex.logging.handler.ILogHandler;
import br.com.looplex.logging.handler.LogHandler;
import br.com.looplex.logging.printer.ILogPrinter;
import br.com.looplex.logging.printer.LogPrinter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class LogConfig {

    @Produces
    public ILogContextFiller injectLogContextFiller() {
        return new LogCDIContextFiller();
    }

    @Produces
    public ILogPrinter injectLogPrinter() {
        return new LogPrinter();
    }

    @Produces
    public ILogFormatter injectLogFormatter() {
        return new LogFormatter();
    }

    @Produces
    public ILogHandler injectLogHandler() {
        return new LogHandler(
                new LogFormatter(),
                new LogCDIContextFiller(),
                new LogPrinter());
    }

}