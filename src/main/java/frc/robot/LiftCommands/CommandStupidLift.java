package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
// import frc.robot.Print;
//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandStupidLift extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandStupidLift(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        PHrint.p();
        c_hazyLift.stupidLift();
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}