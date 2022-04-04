package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * This class allows you to trigger commands from an analog input on
 * a joystick (sich as the triggers - Axis 3). 
 * 
 * 
 * The following example code placed in OI class turns axis 3 into two buttons:
 * ----------------------------------------------------------------------------
 * //Create an AnalogButton for each trigger
 * int joystickChannel = 1;
 * public JoystickAnalogButton TriggerR = new JoystickAnalogButton(joystickChannel, 3, -0.5),
 *                             TriggerL = new JoystickAnalogButton(joystickChannel, 3, 0.5)
 * 
 * //Link the buttons to commands
 * TriggerR.whenPressed(new ExampleCommand1());
 * TriggerL.whenPressed(new ExampleCommand2())
 * 
 * Note that since both buttons are on the same Axis channel, they cannot be
 * pressed simultaneously. One trigger will negate the other and neither will
 * look pressed. So plan your controls accordingly.
 * 
 * @author James@team2168.org
 *
 */
public class HazyDPad extends Button {

    XboxController controller;
    int direction;

    public final static int RIGHT = 90;
    public final static int UP = 0;


    /**
     * Create a button for triggering commands off a joystick's analog axis
     * 
     * @param controller The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
     * @param axis The axis number
     */
    public HazyDPad(XboxController controller, int direction) {
        this.controller = controller;
        this.direction = direction;
    }    

    public boolean get() {
        return controller.getPOV() == direction;    //Return true if axis value is greater than positive threshold
    }
}