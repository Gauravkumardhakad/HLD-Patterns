
// creating a payment interface
interface PaymentProcessor{
    void processPayment(double amount, String currency);
    boolean isPaymentSuccessful();
    String getTransactionId();
}

// creating a concrete class of payment processing
class InhousePaymentProcessor implements PaymentProcessor{
    private String transactionId;
    private boolean isPaymentSuccessful;

    @Override
    public void processPayment(double amount, String currency){
        System.out.println("processing payment using the inhouse payment method");
    }

    @Override
    public boolean isPaymentSuccessful(){
        return isPaymentSuccessful;
    }

    @Override 
    public String getTransactionId(){
        return transactionId;
    }
}


// creating the checkout service
class Checkout{
    private PaymentProcessor paymentProcessor;

    public  Checkout(PaymentProcessor paymentProcessor){
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(double amount, String currency){
        System.out.println("performing checkout logic");
    }
}



// we are given a legacy payment class
class LegacyGateway{
    private long transactionReference;
    private boolean isPaymentSuccessful;

    public void executeTransaction(double totalAmount, String currency){
        System.out.println("executing the transaction using legacy gateway");
    }

    public boolean checkStatus(long transactionReference){
        System.out.println("checking the status of legacy gateway payment....");
        return isPaymentSuccessful;
    }

    public long getReferenceNumber(){
        return transactionReference;
    }
}

// but our system has the following methods 
// 1. processPayment(double, String)
// 2. isPaymentSuccessful()
// 3. getTransactionId()


// we need to create an adapter class

class LegacyGatewayAdapter implements PaymentProcessor{
    private final LegacyGateway legacyGateway;
    private long currentRef;

    public LegacyGatewayAdapter(LegacyGateway legacyGateway){
        this.legacyGateway = legacyGateway;
    }

    @Override
    public void processPayment(double amount, String currency){
        legacyGateway.executeTransaction(amount, currency);
        currentRef= legacyGateway.getReferenceNumber();
    }

    @Override
    public boolean isPaymentSuccessful(){
        return legacyGateway.checkStatus(currentRef);
    }

    @Override
    public String getTransactionId(){
        return "TXN_ID" + currentRef;
    }
}


// creating the client
public class Demo{
    public static void main(String a[]){
        // Modern processor
        PaymentProcessor processor = new InhousePaymentProcessor();
        Checkout modernCheckout = new Checkout(processor);
        modernCheckout.checkout(10, "USD");

        // legacy processor
        LegacyGateway legacy = new LegacyGateway();
        processor = new LegacyGatewayAdapter(legacy);
        Checkout legacyCheckout = new Checkout(processor);
        legacyCheckout.checkout(40, "IND");
    }
}

