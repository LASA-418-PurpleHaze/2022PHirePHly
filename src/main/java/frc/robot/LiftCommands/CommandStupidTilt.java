package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandStupidTilt extends CommandBase {

    private HazyLift c_IntialLift;
    public CommandStupidTilt(HazyLift lift) {
        c_IntialLift = lift;
        addRequirements(c_IntialLift);
    }

    @Override
    public void execute() {
        c_IntialLift.stupidTilt();
    }
}