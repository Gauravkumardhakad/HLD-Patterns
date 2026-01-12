import java.util.Stack;

// define command interface
interface Command{
    void execute();
    void undo();
}

// define the receivers(devices)
class Light{
    void on(){
        System.out.println("light turned on");
    }

    void off(){
        System.out.println("light turned off");
    }
}

class Thermostat{
    private int current=20;

    void setTemperature(int temp){
        current=temp;
    }

    int getTemperature(){
        return current;
    }
}

// implementing the concrete command classes
class LightOnCommand implements Command{
    private final Light light;

    public LightOnCommand(Light light){
        this.light=light;
    }

    @Override
    public void execute(){
        light.on();
    }

    @Override
    public void undo(){
        light.off();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class SetTemperatureCommand implements Command {
    private final Thermostat thermostat;
    private final int newTemperature;
    private int previousTemperature;

    public SetTemperatureCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.newTemperature = temperature;
    }

    @Override
    public void execute() {
        previousTemperature = thermostat.getTemperature();
        thermostat.setTemperature(newTemperature);
    }

    @Override
    public void undo() {
        thermostat.setTemperature(previousTemperature);
    }
}


// creating the invoker (remote)
class SmartButton{
    private Command currentCommand;
    private final Stack<Command> history=new Stack<>();

    public void setCommand(Command command){
        this.currentCommand=command;
    }

    public void press(){
        if (currentCommand != null) {
            currentCommand.execute();
            history.push(currentCommand);
        } else {
            System.out.println("No command assigned.");
        }
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}


// creating the client
public class Demo {
    public static void main(String[] a) {
        // Receivers
        Light light = new Light();
        Thermostat thermostat = new Thermostat();

        // Commands
        Command lightOn  = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command setTemp22 = new SetTemperatureCommand(thermostat, 22);

        // Invoker
        SmartButton button = new SmartButton();

        // Simulate usage
        System.out.println("→ Pressing Light ON");
        button.setCommand(lightOn);
        button.press();

        System.out.println("→ Pressing Set Temp to 22°C");
        button.setCommand(setTemp22);
        button.press();

        System.out.println("→ Pressing Light OFF");
        button.setCommand(lightOff);
        button.press();

        // Undo sequence
        System.out.println("\n↶ Undo Last Action");
        button.undoLast();  // undo Light OFF

        System.out.println("↶ Undo Previous Action");
        button.undoLast();  // undo Set Temp

        System.out.println("↶ Undo Again");
        button.undoLast();  // undo Light ON
    }
}