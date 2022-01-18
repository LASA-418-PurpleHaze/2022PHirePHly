package frc.robot;

public class RobotContainer {
    public static HazyMechBase hazyMechBase;
    public static CommandMecanum commandMecanum;


    public RobotContainer(){
        hazyMechBase = new HazyMechBase();
        commandMecanum = new CommandMecanum(hazyMechBase);
    }
}
