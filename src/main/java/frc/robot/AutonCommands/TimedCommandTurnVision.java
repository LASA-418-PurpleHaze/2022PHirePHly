package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;

public class TimedCommandTurnVision extends CommandBase {
    private HazyMechBase c_hazyMechBase;
    private double startTime;
    public TimedCommandTurnVision(double time, HazyMechBase base){
        c_hazyMechBase = base;
        startTime = time;
        addRequirements(c_hazyMechBase);    
}

    @Override
    public void execute(){
        c_hazyMechBase.readData();
        c_hazyMechBase.turnToTarget();
    }
    @Override
    public boolean isFinished() {
        if(java.lang.System.currentTimeMillis() > startTime + 7000){
            return true;
        }
        return false;
    }

}
