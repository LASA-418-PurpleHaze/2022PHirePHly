package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HazyMechBase extends Subsystem{
    private CANSparkMax lFrontSpark;
    private CANSparkMax rFrontSpark;
    private CANSparkMax lBackSpark;
    private CANSparkMax rBackSpark;

    public HazyMechBase(){
        lFrontSpark = new CANSparkMax(1, MotorType.kBrushless);
        rFrontSpark = new CANSparkMax(2, MotorType.kBrushless);
        lBackSpark = new CANSparkMax(3, MotorType.kBrushless);
        rBackSpark = new CANSparkMax(4, MotorType.kBrushless);
    }


    public void driveCartesian(double x, double y, double angle){
        y = MathUtil.clamp(y, -1.0,1.0);
        x = MathUtil.clamp(x, -1.0,1.0);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[0] = x + y + angle;
        wheelSpeeds[1] = -x + y - angle;
        wheelSpeeds[2] = -x + y + angle;
        wheelSpeeds[3] = x + y - angle;
    
        normalize(wheelSpeeds);
    
        lFrontSpark.set(ControlMode.PercentOutput, -wheelSpeeds[0] );
        rFrontSpark.set(ControlMode.PercentOutput, -wheelSpeeds[1] * -1);
        lBackSpark.set(ControlMode.PercentOutput, -wheelSpeeds[2]);
        rBackSpark.set(ControlMode.PercentOutput, -wheelSpeeds[3]*-1);
    }
    
    @Override
    public void initDefaultCommand(){
        driveCartesian(0,0,0);
    }
}
