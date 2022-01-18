package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    
    //Declare and initialize all input devices
    public static XboxController controller = new XboxController(RobotMap.CONTROLLERPORT);
    public static Joystick leftJoystick = new Joystick(RobotMap.LEFTJOYSTICKPORT);
    public static Joystick rightJoystick = new Joystick(RobotMap.RIGHTJOYSTICKPORT);


    public OI(){
        //put all whileHeld, whenPressed, or other button-command bindings here
        

    }

    public void runMethods(){

    }
}
