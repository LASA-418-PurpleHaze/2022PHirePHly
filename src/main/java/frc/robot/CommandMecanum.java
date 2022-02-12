package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandMecanum extends CommandBase {
    
    //Declare the controllers and subsystem used to control the command
    private HazyMechBase c_hazyMechBase;
    private Joystick c_leftJoystick;
    private Joystick c_rightJoystick;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandMecanum(HazyMechBase subsystem, Joystick left, Joystick right){
        c_hazyMechBase = subsystem;
        c_leftJoystick = left;
        c_rightJoystick = right;
        addRequirements(c_hazyMechBase); //Links the command to the subsystem it affects
    }

    //The function that is called by the commandscheduler when command is called
    public void execute(){
        c_hazyMechBase.driveCartesian(0, c_leftJoystick.getY(), c_rightJoystick.getX());
    }
}