package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandIntakeDefault extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandIntakeDefault (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        c_hazyIntake.defaultC();
    }
}
