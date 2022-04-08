package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyShooter;

public class TimedCommandHighFeedBack extends CommandBase {

    private HazyShooter c_hazyShooter;
    private long startTime;

    public TimedCommandHighFeedBack(HazyShooter subsystem){
        c_hazyShooter = subsystem;
        addRequirements(c_hazyShooter);
    }

    @Override
    public void initialize(){
        startTime = java.lang.System.currentTimeMillis();
    }

    @Override
    public void execute () {
        c_hazyShooter.backFeeder();
    }

    @Override
    public boolean isFinished() {
        if (java.lang.System.currentTimeMillis() > startTime + 650) {
            return true;
        }
        return false;
    }
}