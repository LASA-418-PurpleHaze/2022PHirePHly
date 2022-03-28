package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandStupidTiltOut extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandStupidTiltOut(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        c_hazyLift.stupidTiltOut();
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}