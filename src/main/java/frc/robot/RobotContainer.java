package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
    
    //Robot's controllers that drivers use
    Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICKPORT);
    Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICKPORT);
    
    //Chassis subystem and command setup
    HazyMechBase hazyMechBase = new HazyMechBase();
    CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);
    
    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
    public RobotContainer(){
        configureButtonBindings();
        hazyMechBase.setDefaultCommand(commandMecanum);
    }
    
    //Use this method to define button->command mappings
    public void configureButtonBindings () {

    }
}
