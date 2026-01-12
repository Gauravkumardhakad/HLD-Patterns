
// defien the strategy interface
interface ShippingStrategy{
    double calculateCost(Order order);
}

// implement cocrete strategy
class FlatRateShipping implements ShippingStrategy{
    private double rate;

    public FlatRateShipping(double rate){
        this.rate = rate;
    }

    @Override
    public double calculateCost(Order order){
        System.out.println("claculating the cost with flat rate strategy "+ rate);
        return rate;
    }
}


class WeightBasedShipping implements ShippingStrategy{
    private int ratePerKg;

    public WeightBasedShipping(int ratePerKg){
        this.ratePerKg = ratePerKg;
    }

    @Override
    public double calculateCost(Order order){
        System.out.println("claculating the cost with wight strategy");
        return order.getTotalWeight()*ratePerKg;
    }
}

// creating the context class
class ShippingCostService {
    private ShippingStrategy strategy;

    public ShippingCostService(ShippingStrategy strategy){
        this.strategy = strategy;
    }

    // Method to change strategy at runtime
    public void setStrategy(ShippingStrategy strategy) {
        System.out.println("ShippingCostService: Strategy changed to " + strategy.getClass().getSimpleName());
        this.strategy = strategy;
    }

    public double calculateShippingCost(Order order) {
        if (strategy == null) {
            throw new IllegalStateException("Shipping strategy not set.");
        }
        double cost = strategy.calculateCost(order);
        System.out.println("ShippingCostService: Final Calculated Shipping Cost: $" + cost +
                           " (using " + strategy.getClass().getSimpleName() + ")");
        return cost;
    }
}


public class Demo {
    public static void main(String[] args) {
        Order order1 = new Order();

        // Create different strategy instances
        ShippingStrategy flatRate = new FlatRateShipping(10.0);
        ShippingStrategy weightBased = new WeightBasedShipping(2);

        // Create context with an initial strategy
        ShippingCostService shippingService = new ShippingCostService(flatRate);

        System.out.println("--- Order 1: Using Flat Rate (initial) ---");
        shippingService.calculateShippingCost(order1);

        System.out.println("\n--- Order 1: Changing to Weight-Based ---");
        shippingService.setStrategy(weightBased);
        shippingService.calculateShippingCost(order1);

    }

}

