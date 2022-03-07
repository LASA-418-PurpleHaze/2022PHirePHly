package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//REV imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//local imports
import frc.robot.*;

public class HazyShooter extends SubsystemBase{
    //Declaration of all motorcontrollers in subsystem 
    private CANSparkMax shooterLeft;
    private CANSparkMax shooterRight;
    private CANSparkMax highFeeder;
    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder; //not used rn because the right motor is just following the output of the left motor
    private SparkMaxPIDController leftPID;
    //private RelativeEncoder shooterEncoder;
    //private TalonSRX highFeeder;

     //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyShooter(){
        //shooterRight = new CANSparkMax(RobotMap.SHOOTERSPARKRIGHT, MotorType.kBrushless);
        shooterLeft =  new CANSparkMax(RobotMap.SHOOTERSPARKLEFT, MotorType.kBrushed);
        shooterRight = new CANSparkMax(RobotMap.SHOOTERSPARKRIGHT, MotorType.kBrushed);
        leftEncoder = shooterLeft.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);
        leftPID = shooterLeft.getPIDController();
        leftPID.setFeedbackDevice(leftEncoder);
        leftPID.setP(RobotMap.SHOOTERP);
        leftPID.setI(RobotMap.SHOOTERI);
        leftPID.setD(RobotMap.SHOOTERD);
        leftPID.setFF(RobotMap.SHOOTERF);
        leftPID.setOutputRange(0, RobotMap.SHOOTERMAX);


        highFeeder = new CANSparkMax(RobotMap.HIGHFEEDERSPARK, MotorType.kBrushed);
    }

    //Spins the shooter up to a certain velocity
    public void shoot(){
        leftPID.setReference(RobotMap.SHOOTERSPEED, CANSparkMax.ControlType.kVelocity);
        shooterRight.follow(shooterLeft, true);
        if(getShooterRPM() >= RobotMap.SHOOTERSPEED - 100) 
           highFeeder.set(RobotMap.HIGHFEEDERSPEED);
    }

    //Default Command Calls this, stops the shooter motor
    public void stopShooter(){
        shooterLeft.set(0);
    }

    //Default Command Calls this, stops the feeder motor
    public void stopFeeder(){
        highFeeder.set(0);
    }

    //Testing Method: please delete later
    public void stupidShoot() {
        System.out.println("we stupid");
        shooterLeft.set(-.5);
    }

    //returns RPM of the shooter
    public double getShooterRPM(){
        return leftEncoder.getVelocity();
    }
}