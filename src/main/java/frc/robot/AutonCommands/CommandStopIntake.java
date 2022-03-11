package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//local imports
import frc.robot.Subsystems.HazyIntake;

public class CommandStopIntake extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandStopIntake(HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        c_hazyIntake.defaultC();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
