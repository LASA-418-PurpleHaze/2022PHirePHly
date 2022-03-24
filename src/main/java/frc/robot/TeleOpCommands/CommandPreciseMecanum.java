package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;

public class CommandPreciseMecanum extends CommandBase {
    
    //Declare the controllers and subsystem used to control the command
    private final HazyMechBase c_hazyMechBase;
    private final Joystick c_leftJoystick;
    private final Joystick c_rightJoystick;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandPreciseMecanum(HazyMechBase subsystem, Joystick left, Joystick right){
        c_hazyMechBase = subsystem;
        c_leftJoystick = left;
        c_rightJoystick = right;
        addRequirements(c_hazyMechBase); //Links the command to the subsystem it affects
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyMechBase.driveCartesian(0.5 * c_leftJoystick.getX(), 0.5 * -c_leftJoystick.getY(), 0.5 * -c_rightJoystick.getX());
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}