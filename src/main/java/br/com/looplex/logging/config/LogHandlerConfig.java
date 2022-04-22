package br.com.looplex.logging.config;

import br.com.looplex.logging.filler.ILogContextFiller;
import br.com.looplex.logging.filler.LogCDIContextFiller;
import br.com.looplex.logging.formatter.ILogFormatter;
import br.com.looplex.logging.formatter.LogFormatter;
import br.com.looplex.logging.printer.ILogPrinter;
import br.com.looplex.logging.printer.LogPrinter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class LogHandlerConfig {

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

}