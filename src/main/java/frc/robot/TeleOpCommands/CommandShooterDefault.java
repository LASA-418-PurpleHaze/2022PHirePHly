package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyShooter;

public class CommandShooterDefault extends CommandBase{

    //Declare the controllers and subsystem used to control the command
    private HazyShooter c_hazyShooter;
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandShooterDefault(HazyShooter shooter){
        c_hazyShooter = shooter;
        addRequirements(shooter);
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyShooter.stopFeeder();
        c_hazyShooter.stopShooter();
    }

    @Override
    public boolean isFinished () {
        return false;
    }
}