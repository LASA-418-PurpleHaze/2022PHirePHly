package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyShooter;

public class CommandShoot extends CommandBase{

    //Declare the controllers and subsystem used to control the command
    private HazyShooter c_hazyShooter;
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandShoot(HazyShooter shooter){
        c_hazyShooter = shooter;
    }

    //The function that is called by the commandscheduler when command is called
    public void execute(){
        c_hazyShooter.shoot();
    }
}