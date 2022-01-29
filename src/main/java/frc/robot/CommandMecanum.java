package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandMecanum extends CommandBase {

    private final HazyMechBase c_hazyMechBase;
    private final Joystick c_leftJoystick;
    private final Joystick c_rightJoystick;

    public CommandMecanum(HazyMechBase subsystem, Joystick left, Joystick right){
        c_hazyMechBase = subsystem;
        c_leftJoystick = left;
        c_rightJoystick = right;
        addRequirements(c_hazyMechBase);
    }

    @Override
    public void execute(){
        c_hazyMechBase.driveCartesian(0, c_leftJoystick.getY(), c_rightJoystick.getX());
    }

}
