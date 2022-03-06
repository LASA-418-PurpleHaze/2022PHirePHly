package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button;

//Holds all subsystems and commands for the robot and sets button bindings
public class RobotContainer {
    
    //Robot's controllers that drivers use
    XboxController hazyController = new XboxController(RobotMap.CONTROLLERPORT);
    Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICKPORT);
    Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICKPORT);
    
    //Subsystems and commands
    // Chassis //
    HazyMechBase hazyMechBase = new HazyMechBase();
    CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);

    // Shooter //
    HazyShooter hazyShooter = new HazyShooter();
    HazyLift hazyLift = new HazyLift();
    CommandShoot commandShoot = new CommandShoot(hazyShooter);
    CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);
    CommandLift commandLift = new CommandLift(hazyLift);
    CommandTilt commandTilt = new CommandTilt(hazyLift);

    HazyIntake hazyIntake = new HazyIntake();
    CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);

    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
    //Default commands are what commands run in on a subsystem when no other command is scheduled (called)
    public RobotContainer(){
        configureButtonBindings();
        hazyIntake.setDefaultCommand(commandIntakeDefault);
        hazyMechBase.setDefaultCommand(commandMecanum);
        hazyShooter.setDefaultCommand(commandShooterDefault);
    }
    
    //Use this method to define button->command mappings
    public void configureButtonBindings () {
        // Shooter //
        //new JoystickButton(hazyController, Button.kRightStick.value).toggleWhenPressed(commandShoot);
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                      
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);    
        new JoystickButton(hazyController, Button.kRightStick.value).whileHeld(commandShoot);
        new JoystickButton(hazyController, Button.kA.value).whileHeld(commandTilt);
        new JoystickButton(hazyController, Button.kB.value).whileHeld(commandLift); 




    }
    public void DPadWrapper() {
        if (hazyController.getPOV() == 0) {
            //hazyIntake.drop();
        }
    }
}