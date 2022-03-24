package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandSpinIntake extends CommandBase {
    
    private HazyIntake c_hazyIntake;

    public CommandSpinIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        c_hazyIntake.spin();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
