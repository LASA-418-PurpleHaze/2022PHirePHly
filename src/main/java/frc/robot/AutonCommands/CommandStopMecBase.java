package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyMechBase;

public class CommandStopMecBase extends CommandBase {
    
    private HazyMechBase c_hazyMechBase;

    public CommandStopMecBase(HazyMechBase base){
        c_hazyMechBase = base;
        addRequirements(c_hazyMechBase);    
    }

    @Override
    public void execute(){
        c_hazyMechBase.driveCartesian(0,0,0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}