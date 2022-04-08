package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
//local imports
import frc.robot.Subsystems.HazyMechBase;

public class CommandMoveForward extends CommandBase {
    
    //Declare the controllers and subsystem used to control the command
    private final HazyMechBase c_hazyMechBase;        
    double[] currentWheelPos;

    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandMoveForward(HazyMechBase subsystem){
        c_hazyMechBase = subsystem;
        addRequirements(c_hazyMechBase); //Links the command to the subsystem it affects
        //c_hazyMechBase.resetEncoders();
    }

    @Override
    public void initialize() {
        currentWheelPos = c_hazyMechBase.getCurrentWheelPos();
    }
    
    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyMechBase.moveFeet(RobotMap.AUTONTAXIDISTANCE, currentWheelPos);
        //c_hazyMechBase.moveFeet(RobotMap.AUTONTAXIDISTANCE);

    }
    
    @Override
    public boolean isFinished() {
        return c_hazyMechBase.didMoveFeet(currentWheelPos);
    }
}