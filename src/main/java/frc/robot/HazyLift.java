package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HazyLift extends SubsystemBase  {
    private CANSparkMax tiltMotor;
    private CANSparkMax liftMotor;


    public HazyLift() {
        tiltMotor = new CANSparkMax(RobotMap.TILTMOTORPORT, MotorType.kBrushed);
        liftMotor = new CANSparkMax(RobotMap.LIFTMOTORPORT, MotorType.kBrushed);

      
    }

    public void stupidTilt() {
        tiltMotor.set(.5);
    }
    
    public void stupidLift() {
        liftMotor.set(.5);
    }
}