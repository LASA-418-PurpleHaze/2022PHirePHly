//Imports fot the Joystick and its functions
package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;

public class HazyController extends XboxController{
	public HazyController(int port) {
        super(port);
		// TODO Auto-generated constructor stub
	}

    public JoystickButton aButton = new JoystickButton(this, 1);
    public JoystickButton bButton = new JoystickButton(this, 2);
	public JoystickButton xButton = new JoystickButton(this, 3);
    public JoystickButton yButton = new JoystickButton(this, 4);
    public JoystickButton leftBumper = new JoystickButton(this, 5);
    public JoystickButton rightBumper = new JoystickButton(this, 6);
    public JoystickButton selectButton = new JoystickButton(this, 7);
	public JoystickButton startButton = new JoystickButton(this, 8);
	public JoystickButton leftStickButton = new JoystickButton(this, 9);
    public JoystickButton rightStickButton = new JoystickButton(this, 10);
    //public HazyTriggers triggers = new HazyTriggers(p);
    //public HazyJoysticks joysticks = new HazyJoysticks(p);
    //public HazyDPad dPad = new HazyDPad(p);

    public JoystickButton getXBut(){
        return xButton;
    }

    public JoystickButton getYBut(){
        return yButton;
    }

    public JoystickButton getABut(){
        return aButton;
    }

    public JoystickButton getBBut(){
        return bButton;
    }

    public JoystickButton getRightBump(){
        return rightBumper;
    }

    public JoystickButton getLeftBump(){
        return leftBumper;
    }

    public JoystickButton getStartBut(){
        return startButton;
    }

    public JoystickButton getSelectBut(){
        return selectButton;
    }

    public JoystickButton getLeftStickBut(){
        return leftStickButton;
    }

    public JoystickButton getRightStickBut(){
        return rightStickButton;
    }

    public boolean getLeftAxis(){
        return getLeftTriggerAxis() > .1;
    }

    public boolean getRightAxis(){
        return getRightTriggerAxis() > .1;
    }




}