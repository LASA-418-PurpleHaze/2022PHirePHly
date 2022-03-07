package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandOneBallAuto extends CommandBase {
    HazyMechBase c_hazyMechBase;
    HazyShooter c_hazyShooter;
    
    public CommandOneBallAuto (HazyMechBase mechBase, HazyShooter shooter) {
        c_hazyMechBase = mechBase;
        c_hazyShooter = shooter;
    }

    //The function that is called by the commandscheduler when command is called
    @Override
    public void execute(){
        c_hazyMechBase.moveXInches(-36);
        c_hazyShooter.shoot();
    }
}
