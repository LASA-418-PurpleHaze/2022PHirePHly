package frc.robot; //folder the file is in

import edu.wpi.first.hal.HAL;
//wpilib imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

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
    CommandResetMecanumEncoders commandResetMecanumEncoders = new CommandResetMecanumEncoders(hazyMechBase);
    CommandMoveForward commandMoveForward = new CommandMoveForward(hazyMechBase);

    // Intake //
    HazyIntake hazyIntake = new HazyIntake();
    //CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    CommandDropIntake commandDropIntake = new CommandDropIntake(hazyIntake);
    CommandRaiseIntake commandRaiseIntake = new CommandRaiseIntake(hazyIntake);
    CommandHalfRaiseIntake commandHalfRaiseIntake = new CommandHalfRaiseIntake(hazyIntake);
    CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);
    CommandStopDropIntake commandStopDropIntake = new CommandStopDropIntake(hazyIntake);
    CommandResetIntakeEncoders commandResetIntakeEncoders = new CommandResetIntakeEncoders(hazyIntake);
    TimedCommandDropIntake timedCommandDropIntake = new TimedCommandDropIntake(hazyIntake);
    TimedCommandRaiseIntake timedCommandRaiseIntake = new TimedCommandRaiseIntake(hazyIntake);

    


    // Shooter //
    HazyShooter hazyShooter = new HazyShooter();
    CommandShoot commandShoot = new CommandShoot(hazyShooter);
    CommandShootAndFeed commandShootAndFeed = new CommandShootAndFeed(hazyShooter);
    CommandHighFeed commandHighFeed = new CommandHighFeed(hazyShooter);
    CommandHighFeedBack commandHighFeedBack = new CommandHighFeedBack(hazyShooter);
    CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);
    CommandShootLow commandShootLow = new CommandShootLow(hazyShooter);
    CommandAutoShoot commandAutoShoot = new CommandAutoShoot(hazyShooter);
    // CommandToggleFeeder commandToggleFeeder = new CommandToggleFeeder(hazyShooter);

    // Lift //
    HazyLift hazyLift = new HazyLift();
    CommandResetLiftEncoder commandResetLiftEncoder = new CommandResetLiftEncoder(hazyLift);
    CommandStupidDefault commandStupidDefault = new CommandStupidDefault(hazyLift);
    CommandStupidLift commandStupidLift = new CommandStupidLift(hazyLift);
    CommandStupidDown commandStupidDown = new CommandStupidDown(hazyLift);
    CommandStupidTiltOut commandStupidTiltOut = new CommandStupidTiltOut(hazyLift);
    CommandStupidTiltIn commandStupidTiltIn = new CommandStupidTiltIn(hazyLift);
    CommandStupidTiltInLiftDown commandStupidTiltInLiftDown = new CommandStupidTiltInLiftDown(hazyLift);
    CommandStupidTiltOutLiftUp commandStupidTiltOutLiftUp = new CommandStupidTiltOutLiftUp(hazyLift);

    CommandEnableStops commandEnableStops = new CommandEnableStops(hazyLift);
    CommandDisableStops commandDisableStops = new CommandDisableStops(hazyLift);

    CommandBarTwoLiftUp commandBarTwoLiftUp = new CommandBarTwoLiftUp(hazyLift);

    SequenceBarThreeTiltExtend sequenceBarThreeTiltExtend = new SequenceBarThreeTiltExtend(hazyLift);
    CommandBarThreeTiltBack commandBarThreeTiltBack = new CommandBarThreeTiltBack(hazyLift);
    CommandBarThreePull commandBarThreePull = new CommandBarThreePull(hazyLift);

    // Autonomous //
    SendableChooser<Command> chooser = new SendableChooser<>();
    SequentialCommandGroup twoBallAuton = new SequenceTwoBallAuton(hazyMechBase, hazyShooter, hazyIntake);
    
    //This constructor is called once in Robotinit and should set up all button->command bindings and default commands
    public RobotContainer(){
        configureButtonBindings();

        hazyMechBase.setDefaultCommand(commandMecanum);
        hazyIntake.setDefaultCommand(commandIntakeDefault);
        hazyShooter.setDefaultCommand(commandShooterDefault);
        hazyLift.setDefaultCommand(commandStupidDefault);
        
        //Not sure if lift needs a default command since all it does is go to PID positions so it won't keep spinning forever, maybe I'm wrong

        chooser.setDefaultOption("Two Ball Auto", twoBallAuton);
        // chooser.addOption("One Ball Auto", oneBallAuto);
        Shuffleboard.getTab("Autonomous").add(chooser);
        
    }
    
    //Use this method to define button->command mappings
    public void configureButtonBindings () {
        // Chassis //
        // new JoystickButton(rightJoystick, 4).whenPressed(commandSwapDirection);                      // RJ 4 --> swap direction
        new JoystickButton(leftJoystick, 3).whileHeld(commandPreciseMecanum);                           // LJ 2 --> precise mecanum
        new JoystickButton(rightJoystick, 2).whileHeld(commandTurnVision);
         new JoystickButton(rightJoystick, 2).whileHeld(commandSpinIntake);                           // RJ 2 --> turn to vision
        new JoystickButton(rightJoystick, 3).whileHeld(commandFollowVision);                            // RJ 3 --> follow vision
        // new JoystickButton(leftJoystick, 8).whenPressed(commandMoveForward);

        // Intake //
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                              // RJ 1 --> spin intake
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);                               // LJ 1 --> spin intake (backwards)
        //new JoystickButton(hazyController, 3).whenPressed(commandDropIntake);  
        //new JoystickButton(rightJoystick, 8).whenPressed(commandHalfRaiseIntake);                              // RJ 1 --> spin intake
        // B    --> raise/drop intake
        new JoystickButton(hazyController, 1).whenPressed(commandStopDropIntake);      // X    --> half lift intake to get bouncing balls
        //new JoystickButton(hazyController, 4).whenPressed(commandRaiseIntake);
        // new JoystickButton(hazyController, Button.kY.value).whenPressed(commandShoot);               //      --> half lift intake to get bouncing balls
        new JoystickButton(rightJoystick, 7).whenPressed(commandResetIntakeEncoders);

        new JoystickButton(hazyController, 3).whenPressed(timedCommandDropIntake);
        new JoystickButton(hazyController, 4).whenPressed(timedCommandRaiseIntake);

        // Shooter //
        new JoystickButton(hazyController, 2).toggleWhenPressed(commandShoot);            // A    --> full shoot by itself
        new JoystickButton(hazyController, 9).whileHeld(commandHighFeedBack);
        new JoystickButton(hazyController, 9).whileHeld(commandSpitIntake);
        new JoystickButton(hazyController, 10).toggleWhenPressed(commandShootAndFeed);
        new HazyDPad(hazyController, HazyDPad.RIGHT).toggleWhenPressed(commandShootLow);
        new HazyDPad(hazyController, HazyDPad.UP).toggleWhenPressed(commandAutoShoot);


        // Lift //
        new JoystickButton(hazyController, 11).whileHeld(commandStupidDown);       // press left xbox stick    --> manually move lift down
        new JoystickButton(hazyController, 12).whileHeld(commandStupidLift);      // press right xbox stick   --> manually move lift up 
        new JoystickButton(hazyController, 5).whileHeld(commandStupidTiltIn);       
        new JoystickButton(hazyController, 6).whileHeld(commandStupidTiltOut);
        new JoystickButton(hazyController, 7).whileHeld(commandStupidTiltInLiftDown);
        new JoystickButton(hazyController, 8).whileHeld(commandStupidTiltOutLiftUp);
        
        // new JoystickButton(leftJoystick, 3).whenPressed(commandBarTwoLiftUp);                        // LJ 3                     --> lift up to above bar 2
        // new JoystickButton(leftJoystick, 5).whenPressed(commandBarTwoLiftDown);                      // LJ 5                     --> lift down to be completely on bar 2

        // Etc //
        /*new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetIntakeEncoders);
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetIntakeEncoders);
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetMecanumEncoders);
        */
        //new JoystickButton(hazyController, Button.kBack.value).toggleWhenPressed(commandShootLow);
        //new JoystickButton(leftJoystick, 8).whenPressed(commandMoveForward);

        new JoystickButton(leftJoystick, 6).whenPressed(commandEnableStops);
        new JoystickButton(leftJoystick, 11).whenPressed(commandDisableStops);

        new JoystickButton(leftJoystick, 7).whenPressed(commandResetLiftEncoder);
        //new JoystickButton(hazyController, Button.kBack.value).whenPressed(commandResetAllEncoders);
    }

    public void putAllData () {
        hazyMechBase.putData();
        hazyIntake.putData();
        hazyShooter.putData();
        hazyLift.putData();
    }

    // public void DPadWrapper() {
    //     if(hazyController.getPOV() == 0) {} //Up
    //     else if(hazyController.getPOV() == 90){ //Right
    //         commandShootLow.execute();                    // DPad right --> tilt arm back to be on bar 3
    //     }
    //     else if(hazyController.getPOV() == 180){ //Down
    //         //commandBarThreePull.execute();                // DPad down  --> pull arm to bar 3
    //     }
    //     else if(hazyController.getPOV() == 270){ //Left
    //         //sequenceBarThreeTiltExtend.execute();         // DPad left  --> tilt arm to bar 3 and extend to bar 3
    //     }
    // }

    //Sends autonomous command so that it can be used in robot.java
    public Command getAutonomousCommand() {
        //return chooser.getSelected();
        return twoBallAuton;
    }
}