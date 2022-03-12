package frc.robot; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
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
    CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    CommandDropIntake commandDropIntake = new CommandDropIntake(hazyIntake);
    CommandRaiseIntake commandRaiseIntake = new CommandRaiseIntake(hazyIntake);
    CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);
    CommandStopDropIntake commandStopDropIntake = new CommandStopDropIntake(hazyIntake);
    CommandResetIntakeEncoders commandResetIntakeEncoders = new CommandResetIntakeEncoders(hazyIntake);

    // Shooter //
    HazyShooter hazyShooter = new HazyShooter();
    CommandShoot commandShoot = new CommandShoot(hazyShooter);
    CommandHighFeed commandHighFeed = new CommandHighFeed(hazyShooter);
    CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);
    CommandShootLow commandShootLow = new CommandShootLow(hazyShooter);
    // Lift //
    HazyLift hazyLift = new HazyLift();
    CommandResetLiftEncoder commandResetLiftEncoder = new CommandResetLiftEncoder(hazyLift);
    CommandStupidDefault commandStupidDefault = new CommandStupidDefault(hazyLift);
    CommandStupidLift commandStupidLift = new CommandStupidLift(hazyLift);
    CommandStupidDown commandStupidDown = new CommandStupidDown(hazyLift);
    CommandStupidTilt commandStupidTilt = new CommandStupidTilt(hazyLift);
    CommandStupidTiltBack commandStupidTiltBack = new CommandStupidTiltBack(hazyLift);


    CommandBarTwoLiftUp commandBarTwoLiftUp = new CommandBarTwoLiftUp(hazyLift);
    CommandBarTwoLiftDown commandBarTwoLiftDown = new CommandBarTwoLiftDown(hazyLift);

    SequenceBarThreeTiltExtend sequenceBarThreeTiltExtend = new SequenceBarThreeTiltExtend(hazyLift);
    CommandBarThreeTiltBack commandBarThreeTiltBack = new CommandBarThreeTiltBack(hazyLift);
    CommandBarThreePull commandBarThreePull = new CommandBarThreePull(hazyLift);

    // Autonomous //
    SendableChooser<Command> chooser = new SendableChooser<>();
    SequentialCommandGroup twoBallAuton = new SequenceTwoBallAuton(hazyMechBase, hazyShooter, hazyIntake);

    // Etc //
    //CommandResetAllEncoders commandResetAllEncoders = new CommandResetAllEncoders(hazyMechBase, hazyIntake, hazyLift);
    
    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
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
        new JoystickButton(leftJoystick, 2).whileHeld(commandPreciseMecanum);                         // LJ 2 --> precise mecanum
        new JoystickButton(rightJoystick, 2).whileHeld(commandTurnVision);
        new JoystickButton(rightJoystick, 2).whileHeld(commandSpinIntake);                              // RJ 2 --> turn to vision
        // RJ 2 --> turn to vision
        new JoystickButton(rightJoystick, 3).whileHeld(commandFollowVision);                            // RJ 3 --> follow vision

        // Intake //
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                              // RJ 1 --> spin intake
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);                               // LJ 1 --> spin intake (backwards)
        new JoystickButton(hazyController, Button.kB.value).whenPressed(commandDropIntake);        // B    --> raise/drop intake
        new JoystickButton(hazyController, Button.kX.value).whenPressed(commandStopDropIntake);      // X    --> half lift intake to get bouncing balls
        new JoystickButton(hazyController, Button.kY.value).whenPressed(commandRaiseIntake);
        //new JoystickButton(hazyController, Button.kY.value).whenPressed(commandShoot);      //     --> half lift intake to get bouncing balls

        // Shooter //
        new JoystickButton(hazyController, Button.kA.value).toggleWhenPressed(commandShoot);                  // A    --> full shoot by itself

        // Lift //
        new JoystickButton(hazyController, Button.kLeftStick.value).whileHeld(commandStupidDown);       // press left xbox stick    --> manually move lift down
        new JoystickButton(hazyController, Button.kRightStick.value).whileHeld(commandStupidLift);      // press right xbox stick   --> manually move lift up 
        //new JoystickButton(leftJoystick, 3).whenPressed(commandBarTwoLiftUp);                           // LJ 3                     --> lift up to above bar 2
        //new JoystickButton(leftJoystick, 5).whenPressed(commandBarTwoLiftDown);                        // LJ 5                     --> lift down to be completely on bar 2

        // Etc //
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetIntakeEncoders);
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetIntakeEncoders);
        new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetMecanumEncoders);

        new JoystickButton(hazyController, Button.kBack.value).toggleWhenPressed(commandShootLow);
        new JoystickButton(leftJoystick, 8).whenPressed(commandMoveForward);
        //new JoystickButton(hazyController, Button.kStart.value).whenPressed(commandResetLiftEncoders);
        //new JoystickButton(hazyController, Button.kBack.value).whenPressed(commandResetAllEncoders);
    }


    public void DPadWrapper() {
        // Lift //
        if(hazyController.getPOV() == 0) {} //Up
        else if(hazyController.getPOV() == 90){ //Right
            //System.out.println("dpad 90");
            //commandShootLow.execute();                                                       // DPad right --> tilt arm back to be on bar 3
        }
        else if(hazyController.getPOV() == 180){ //Down
            //commandBarThreePull.execute();                                                              // DPad down  --> pull arm to bar 3
        }
        else if(hazyController.getPOV() == 270){ //Left
            //sequenceBarThreeTiltExtend.execute();                                                       // DPad left  --> tilt arm to bar 3 and extend to bar 3
        }
    }
    
    //Sends autonomous command so that it can be used in robot.java
    public Command getAutonomousCommand() {
        //return chooser.getSelected();
        return twoBallAuton;
    }
}