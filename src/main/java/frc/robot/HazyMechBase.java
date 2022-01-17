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
        y = clamp(y, -1.0,1.0);
        x = clamp(x, -1.0,1.0);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[0] = x + y + angle;
        wheelSpeeds[1] = -x + y - angle;
        wheelSpeeds[2] = -x + y + angle;
        wheelSpeeds[3] = x + y - angle;
    
        normalize(wheelSpeeds);
    
        lFrontSpark.set(-wheelSpeeds[0] );
        rFrontSpark.set(-wheelSpeeds[1] * -1);
        lBackSpark.set(-wheelSpeeds[2]);
        rBackSpark.set(-wheelSpeeds[3]*-1);
    }
    
    private double clamp (double input, double low, double high){ //utility function for drive cartesian
        if(input > high)
            return high;
        else if(input < low)
            return low;
        return input;
    }

    protected void normalize(double[] wheelSpeeds) { //utility function for drive cartesian
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        
        for (int i = 1; i < wheelSpeeds.length; i++) {
          double temp = Math.abs(wheelSpeeds[i]);
          if (maxMagnitude < temp) {
            maxMagnitude = temp;
          }
        }
  
        if (maxMagnitude > 1.0) {
          for (int i = 0; i < wheelSpeeds.length; i++) {
          wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
          }
        }
      }
    
    @Override
    public void initDefaultCommand(){
        driveCartesian(0,0,0);
    }
}
