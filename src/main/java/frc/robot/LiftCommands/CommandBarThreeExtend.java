package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandBarThreeExtend extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandBarThreeExtend (HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        //c_hazyLift.lift(RobotMap.BARTHREEEXTEND);
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}