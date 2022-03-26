package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandDropIntake extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandDropIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        //print.p();
        c_hazyIntake.drop();
    }

    @Override
    public boolean isFinished() {
        return c_hazyIntake.didDrop();
    }
}