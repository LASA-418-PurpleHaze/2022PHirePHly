package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandDisableStops extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandDisableStops(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        c_hazyLift.stopsOff();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}