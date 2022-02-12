package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HazyShooter extends SubsystemBase{
    //Declaration of all motorcontrollers in subsystem 
    private CANSparkMax shooter;
    private SparkMaxPIDController shooterPID;
    private RelativeEncoder shooterEncoder;
    private TalonSRX highFeeder;

     //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyShooter(){
        shooter = new CANSparkMax(RobotMap.SHOOTERSPARK, MotorType.kBrushless);
        shooterPID = shooter.getPIDController();
        shooterPID.setP(RobotMap.SHOOTERP);
        shooterPID.setI(RobotMap.SHOOTERI);
        shooterPID.setD(RobotMap.SHOOTERD);
        shooterPID.setFF(RobotMap.SHOOTERFF);
        shooterEncoder = shooter.getEncoder();

        highFeeder = new TalonSRX(RobotMap.HIGHFEEDERTALON);
    }

    //Spins the shooter up to a certain velocity
    public void shoot(){
        shooterPID.setReference(RobotMap.SHOOTERSPEED, CANSparkMax.ControlType.kVelocity);
        if(getShooterRPM() >= RobotMap.SHOOTERSPEED - 100)
            highFeeder.set(ControlMode.PercentOutput, 1);
    }

    //Default Command Calls this, stops the shooter motor
    public void stopShooter(){
        shooter.set(0);
    }

    //Default Command Calls this, stops the feeder motor
    public void stopFeeder(){
        highFeeder.set(ControlMode.PercentOutput, 0);
    }

    //returns RPM of the shooter
    public double getShooterRPM(){
        return shooterEncoder.getVelocity();
    }
}
