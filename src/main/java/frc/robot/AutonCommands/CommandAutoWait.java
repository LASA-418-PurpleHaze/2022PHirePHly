package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyShooter;
public class CommandAutoWait extends CommandBase {

    //Declare the controllers and subsystem used to control the command
    private HazyShooter c_hazyShooter;
    long startTime;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandAutoWait(){
    }

    @Override
    public void initialize(){
        startTime = java.lang.System.currentTimeMillis();
    }

    //The function that is called by the commandscheduler when command is called
    public void execute(){
        //c_hazyShooter.shootAuto();
    }

    @Override
    public boolean isFinished(){
        if (java.lang.System.currentTimeMillis() > startTime + 4000) {
            return true;
        } else {
            return false;
        }
    }
}