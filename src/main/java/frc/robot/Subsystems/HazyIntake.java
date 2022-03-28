package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//CTRE imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//local imports
import frc.robot.*;

public class HazyIntake extends SubsystemBase {

    private TalonSRX spinTalon;
    private TalonSRX upDownTalon;
   
    public HazyIntake () {
        spinTalon = new TalonSRX(RobotMap.INTAKESPINTALON);
        upDownTalon = new TalonSRX(RobotMap.INTAKEUPDOWNTALON);
        
        upDownTalon.setNeutralMode(NeutralMode.Brake);
        resetEncoders();
       
        // upDownTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        // upDownTalon.config_kP(1, RobotMap.INTAKEDOWNP);
        // upDownTalon.config_kI(0, RobotMap.INTAKEDOWNI);
        // upDownTalon.config_kD(0, RobotMap.INTAKEDOWND);
        // upDownTalon.config_kF(0, RobotMap.INTAKEDOWNF);

        // upDownTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        // upDownTalon.config_kP(1, RobotMap.INTAKEUPP);
        // upDownTalon.config_kI(1, RobotMap.INTAKEUPI);
        // upDownTalon.config_kD(1, RobotMap.INTAKEUPD);
        // upDownTalon.config_kF(1, RobotMap.INTAKEUPF);
        
        spinTalon.configPeakOutputReverse(-1);
        spinTalon.configPeakOutputForward(1);
        upDownTalon.configPeakOutputForward(1);
        upDownTalon.configPeakOutputReverse(-1);
        upDownTalon.selectProfileSlot(0, 0); //Sets the motor to use the "0" id where we have stored our PID values and makes usre its running in primary closed loop mode: each motor can have multiple PID slots each with its own id
    }

    public void defaultC () {
        //upDownTalon.set(ControlMode.PercentOutput, 0);
        // PHrint.p();
       spinTalon.set(ControlMode.PercentOutput, 0);
    }


    public void drop () {
        //print.p(upDownTalon.getSelectedSensorPosition());
        if (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKEDROPPOSITION) { 
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKEDROPSPEED);
        }
        else {
            //intakeStop();
            upDownTalon.set(ControlMode.PercentOutput, 0);
        }
    }
    // public void intakeStop() {
    //     upDownTalon.set(ControlMode.PercentOutput, 0);
    //     resetEncoders();
    // }

    public void raise() {
        if (upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKERAISEPOSITION) {
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKERAISESPEED);
        }
        else {
            upDownTalon.set(ControlMode.PercentOutput, 0);
        }
    }

    public boolean didDrop () {
        return (upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKEDROPPOSITION);
    }

    public boolean didRaise () {
        return (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKERAISEPOSITION);
    }

    public void resetEncoders() {
        upDownTalon.setSelectedSensorPosition(0);
    }
    
    public void spin () {
        spinTalon.set(ControlMode.PercentOutput, 1);
    }

    public void spit () {
        spinTalon.set(ControlMode.PercentOutput, -1);
    }
}
