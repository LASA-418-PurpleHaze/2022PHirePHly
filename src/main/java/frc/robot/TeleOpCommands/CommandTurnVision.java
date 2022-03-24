package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;

public class CommandTurnVision extends CommandBase {
    
    private HazyMechBase c_hazyMechBase;

    public CommandTurnVision(HazyMechBase base){
        c_hazyMechBase = base;
        addRequirements(c_hazyMechBase);    
}

    @Override
    public void execute(){
        c_hazyMechBase.readData();
        c_hazyMechBase.turnToTarget();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
