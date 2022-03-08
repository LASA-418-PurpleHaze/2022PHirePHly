package frc.robot; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;

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
    XboxController hazyController = new XboxController(RobotMap.CONTROLLERPORT);
    
    //Subsystems and commands

    // Chassis //
    HazyMechBase hazyMechBase = new HazyMechBase();
    CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);
    CommandFollowVision commandFollowVision = new CommandFollowVision(hazyMechBase);
    CommandTurnVision commandTurnVision = new CommandTurnVision(hazyMechBase);
    CommandPreciseMecanum commandPreciseMecanum = new CommandPreciseMecanum(hazyMechBase, leftJoystick, rightJoystick);

    // Intake //
    HazyIntake hazyIntake = new HazyIntake();
    CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);

    // Shooter //
    HazyShooter hazyShooter = new HazyShooter();
    CommandShoot commandShoot = new CommandShoot(hazyShooter);
    CommandHighFeed commandHighFeed = new CommandHighFeed(hazyShooter);
    CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);

    // Lift //
    HazyLift hazyLift = new HazyLift();
    CommandBarTwoLiftUp commandBarTwoLiftUp = new CommandBarTwoLiftUp(hazyLift);
    CommandBarTwoLiftDown commandBarTwoLiftDown = new CommandBarTwoLiftDown(hazyLift);
    CommandBarThreeTilt commandBarThreeTilt = new CommandBarThreeTilt(hazyLift);
    CommandBarThreeLiftDown commandBarThreeLiftDown = new CommandBarThreeLiftDown(hazyLift);
    CommandBarFourTilt commandBarFourTilt = new CommandBarFourTilt(hazyLift);
    CommandBarFourLiftDown commandBarFourLiftDown = new CommandBarFourLiftDown(hazyLift);
    CommandStupidLift commandStupidLift = new CommandStupidLift(hazyLift);
    CommandStupidDown commandStupidDown = new CommandStupidDown(hazyLift);
    CommandStupidDefault commandStupidDefault = new CommandStupidDefault(hazyLift);
    CommandReset commandReset = new CommandReset(hazyLift);
    CommandStupidTilt commandStupidTilt = new CommandStupidTilt(hazyLift);
    CommandStupidBack commandStupidBack = new CommandStupidBack(hazyLift);
    CommandResetIntakeEncoders commandResetIntakeEncoders = new CommandResetIntakeEncoders(hazyIntake);
    // Autonomous //
    SequentialCommandGroup twoballAuton = new SequenceTwoBallAuton(hazyMechBase, hazyShooter, hazyIntake);
    
    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
    public RobotContainer(){
        configureButtonBindings();
        hazyMechBase.setDefaultCommand(commandMecanum);
        hazyIntake.setDefaultCommand(commandIntakeDefault);
        hazyShooter.setDefaultCommand(commandShooterDefault);
        hazyLift.setDefaultCommand(commandStupidDefault);
        //Not sure if lift needs a default command since all it does is go to PID positions so it won't keep spinning forever, maybe I'm wrong
    }
    
    //Use this method to define button->command mappings
    public void configureButtonBindings () {
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                      //Right joystick Trigger    --> spin intake
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);                       //Left joystick Trigger     --> spit intake
        new JoystickButton(rightJoystick, 2).whileHeld(commandTurnVision);                    //Right joystick thumb      --> makes robot turn to target and go to shooting distance
        new JoystickButton(leftJoystick, 2).whileHeld(commandPreciseMecanum);                   //Left joystick thumb       --> quarters all joystick inputs so the robot moves slower and is easier to control
        new JoystickButton(hazyController, Button.kB.value).whileHeld(commandRaiseDropIntake);  //B                         --> raise or drop intake
        new JoystickButton(hazyController, Button.kA.value).toggleWhenPressed(commandShoot);    //A                         --> start or stop shooter
        new JoystickButton(hazyController, Button.kX.value).whileHeld(commandHighFeed);         //X                         --> manually spin the high feeder
        new JoystickButton(hazyController, Button.kRightStick.value).whileHeld(commandStupidLift);
        new JoystickButton(hazyController, Button.kLeftStick.value).whileHeld(commandStupidDown);
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandBarTwoLiftUp);
        new JoystickButton(rightJoystick, 4).whenPressed(commandReset);
        new JoystickButton(hazyController, Button.kBack.value).whenPressed(commandBarThreeTilt);
        new JoystickButton(rightJoystick, 4).whenPressed(commandResetIntakeEncoders);

        // new JoystickButton(hazyController, Button.kLeftBumper.value).whenPressed(commandStupidTilt);
        // new JoystickButton(hazyController, Button.kRightBumper.value).whenPressed(commandStupidBack);
    }

    public void DPadWrapper() {
        if(hazyController.getPOV() == 0){
            commandBarTwoLiftUp.execute();
        }
        else if(hazyController.getPOV() == 90){
            commandBarTwoLiftDown.execute();
        }
        else if(hazyController.getPOV() == 180){
            commandBarThreeTilt.execute();
        }
        else if(hazyController.getPOV() == 270){
            commandBarThreeLiftDown.execute();
        }
    }
    //Sends autonomous command so that it can be used in robot.java
    public SequentialCommandGroup getAutonomousCommand(){
        return twoballAuton;
    }
}