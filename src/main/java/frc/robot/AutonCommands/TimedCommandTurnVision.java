package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;
import frc.robot.Print;

public class TimedCommandTurnVision extends CommandBase {

    private HazyMechBase c_hazyMechBase;
    private long startTime;

    public TimedCommandTurnVision(HazyMechBase base){
        c_hazyMechBase = base;
        addRequirements(c_hazyMechBase);
    }

    @Override
    public void initialize(){
        startTime = java.lang.System.currentTimeMillis();
    }

    @Override
    public void execute(){
        c_hazyMechBase.readData();
        c_hazyMechBase.turnToTarget();
        //c_hazyMechBase.goToTarget();
    }

    @Override
    public boolean isFinished() {
        if (java.lang.System.currentTimeMillis()> startTime + 2000) {
            return true;
        } else {
            return false;
        }
    }
}