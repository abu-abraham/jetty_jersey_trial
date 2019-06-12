package jetty_abt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("msg")
public class MyMessage {

    final Logger logger = LogManager.getLogger();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        //curl localhost:8080/rest/msg
        ThreadContext.put("id", "Random message");
        Integer a = new Integer(10);
        anotherFunction(Optional.ofNullable(a));
        logger.debug(ThreadContext.get("id2"));
        return "My message\n";
    }

    private void anotherFunction(Optional<Integer> no){
        no.orElse(100);
        ThreadContext.put("id2", "Random integer passed in another function: "+no.toString());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PostMessage setMessage(PostMessage postMessage){
        //curl --header "Content-Type: application/json" -X POST  -d '{"message":"value1"}' localhost:8080/rest/msg
        anotherFunction(Optional.ofNullable(null));
        logger.debug(ThreadContext.get("id2"));
        postMessage.setMessage(postMessage.getMessage().toUpperCase());
        return postMessage;
    }
}

