package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandResetLiftEncoder extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandResetLiftEncoder (HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        c_hazyLift.resetEncoders();;
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}