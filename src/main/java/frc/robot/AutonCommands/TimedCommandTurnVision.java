package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;

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
        //System.out.println("starttime " + startTime + ", current " + java.lang.System.currentTimeMillis());
        //System.out.println("Difference: " + (java.lang.System.currentTimeMillis() - startTime));
        if (java.lang.System.currentTimeMillis()> startTime + 2000) {
            // System.out.println("turn vision finished");
            return true;
        } else {
            return false;
        }
    }
}