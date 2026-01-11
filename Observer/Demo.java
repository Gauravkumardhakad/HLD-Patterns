
import java.util.ArrayList;
import java.util.List;


// implementing the fitness data observer interface
interface FitnessDataObserver{
    void update(FitnessData data);
}

// implementing the fitnessDataSuubject interface
interface fitnessDataSubject{
    void registerObserver(FitnessDataObserver observer);
    void removeObserver(FitnessDataObserver observer);
    void notifyObserver();
}

// implenting the concrete subject 
class FitnessData implements fitnessDataSubject{
    private int steps;
    private int activeMinutes;
    private int calories;

    private final List<FitnessDataObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(FitnessDataObserver observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(FitnessDataObserver observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(){
        for(FitnessDataObserver observer: observers){
            observer.update(this);
        }
    }

    public void newFitnessDataPushed(int steps, int activeMinutes, int calories){
        this.steps = steps;
        this.activeMinutes = activeMinutes;
        this.calories = calories;

        System.out.println("\nFitnessData: New data received – Steps: " + steps +
            ", Active Minutes: " + activeMinutes + ", Calories: " + calories);

        notifyObserver();
    }

    public void dailyReset() {
        this.steps = 0;
        this.activeMinutes = 0;
        this.calories = 0;

        System.out.println("\nFitnessData: Daily reset performed.");
        notifyObserver();
    }

    public int getSteps() { return steps; }
    public int getActiveMinutes() { return activeMinutes; }
    public int getCalories() { return calories; }
}

// implementing the concrete modules
class LiveActicivityDisplay implements FitnessDataObserver{
    @Override
    public void update(FitnessData data){
        System.out.println("Live Display → Steps: " + data.getSteps() +
            " | Active Minutes: " + data.getActiveMinutes() +
            " | Calories: " + data.getCalories());
    }
}

class ProgressLogger implements FitnessDataObserver {
    @Override
    public void update(FitnessData data) {
        System.out.println("Logger → Saving to DB: Steps=" + data.getSteps() +
            ", ActiveMinutes=" + data.getActiveMinutes() +
            ", Calories=" + data.getCalories());
        // Simulated DB/file write...
    }
}

class GoalNotifier implements FitnessDataObserver {
    private final int stepGoal = 10000;
    private boolean goalReached = false;

    @Override
    public void update(FitnessData data) {
        if (data.getSteps() >= stepGoal && !goalReached) {
            System.out.println("Notifier → 🎉 Goal Reached! You've hit " + stepGoal + " steps!");
            goalReached = true;
        }
    }

    public void reset() {
        goalReached = false;
    }
}

// client code
public class Demo {
    public static void main(String[] args) {
        FitnessData fitnessData = new FitnessData();

        LiveActicivityDisplay display = new LiveActicivityDisplay();
        ProgressLogger logger = new ProgressLogger();
        GoalNotifier notifier = new GoalNotifier();

        // Register observers
        fitnessData.registerObserver(display);
        fitnessData.registerObserver(logger);
        fitnessData.registerObserver(notifier);

        // Simulate updates
        fitnessData.newFitnessDataPushed(500, 5, 20);
        fitnessData.newFitnessDataPushed(9800, 85, 350);
        fitnessData.newFitnessDataPushed(10100, 90, 380); // Goal should trigger

        // Daily reset
        notifier.reset();
        fitnessData.dailyReset();
    }
}