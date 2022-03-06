package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandRaiseDropIntake extends CommandBase {
    private HazyIntake c_hazyIntake;

    public CommandRaiseDropIntake (HazyIntake subsystem) {
        c_hazyIntake = subsystem;
        addRequirements(c_hazyIntake);
    }

    @Override
    public void execute () {
        //c_hazyIntake.resetEncoderTicks();
        c_hazyIntake.drop();
    }

    public boolean isFinished(){
        return true;
    }
}