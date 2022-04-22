package br.com.looplex.logging;

import br.com.looplex.logging.annotations.Loggable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
@Loggable
public class LogResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

}