package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;
import frc.robot.*;

public class CommandBarThreeTilt extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandBarThreeTilt(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        c_hazyLift.tilt(RobotMap.BARTHREETILT);
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}