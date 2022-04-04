package frc.robot.Subsystems; //folder the file is in

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import javax.swing.text.StyleContext.SmallAttributeSet;

//REV imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.RelativeEncoder;

//local imports
import frc.robot.*;

public class HazyLift extends SubsystemBase  {

    //Declaration of all motor controllers, their associated PID objects, and encoders used in the subsystem
    private CANSparkMax tiltMotor;
    private CANSparkMax liftMotorLeft;
    private CANSparkMax liftMotorRight;

    private RelativeEncoder tiltEncoder;
    private RelativeEncoder liftLeftEncoder;

    private SparkMaxPIDController tiltMotorPID;
    private SparkMaxPIDController liftMotorLeftPID;
    private SparkMaxPIDController liftMotorRightPID;

    private boolean stopEnabled;

    double setpoint;

    public HazyLift() { //Initalizes all declared variables in the constructor
        tiltMotor = new CANSparkMax(RobotMap.TILTMOTORPORT, MotorType.kBrushed);
        liftMotorLeft = new CANSparkMax(RobotMap.LIFTMOTORPORTLEFT, MotorType.kBrushed);
        liftMotorRight = new CANSparkMax(RobotMap.LIFTMOTORPORTRIGHT, MotorType.kBrushed);

        tiltMotor.restoreFactoryDefaults();
        liftMotorLeft.restoreFactoryDefaults();
        liftMotorRight.restoreFactoryDefaults();

        stopEnabled = true;

        tiltEncoder = tiltMotor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, RobotMap.HALENCONDERTICKSPERROTATION);
        liftLeftEncoder = liftMotorLeft.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, RobotMap.HALENCONDERTICKSPERROTATION);
        liftLeftEncoder.setInverted(true);
        tiltMotor.setInverted(true);

        tiltMotorPID = tiltMotor.getPIDController();
        tiltMotorPID.setFeedbackDevice(tiltEncoder);
        tiltMotorPID.setP(RobotMap.TILTP);
        tiltMotorPID.setI(RobotMap.TILTI);
        tiltMotorPID.setD(RobotMap.TILTD);
        tiltMotorPID.setFF(RobotMap.TILTF);

        liftMotorLeftPID = liftMotorLeft.getPIDController();
        liftMotorLeftPID.setFeedbackDevice(liftLeftEncoder);
        liftMotorLeftPID.setP(RobotMap.LIFTP);
        liftMotorLeftPID.setI(RobotMap.LIFTI);
        liftMotorLeftPID.setD(RobotMap.LIFTD);
        liftMotorLeftPID.setFF(RobotMap.LIFTF);

        liftMotorRightPID = liftMotorRight.getPIDController();
        liftMotorRightPID.setP(RobotMap.LIFTP);
        liftMotorRightPID.setI(RobotMap.LIFTI);
        liftMotorRightPID.setD(RobotMap.LIFTD);
        liftMotorRightPID.setFF(RobotMap.LIFTF);

        liftMotorLeftPID.setOutputRange(-1, 1);
        liftMotorRightPID.setOutputRange(-1, 1);
        
        resetEncoders();
    }

    //tilts the lift a certain number of encoder ticks
    public void tilt(double ticks) {
        tiltMotorPID.setReference(ticks, CANSparkMax.ControlType.kPosition);
    }
    
    //makes the lift go up or down a certain number of ticks
    public void lift(double ticks) {
        // liftMotorRightPID.setReference(-3.7, CANSparkMax.ControlType.kPosition);
        // liftMotorLeft.follow(liftMotorRight,true);
        // liftMotorRightPID.setReference(-setpoint, CANSparkMax.ControlType.kPosition); //Right motor must run opposite to the left motor because that's how our lift is set up mechanically
        // //print.p(liftRightEncoder.getPosition());
    }

    public void resetEncoders(){
        tiltEncoder.setPosition(0);
        liftLeftEncoder.setPosition(0);
        //liftRightEncoder.setPosition(0);
        //liftLeftEncoder.setInverted(true);
    }

    public void stopsOn(){
        stopEnabled = true;
    }

    public void stopsOff(){
        stopEnabled = false;
    }

    public void stupidLift(){
        liftMotorLeft.set(-1);
        liftMotorRight.follow(liftMotorLeft,true);
        PHrint.p(liftLeftEncoder.getPosition());
        if(stopEnabled && (liftLeftEncoder.getPosition() >= RobotMap.MAXMAXLIFTHEIGHT)){ //Going up is negative encoder ticks so we do <= instead of >=
            liftMotorLeft.set(0); 
        }
    }

    public void stupidDown(){
        liftMotorLeft.set(1);
        liftMotorRight.follow(liftMotorLeft,true);
        PHrint.p(liftLeftEncoder.getPosition());
        if(stopEnabled && (liftLeftEncoder.getPosition() <= RobotMap.MINLIFTHEIGHT)){
            liftMotorLeft.set(0);
        }
    }

    public void stupidTiltOut(){
        tiltMotor.set(-0.5);
        if(stopEnabled && (tiltEncoder.getPosition() >= RobotMap.MAXTILT)){
           tiltMotor.set(0);
        }
        PHrint.p(tiltEncoder.getPosition());
        
    }

    public void stupidTiltIn(){
        tiltMotor.set(0.5);
        if(stopEnabled && (tiltEncoder.getPosition() <= RobotMap.MINTILT)){
           tiltMotor.set(0);
           PHrint.p("Stopping" + tiltEncoder.getPosition());

        }
    }

    public void stupidTiltOutLiftUp () {
        tiltMotor.set(-0.5);
        if(stopEnabled && (tiltEncoder.getPosition() >= RobotMap.MAXTILT)){
           tiltMotor.set(0);
        }
        
        // liftMotorLeft.set(-1);
        // liftMotorRight.follow(liftMotorLeft, true);
        // if(stopEnabled && (liftLeftEncoder.getPosition() >= RobotMap.MAXMAXLIFTHEIGHT)){ //Going up is negative encoder ticks so we do <= instead of >=
        //     liftMotorLeft.set(0); 
        // }
        stupidLift();
    }

    public void stupidTiltInLiftDown () {
        tiltMotor.set(0.5);
        PHrint.p("Tilt: " + tiltEncoder.getPosition() + " Winch: " + liftLeftEncoder.getPosition());
        if(stopEnabled && (tiltEncoder.getPosition() <= RobotMap.MINTILT)){
            tiltMotor.set(0);
        }
    
        // liftMotorLeft.set(1);
        // liftMotorRight.follow(liftMotorLeft, true);
        // if(stopEnabled && (liftLeftEncoder.getPosition() <= RobotMap.MINLIFTHEIGHT)){
        //     liftMotorLeft.set(0);
        // }
        stupidDown();
    }

    public void stupidDefault(){
        liftMotorLeft.set(0);
        liftMotorRight.set(0); 
        tiltMotor.set(0);
    }

    public void fakeEStop() {
        liftMotorLeft.set(0);
        liftMotorRight.set(0); 
        tiltMotor.set(0);
    }

    public void putData () {
        SmartDashboard.putNumber("Lift Encoder", liftLeftEncoder.getPosition());
        SmartDashboard.putNumber("Tilt Encoder", tiltEncoder.getPosition());
        SmartDashboard.putNumber("Lift Left Amps", liftMotorLeft.getOutputCurrent());
        SmartDashboard.putNumber("Lift Right Amps", liftMotorRight.getOutputCurrent());
        SmartDashboard.putNumber("Tilt Amps", tiltMotor.getOutputCurrent());
        SmartDashboard.putBoolean("Climb stops enabled", stopEnabled);
    }
}