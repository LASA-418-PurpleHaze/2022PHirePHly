package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Print;
//local imports
import frc.robot.Subsystems.HazyShooter;

public class CommandShoot extends CommandBase{

    //Declare the controllers and subsystem used to control the command
    private HazyShooter c_hazyShooter;

    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandShoot(HazyShooter shooter){
        c_hazyShooter = shooter;
        addRequirements(shooter);
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        // //print.p();
        c_hazyShooter.shoot();
    }
}