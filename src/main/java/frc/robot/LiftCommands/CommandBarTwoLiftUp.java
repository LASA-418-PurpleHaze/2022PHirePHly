package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyLift;

public class CommandBarTwoLiftUp extends CommandBase {

    private HazyLift c_hazyLift;
    public CommandBarTwoLiftUp(HazyLift lift) {
        c_hazyLift = lift;
        addRequirements(c_hazyLift);
    }

    @Override
    public void execute() {
        //c_hazyLift.lift(RobotMap.BARTWOUP);
        //c_IntialLift.tilt(0);
    }
    @Override
    public boolean isFinished() {
        return false;
    }
}