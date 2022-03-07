package frc.robot; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj.Joystick;
<<<<<<< Updated upstream
=======
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;
>>>>>>> Stashed changes

//local imports
import frc.robot.Subsystems.*;
import frc.robot.LiftCommands.*;
import frc.robot.AutonCommands.*;
import frc.robot.TeleOpCommands.*;

//Holds all subsystems and commands for the robot and sets button bindings
public class RobotContainer {
    
    //Robot's controllers that drivers use
    Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICKPORT);
    Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICKPORT);
<<<<<<< Updated upstream

    //Subsystems and commands    
    // Chassis //
    HazyMechBase hazyMechBase = new HazyMechBase();
    CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);
=======
    
    //Subsystems and commands

    // Chassis //
    HazyMechBase hazyMechBase = new HazyMechBase();
    CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);

    // Intake //
    HazyIntake hazyIntake = new HazyIntake();
    CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);

    // Shooter //
    HazyShooter hazyShooter = new HazyShooter();
    CommandShoot commandShoot = new CommandShoot(hazyShooter);
    CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);

    // Autonomous //
    SequentialCommandGroup twoballAuton = new SequenceTwoBallAuton(hazyMechBase, hazyShooter, hazyIntake);
>>>>>>> Stashed changes
    
    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
    public RobotContainer(){
        configureButtonBindings();
        hazyMechBase.setDefaultCommand(commandMecanum);
<<<<<<< Updated upstream
=======
        hazyIntake.setDefaultCommand(commandIntakeDefault);
        hazyShooter.setDefaultCommand(commandShooterDefault);
>>>>>>> Stashed changes
    }
    
    //Use this method to define button->command mappings
    public void configureButtonBindings () {
<<<<<<< Updated upstream
        
=======
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                      //Right joystick Trigger    --> spin intake
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);                       //Left joystick Trigger     --> spit intake
        new JoystickButton(hazyController, Button.kB.value).whileHeld(commandRaiseDropIntake);  //B                         --> raise or drop intake
        new JoystickButton(hazyController, Button.kA.value).toggleWhenPressed(commandShoot);    //A                         --> start or stop shooter
    }

    public SequentialCommandGroup getAutonomousCommand(){
        return twoballAuton;
>>>>>>> Stashed changes
    }
}