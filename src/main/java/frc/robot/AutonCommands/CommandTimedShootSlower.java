package frc.robot.AutonCommands; //folder the file is in
//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//local imports
import frc.robot.Subsystems.HazyShooter;
public class CommandTimedShootSlower extends CommandBase{
    //Declare the controllers and subsystem used to control the command
    private HazyShooter c_hazyShooter;
    long startTime;
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandTimedShootSlower(HazyShooter shooter){
        c_hazyShooter = shooter;
        addRequirements(shooter);
    }
    @Override
    public void initialize(){
        startTime = java.lang.System.currentTimeMillis();
    }
    //The function that is called by the commandscheduler when command is called
    public void execute(){
        c_hazyShooter.shootSlow();
    }

    @Override
    public boolean isFinished(){
        if(java.lang.System.currentTimeMillis() > startTime + 2000){
            return true;
        }
        return false;
    }
}