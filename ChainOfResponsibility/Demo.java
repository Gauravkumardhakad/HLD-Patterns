

// defining the command handler
interface RequestHandler{
    void setNext(RequestHandler next);
    void handle(Request req);
}

// defining the abstract base handler
abstract class BaseHandler implements RequestHandler{
    protected RequestHandler next;

    @Override
    public void setNext(RequestHandler next){
        this.next = next;
    }

    protected void forward(Request request){
        if(next!=null){
            next.handle(request);
        }
    }
}

// creating the concrete handlers
class AuthHandler extends  BaseHandler{
    @Override
    public void handle(Request request){
        // some logic 
        forward(request);
    }
}

class RateLimitingHandler extends BaseHandler{
    @Override
    public void handle(Request request){
        // some logic 
        forward(request);
    }
}

class ValidationHandler extends BaseHandler{
    @Override
    public void handle(Request request){
        // some logic 
        forward(request);
    }
}

// client code
public class Demo{
    public static void main(String[] a){
        RequestHandler auth = new AuthHandler();
        RequestHandler  rateLimiter = new RateLimitingHandler();
        RequestHandler validation = new ValidationHandler();

        auth.setNext(rateLimiter);
        rateLimiter.setNext(validation);

        Request request = new Request("john", "ADMIN", 10, "{ \"data\": \"valid\" }");
        auth.handle(request);

        System.out.println("\n--- Trying an invalid request ---");
        Request badRequest = new Request(null, "USER", 150, "");
        auth.handle(badRequest); 
    }
}
