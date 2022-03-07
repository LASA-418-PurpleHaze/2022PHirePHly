package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button;

//Holds all subsystems and commands for the robot and sets button bindings
public class RobotContainer {
    
    //Robot's controllers that drivers use
    private final XboxController hazyController = new XboxController(RobotMap.CONTROLLERPORT);
    private final Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICKPORT);
    private final Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICKPORT);
    
    //Subsystems and commands
    // Chassis //
    private final HazyMechBase hazyMechBase = new HazyMechBase();
    private final CommandMecanum commandMecanum = new CommandMecanum(hazyMechBase, leftJoystick, rightJoystick);

    // Shooter //
    private final HazyShooter hazyShooter = new HazyShooter();
    private final HazyLift hazyLift = new HazyLift();
    private final CommandShoot commandShoot = new CommandShoot(hazyShooter);
    private final CommandShooterDefault commandShooterDefault = new CommandShooterDefault(hazyShooter);
    private final CommandLift commandLift = new CommandLift(hazyLift);
    private final CommandTilt commandTilt = new CommandTilt(hazyLift);

    // Intake //
    private final HazyIntake hazyIntake = new HazyIntake();
    private final CommandRaiseDropIntake commandRaiseDropIntake = new CommandRaiseDropIntake(hazyIntake);
    private final CommandIntakeDefault commandIntakeDefault = new CommandIntakeDefault(hazyIntake);
    private final CommandSpinIntake commandSpinIntake = new CommandSpinIntake(hazyIntake);
    private final CommandSpitIntake commandSpitIntake = new CommandSpitIntake(hazyIntake);

    //Auto
    private final Command oneBallAuto = new CommandOneBallAuto(hazyMechBase, hazyShooter);
    private final Command twoBallAuto = new CommandTwoBallAuto(hazyMechBase, hazyShooter, hazyIntake);

    SendableChooser<Command> chooser = new SendableChooser<>();

    //This constructor is called once in Robotinit and should set up all button-> command bindings and default commands
    //Default commands are what commands run in on a subsystem when no other command is scheduled (called)
    public RobotContainer(){
        configureButtonBindings();
        hazyIntake.setDefaultCommand(commandIntakeDefault);
        hazyMechBase.setDefaultCommand(commandMecanum);
        hazyShooter.setDefaultCommand(commandShooterDefault);

        chooser.setDefaultOption("Two Ball Auto", twoBallAuto);
        chooser.addOption("One Ball Auto", oneBallAuto);

        Shuffleboard.getTab("Autonomous").add(chooser);
    }

    public Command getAutonomousCommand() {
        return chooser.getSelected();
    }

    //Use this method to define button->command mappings
    public void configureButtonBindings () {
        // Shooter //
        //new JoystickButton(hazyController, Button.kRightStick.value).toggleWhenPressed(commandShoot);
        new JoystickButton(hazyController, Button.kRightStick.value).whileHeld(commandShoot);

        // Intake //
        new JoystickButton(rightJoystick, 1).whileHeld(commandSpinIntake);                      
        new JoystickButton(leftJoystick, 1).whileHeld(commandSpitIntake);   
        
        // Lift //
        new JoystickButton(hazyController, Button.kA.value).whileHeld(commandTilt);
        new JoystickButton(hazyController, Button.kB.value).whileHeld(commandLift); 
    }

    public void DPadWrapper() {
        if (hazyController.getPOV() == 0) {
            //hazyIntake.drop();
        }
    }
}