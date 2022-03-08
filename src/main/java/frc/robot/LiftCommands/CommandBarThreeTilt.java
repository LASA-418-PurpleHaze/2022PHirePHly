package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;
import frc.robot.*;

public class CommandBarThreeTilt extends CommandBase {

    private HazyLift c_IntialLift;
    public CommandBarThreeTilt(HazyLift lift) {
        c_IntialLift = lift;
        addRequirements(c_IntialLift);
    }

    @Override
    public void execute() {
        c_IntialLift.tilt(RobotMap.BARTHREETILTTICKS);
    }
}