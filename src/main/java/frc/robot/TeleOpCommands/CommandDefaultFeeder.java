package frc.robot.TeleOpCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PHrint;
//local imports
import frc.robot.Subsystems.HazyIntake;
import frc.robot.Subsystems.HazyShooter;

public class CommandDefaultFeeder extends CommandBase {
    private HazyShooter c_hazyShooter;

    public CommandDefaultFeeder (HazyShooter subsystem) {
        c_hazyShooter = subsystem;
        addRequirements(c_hazyShooter);
    }

    @Override
    public void execute () {
        //print.p();
        c_hazyShooter.stopFeeder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}