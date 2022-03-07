package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandTwoBallAuto extends CommandBase {
    HazyMechBase c_hazyMechBase;
    HazyShooter c_hazyShooter;
    HazyIntake c_hazyIntake;
    
    public CommandTwoBallAuto (HazyMechBase mechBase, HazyShooter shooter, HazyIntake intake) {
        c_hazyMechBase = mechBase;
        c_hazyShooter = shooter;
        c_hazyIntake = intake;
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyIntake.dropOrRaise();
        c_hazyIntake.spin();
        c_hazyMechBase.moveXInches(-36);
        c_hazyIntake.stopSpinning();
        c_hazyShooter.shoot();
    }
}
