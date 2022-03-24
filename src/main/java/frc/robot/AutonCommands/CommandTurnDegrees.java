package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.*;

public class CommandTurnDegrees extends CommandBase {

    //Declare the controllers and subsystem used to control the command
    private final HazyMechBase c_hazyMechBase;
    private final double degrees;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandTurnDegrees(HazyMechBase chassis, double x){
        c_hazyMechBase = chassis;
        degrees = x;
        addRequirements(c_hazyMechBase); //Links the command to the subsystem it affects
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyMechBase.turnDegrees(degrees);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}