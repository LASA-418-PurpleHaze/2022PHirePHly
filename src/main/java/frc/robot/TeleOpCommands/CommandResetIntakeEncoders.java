package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandResetIntakeEncoders extends CommandBase {

    private HazyIntake c_resetIntake;
    public CommandResetIntakeEncoders(HazyIntake intake) {
        c_resetIntake = intake;
        addRequirements(c_resetIntake);
    }

    @Override
    public void execute() {
        c_resetIntake.resetEncoders();
    }

    @Override
    public boolean isFinished () {
        return true;
    }
}