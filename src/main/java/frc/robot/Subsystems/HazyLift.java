package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
    private RelativeEncoder liftRightEncoder;

    private SparkMaxPIDController tiltMotorPID;
    private SparkMaxPIDController liftMotorLeftPID;
    private SparkMaxPIDController liftMotorRightPID;

    double setpoint;

    public HazyLift() { //Initalizes all declared variables in the constructor
        tiltMotor = new CANSparkMax(RobotMap.TILTMOTORPORT, MotorType.kBrushed);
        liftMotorLeft = new CANSparkMax(RobotMap.LIFTMOTORPORTLEFT, MotorType.kBrushed);
        liftMotorRight = new CANSparkMax(RobotMap.LIFTMOTORPORTRIGHT, MotorType.kBrushed);

        tiltEncoder = tiltMotor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);
        liftLeftEncoder = liftMotorLeft.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);
        liftRightEncoder = liftMotorRight.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);
        liftRightEncoder.setInverted(true);
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
        liftMotorRightPID.setFeedbackDevice(liftRightEncoder);
        liftMotorRightPID.setP(RobotMap.LIFTP);
        liftMotorRightPID.setI(RobotMap.LIFTI);
        liftMotorRightPID.setD(RobotMap.LIFTD);
        liftMotorRightPID.setFF(RobotMap.LIFTF);

        resetEncoders();
        //tiltMotorPID.setReference(0, CANSparkMax.ControlType.kPosition);
    }

    //tilts the lift a certain number of encoder ticks
    public void tilt(double ticks) {
        tiltMotorPID.setReference(ticks, CANSparkMax.ControlType.kPosition);
        System.out.println(tiltEncoder.getPosition());
    }
    
    //makes the lift go up or down a certain number of ticks
    public void lift(double ticks) {
        liftMotorRightPID.setReference(ticks, CANSparkMax.ControlType.kPosition);
        liftMotorLeft.follow(liftMotorRight,true);
        // liftMotorRightPID.setReference(-setpoint, CANSparkMax.ControlType.kPosition); //Right motor must run opposite to the left motor because that's how our lift is set up mechanically
        System.out.println(liftRightEncoder.getPosition());
    }

    public void resetEncoders(){
        tiltEncoder.setPosition(0);
        liftLeftEncoder.setPosition(0);
        liftRightEncoder.setPosition(0);
    }

    //testing method, please remove later
    public void stupidLift(){
        liftMotorRight.set(0.75);
        liftMotorLeft.follow(liftMotorRight,true);
        //System.out.println("Left" + liftLeftEncoder.getPosition());
        System.out.println("Right" + liftRightEncoder.getPosition());
    }

    public void stupidTilt(){
        tiltMotor.set(-0.2);
        System.out.println("Hello");
    }

    public void stupidTiltBack(){
        tiltMotor.set(0.5);
        System.out.println("Hello");
    }

    public void stupidDown(){
        liftMotorRight.set(-0.75);
        liftMotorLeft.follow(liftMotorRight,true);
        System.out.println("Right" + liftRightEncoder.getPosition());
    }

    public void stupidDefault(){
        liftMotorLeft.set(0);
        liftMotorRight.set(0); 
        tiltMotor.set(0);
        //System.out.println(tiltEncoder.getPosition());
    }

    public void noTilt(){
        tiltMotorPID.setReference(0, CANSparkMax.ControlType.kPosition);
    }


}