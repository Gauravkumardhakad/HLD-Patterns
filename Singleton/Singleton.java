
class LazySingleton{
    // creating a single instance, initially null
    private static LazySingleton instance;

    // private constructor
    private LazySingleton(){};

    // creating method to get instance
    public static LazySingleton getInstance(){
        if(instance==null){
            instance = new LazySingleton();
        }
        return instance;
    }
}

//------------------- Thread safe singleton------------------
class ThreadSafeSingleton{

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){};

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance==null){
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}

//------------------- Double Check singleton------------------
class DoubleCheckSingleton{

    private static DoubleCheckSingleton instance;

    private DoubleCheckSingleton(){};

    public static DoubleCheckSingleton getInstance(){
        if(instance==null){
            synchronized (DoubleCheckSingleton.class) {
                if(instance==null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}

//------------------Eager Singleton--------------------
class EagerSingleton{
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton(){};

    public static final EagerSingleton getInstance(){
        return instance;
    }
}

//----------------Bill Pugh Singleton------------------
class BillPughSingleton{
   private BillPughSingleton(){};

    private static class SingletonHelper{
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }
}

//--------------Static block init------------
class StaticBlockSingleton{
    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Error occured during creation of singleton");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}

// --------------------Enum Singleton----------
enum EnumSingleton{
    INSTANCE;

    public void getInstance(){
        // some logic
    }
}

public class Singleton{
    public static void main(String a[]){
        LazySingleton obj1 = LazySingleton.getInstance();

    }
}
