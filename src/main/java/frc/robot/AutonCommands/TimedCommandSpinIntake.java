package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyIntake;

public class TimedCommandSpinIntake extends CommandBase {
    private HazyIntake c_hazyIntake;
    private double startTime;
    public TimedCommandSpinIntake (double time, HazyIntake subsystem) {
        startTime = time;
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
        
    }

    @Override
    public void execute () {
        c_hazyIntake.spin();
    }
    @Override
    public boolean isFinished() {
            if(java.lang.System.currentTimeMillis() > startTime + 2000){
                return true;
            }
        return false;
    }
}
