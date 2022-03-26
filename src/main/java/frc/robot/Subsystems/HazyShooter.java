package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//CTRE Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//REV Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//local imports
import frc.robot.*;

public class HazyShooter extends SubsystemBase{

    //Declaration of all motorcontrollers in subsystem 
    private TalonSRX shooterLeft;
    private TalonSRX shooterRight;
    private CANSparkMax highFeeder;

     //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyShooter(){
        shooterLeft = new TalonSRX(RobotMap.SHOOTERTALONLEFT);
        shooterRight = new TalonSRX(RobotMap.SHOOTERTALONRIGHT);
        highFeeder = new CANSparkMax(RobotMap.HIGHFEEDERSPARK, MotorType.kBrushed);

        shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        shooterLeft.config_kP(0, RobotMap.SHOOTERP);
        shooterLeft.config_kI(0, RobotMap.SHOOTERI);
        shooterLeft.config_kD(0, RobotMap.SHOOTERD);
        shooterLeft.config_kF(0, RobotMap.SHOOTERF);

        shooterRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        shooterRight.config_kP(0, RobotMap.SHOOTERP);
        shooterRight.config_kI(0, RobotMap.SHOOTERI);
        shooterRight.config_kD(0, RobotMap.SHOOTERD);
        shooterRight.config_kF(0, RobotMap.SHOOTERF);

        shooterLeft.configPeakOutputReverse(-1);
        shooterRight.configPeakOutputForward(1);

        shooterLeft.setSelectedSensorPosition(0);
        shooterRight.setSelectedSensorPosition(0);
    }

    //Spins the shooter up to a certain velocity
    public void shoot(){
        shooterLeft.set(ControlMode.Velocity, RobotMap.SHOOTERSPEED);
        shooterRight.follow(shooterLeft);
        PHrint.p(getShooterRPM());
        if (getShooterRPM() >= RobotMap.SHOOTERSPEED - 10)  {
            PHrint.p("high feed");
            highFeeder.set(RobotMap.HIGHFEEDERSPEED);
        }
    }

    public void shootSlow(){
        shooterLeft.set(ControlMode.Velocity, 7000);
        shooterRight.follow(shooterLeft);
        if(getShooterRPM() >= 7000 - 100) 
           highFeeder.set(RobotMap.HIGHFEEDERSPEED);
    }
    
    public void shootLow(){
        shooterLeft.set(ControlMode.Velocity, RobotMap.SHOOTERLOWSPEED);
        shooterRight.follow(shooterLeft);
        if(getShooterRPM() >= RobotMap.SHOOTERLOWSPEED - 100) 
           highFeeder.set(RobotMap.HIGHFEEDERSPEED);
    }

    public boolean didShoot(){
        // this is not right but i'm not sure how else to do this
        return false;
    }

    public boolean didShootSlow(){
        // this is not right but i'm not sure how else to do this
        return false;
    }

    public boolean didShootLow(){
        // this is not right but i'm not sure how else to do this
        return false;
    }

    //Default Command Calls this, stops the shooter motor
    public void stopShooter(){
        shooterLeft.set(ControlMode.PercentOutput,0);
        shooterRight.set(ControlMode.PercentOutput,0);
    }

    //Default Command Calls this, stops the feeder motor
    public void stopFeeder(){
        highFeeder.set(0);
    }

    //Testing Method: please delete later
    public void stupidShoot() {
        shooterLeft.set(ControlMode.PercentOutput,-.5);
    }

    //returns RPM of the shooter
    public double getShooterRPM(){
        return shooterLeft.getSelectedSensorVelocity();
    }

    //Moves the high feeder at a set speed, used for manually spinning the high feeder
    public void moveFeeder(){
        highFeeder.set(RobotMap.HIGHFEEDERSPEED);
    }
}