package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandSpinIntake extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandSpinIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        c_hazyIntake.spin();
    }
}
