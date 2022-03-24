package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

//local imports
import frc.robot.Subsystems.*;

public class CommandDriveDistance extends CommandBase {
    
    //Declare the controllers and subsystem used to control the command
    private final HazyMechBase c_hazyMechBase;
    private final double distance;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandDriveDistance(HazyMechBase chassis, double x){
        c_hazyMechBase = chassis;
        distance = x;
        addRequirements(c_hazyMechBase); //Links the command to the subsystem it affects
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyMechBase.moveFeet(distance);
    }

    @Override
    public boolean isFinished(){
        return c_hazyMechBase.didMoveFeet(RobotMap.AUTONTAXIDISTANCE, c_hazyMechBase.getLFrontSparkMax());
    }
}