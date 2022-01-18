package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandMecanum extends CommandBase{

    private final HazyMechBase m_hazymechBase;

    public CommandMecanum(HazyMechBase subsystem){
        m_hazymechBase = subsystem;
        addRequirements(m_hazymechBase);

    }

    @Override
    public void execute(){
        m_hazymechBase.driveCartesian(OI.leftJoystick.getX(), OI.leftJoystick.getY(), OI.rightJoystick.getX());
    }

}
