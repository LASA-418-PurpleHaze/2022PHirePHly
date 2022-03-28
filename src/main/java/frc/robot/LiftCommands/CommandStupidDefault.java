package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandStupidDefault extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandStupidDefault(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        c_hazyLift.stupidDefault();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}