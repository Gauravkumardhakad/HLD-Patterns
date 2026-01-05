// define the product interface
interface Notification{
    public void send(String msg);
}

// defining the concrete product
class EmailNotification implements Notification{

    @Override
    public void send(String msg){
        System.out.println("Email notification "+ msg);
    }
}

// defining the concrete product
class WhatsappNotification implements Notification{

    @Override
    public void send(String msg){
        System.out.println("Whatsapp notification "+ msg);
    }
}

// defining the concrete product
class SMSNotification implements Notification{

    @Override
    public void send(String msg){
        System.out.println("SMS notification "+ msg);
    }
}

// define the abstract creator
abstract class NotificationCreator{
    // factory method
    public abstract Notification createNotification();

    // common logic using the factory method
    public void send(String msg){
        Notification notification = createNotification();
        notification.send(msg);
    }
}

// define the concrete email notification creator
class EmailNotificationCreator extends NotificationCreator{
    
    @Override
    public Notification createNotification(){
        return new EmailNotification();
    }
}

// define the concrete Whatsapp creator
class WhatsappNotificationCreator extends NotificationCreator{
    
    @Override
    public Notification createNotification(){
        return new WhatsappNotification();
    }
}

// define the concrete SMS notification creator
class SMSNotificationCreator extends NotificationCreator{
    
    @Override
    public Notification createNotification(){
        return new SMSNotification();
    }
}


public class Factory{
    public static void main(String a[]){
        NotificationCreator creator;

        // send Email
        creator = new EmailNotificationCreator();
        creator.send("Welcome to India 1");

        // send Whatsapp
        creator = new WhatsappNotificationCreator();
        creator.send("Welcome to India 2");

        // send Email
        creator = new SMSNotificationCreator();
        creator.send("Welcome to India 3");
    }
}
