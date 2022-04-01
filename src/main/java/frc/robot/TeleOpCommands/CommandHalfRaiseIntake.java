package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandHalfRaiseIntake extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandHalfRaiseIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        //print.p();
        c_hazyIntake.halfRaise();
    }

    @Override
    public boolean isFinished() {
        return false;
        //return c_hazyIntake.did3Drop();
    }
}