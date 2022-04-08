package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        
        //upDownTalon.setNeutralMode(NeutralMode.Brake);
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
        PHrint.p(upDownTalon.getSelectedSensorPosition());
       spinTalon.set(ControlMode.PercentOutput, 0);
    }


    public void drop () {
        PHrint.p("controller works");
        if (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKEDROPPOSITION && Math.abs(upDownTalon.getStatorCurrent()) < 30 ) { 
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKEDROPSPEED);
        }
        else {
            //intakeStop();
            upDownTalon.set(ControlMode.PercentOutput, 0);
        }
    }
    public void intakeStop() {
        upDownTalon.set(ControlMode.PercentOutput, 0);
        //resetEncoders();
    }

    public void raise() {
        if (upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKERAISEPOSITION) {
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKERAISESPEED);
            
        }
        else {
            upDownTalon.set(ControlMode.PercentOutput, 0);
            PHrint.p("else 2");
        }
    }

    public void timedRaise(long startTime) {
        if (java.lang.System.currentTimeMillis()< startTime + 450) {
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKERAISESPEED);
        } else {
            upDownTalon.set(ControlMode.PercentOutput, 0);
        }
    }

    public void timedDrop(long startTime) {
        if (java.lang.System.currentTimeMillis()< startTime + 400) {
            PHrint.p("if");
            upDownTalon.set(ControlMode.PercentOutput, RobotMap.INTAKEDROPSPEED);
        } else {
            upDownTalon.set(ControlMode.PercentOutput, 0);
            PHrint.p("else");
        }
    }

    public void halfRaise() {
        PHrint.p("HALF RAISE IS RUNNING!");
        // PHrint.p(upDownTalon.getSelectedSensorPosition());
        if (upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKEHALFRAISEPOSITION) {
            //PHrint.p("if");
            upDownTalon.set(ControlMode.PercentOutput, -.5);
         }
        // else if (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKEHALFRAISEPOSITION &&  upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKERAISEPOSITION) {
        //     PHrint.p("else if");
        //     upDownTalon.set(ControlMode.PercentOutput, -.2);
        // }
        // else if (upDownTalon.getSelectedSensorPosition() < 0 &&  upDownTalon.getSelectedSensorPosition() > -400) {
        //     PHrint.p("else if2");
        //     upDownTalon.set(ControlMode.PercentOutput, .45);
        // }

        // else {
        //     PHrint.p("else if");
        //     upDownTalon.set(ControlMode.PercentOutput, 0);
        // }
    }

    public boolean didDrop () {
        return (upDownTalon.getSelectedSensorPosition() < RobotMap.INTAKEDROPPOSITION);
    }

    public boolean didRaise () {
        return (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKERAISEPOSITION);
    }
    public boolean didHalfRaise () {
        return (upDownTalon.getSelectedSensorPosition() > RobotMap.INTAKEHALFRAISEPOSITION);
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

    public void slowSpit() {
        spinTalon.set(ControlMode.PercentOutput, -.8);
    }

    public void putData () {
        SmartDashboard.putNumber("Intake Up/Down Encoder", upDownTalon.getSelectedSensorPosition());
        // SmartDashboard.putNumber("Intake Spin Speed", spinTalon.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Intake Up/Down Amps", upDownTalon.getStatorCurrent());
        SmartDashboard.putNumber("Intake Spin Amps", spinTalon.getStatorCurrent());
    } 
}
