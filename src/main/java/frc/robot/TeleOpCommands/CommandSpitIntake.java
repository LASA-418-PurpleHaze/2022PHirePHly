package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandSpitIntake extends CommandBase {
    
    private HazyIntake c_hazyIntake;

    public CommandSpitIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        c_hazyIntake.spit();
    }

    @Override
    public boolean isFinished () {
        return false;
    }
}
