package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyIntake;

public class TimedCommandDropIntake extends CommandBase {
    private HazyIntake c_hazyIntake;
    private long startTime;
    public TimedCommandDropIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }
    @Override
    public void initialize(){
        startTime = java.lang.System.currentTimeMillis();
    }


    @Override
    public void execute () {
        //print.p();
        c_hazyIntake.drop();
    }

    @Override
    public boolean isFinished() {
        if (java.lang.System.currentTimeMillis()> startTime + 750) {
            return true;
        } else {
            return false;
        }
    }
}