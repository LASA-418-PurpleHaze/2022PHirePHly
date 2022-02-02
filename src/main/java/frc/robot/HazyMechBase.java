package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HazyMechBase extends SubsystemBase {
    
    //Declaration of all motorcontrollers in subsystem
    private CANSparkMax lFrontSpark;
    private CANSparkMax rFrontSpark;
    private CANSparkMax lBackSpark;
    private CANSparkMax rBackSpark;
    
    //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyMechBase(){
        lFrontSpark = new CANSparkMax(RobotMap.LEFTFRONTSPARK, MotorType.kBrushless);
        rFrontSpark = new CANSparkMax(RobotMap.RIGHTFRONTSPARK, MotorType.kBrushless);
        lBackSpark = new CANSparkMax(RobotMap.LEFTBACKSPARK, MotorType.kBrushless);
        rBackSpark = new CANSparkMax(RobotMap.RIGHTBACKSPARK, MotorType.kBrushless);
    }
    
    //Mecanum drive function that is called by the default
    public void driveCartesian(double x, double y, double angle){
        y = clamp(y, -1.0,1.0);
        x = clamp(x, -1.0,1.0);

        //The + and - are to make the mecanum drive move correctly & be able to move side to side
        double[] wheelSpeeds = new double[4];
        wheelSpeeds[0] = x + y + angle;
        wheelSpeeds[1] = -x + y - angle;
        wheelSpeeds[2] = -x + y + angle;
        wheelSpeeds[3] = x + y - angle;
    
        normalize(wheelSpeeds);
    
        lFrontSpark.set(-wheelSpeeds[0] );
        rFrontSpark.set(wheelSpeeds[1]);
        lBackSpark.set(-wheelSpeeds[2]);
        rBackSpark.set(wheelSpeeds[3]);
    }
    
    //Keeps input value between the high and low values
    private double clamp (double input, double low, double high){ //utility function for drive cartesian
        if (input > high)
            return high;
        else if (input < low)
            return low;
        return input;
    }
    
    //Sets a max speed value that the wheels can't exceed
    protected void normalize(double[] wheelSpeeds){ //utility function for drive cartesian
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
}