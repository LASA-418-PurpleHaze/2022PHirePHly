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

        tiltMotor.restoreFactoryDefaults();
        liftMotorLeft.restoreFactoryDefaults();
        liftMotorRight.restoreFactoryDefaults();


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

        liftMotorLeftPID.setOutputRange(-1, 1);
        liftMotorRightPID.setOutputRange(-1, 1);
        resetEncoders();
        //tiltMotorPID.setReference(0, CANSparkMax.ControlType.kPosition);
    }

    //tilts the lift a certain number of encoder ticks
    public void tilt(double ticks) {
        System.out.println("tilt" + tiltEncoder.getPosition());
        tiltMotorPID.setReference(ticks, CANSparkMax.ControlType.kPosition);
    }
    
    //makes the lift go up or down a certain number of ticks
    public void lift(double ticks) {
        //liftMotorRightPID.setReference(-3.7, CANSparkMax.ControlType.kPosition);
        //liftMotorLeft.follow(liftMotorRight,true);
        // liftMotorRightPID.setReference(-setpoint, CANSparkMax.ControlType.kPosition); //Right motor must run opposite to the left motor because that's how our lift is set up mechanically
        //System.out.println("lift" + liftRightEncoder.getPosition());
    }
    public void lifttilt(double liftTicks, double tiltTicks) {
        //lift(liftTicks);
        //tilt(tiltTicks);
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
        if(liftRightEncoder.getPosition() <= RobotMap.MAXLIFTHEIGHT){ //Going up is negative encoder ticks so we do <= instead of >=
            liftMotorRight.set(0); 
        }

        System.out.println("LIFT MOVING DOWNup");

    }

    public void stupidDown(){
        liftMotorRight.set(-0.5);
        liftMotorLeft.follow(liftMotorRight,true);
        System.out.println("Right" + liftRightEncoder.getPosition());
         if(liftRightEncoder.getPosition() >= RobotMap.MINLIFTHEIGHT){
             liftMotorRight.set(0);
         }

        System.out.println("LIFT MOVING DOWN");
    }
    // public void reallyStupidTilt(double ticks) {
    //     if (tiltEncoder.getPosition() > .12) {
    //         tiltMotorPID.setReference(ticks, CANSparkMax.ControlType.kPosition);
    //     }
        

    // }

    public void stupidTilt(){
        // tiltMotor.set(-0.3);
        System.out.println("tilt" + tiltEncoder.getPosition());

        //System.out.println("Hello");
        //tilt(0.093);
    }

    public void stupidTiltBack(){
        tiltMotor.set(0.3);
        System.out.println("Hello");
    }

    

    public void stupidDefault(){
        liftMotorLeft.set(0);
        liftMotorRight.set(0); 
        tiltMotor.set(0);
        System.out.println(liftRightEncoder.getPosition());
    }
    public void fakeEStop() {
        liftMotorLeft.set(0);
        liftMotorRight.set(0); 
        tiltMotor.set(0);
    }

    public void noTilt(){
        tiltMotorPID.setReference(0, CANSparkMax.ControlType.kPosition);
    }


}