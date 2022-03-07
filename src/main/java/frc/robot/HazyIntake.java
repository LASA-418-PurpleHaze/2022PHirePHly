package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HazyIntake extends SubsystemBase {
    private TalonSRX spinTalon;
    private TalonSRX upDownTalon;

    private boolean isUp;

    public HazyIntake () {
        spinTalon = new TalonSRX(RobotMap.INTAKESPINTALON);
        upDownTalon = new TalonSRX(RobotMap.INTAKEUPDOWNTALON);
        isUp = true;

        resetEncoderTicks();

        // sets the talon to not go at max speed (for simple movement)
        upDownTalon.configPeakOutputForward(0.5);
        upDownTalon.configPeakOutputReverse(0.5);

        // set up PID 
        upDownTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        upDownTalon.config_kP(0, RobotMap.INTAKEUPDOWNP, 30);
        upDownTalon.config_kI(0, RobotMap.INTAKEUPDOWNI, 30);
        upDownTalon.config_kD(0, RobotMap.INTAKEUPDOWND, 30);
        upDownTalon.config_kF(0, RobotMap.INTAKEUPDOWNFF, 30);
        
    }

    public void resetEncoderTicks() {
        spinTalon.setSelectedSensorPosition(0);
        upDownTalon.setSelectedSensorPosition(0);
    }

    public void drop() {
        System.out.println("Boo");
        upDownTalon.set(ControlMode.Position, -1200);
        System.out.println(upDownTalon.getSelectedSensorPosition());
    }

    public void raise() {
        upDownTalon.set(ControlMode.Position, RobotMap.INTAKEUPTICKS);
    }

    public void dropOrRaise () {
        System.out.println("Hello");
        if (isUp) {
            upDownTalon.set(ControlMode.Position, RobotMap.INTAKEDOWNTICKS);
            isUp = false;
        } else {
            upDownTalon.set(ControlMode.Position, RobotMap.INTAKEUPTICKS);
            isUp = true;
        }
    }

    public void spin () {
        spinTalon.set(ControlMode.PercentOutput, .5);
    }

    public void spit () {
        spinTalon.set(ControlMode.PercentOutput, -.5);
    }

    public void stopSpinning () {
        spinTalon.set(ControlMode.PercentOutput, 0);
    }
}
