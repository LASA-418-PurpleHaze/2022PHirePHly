package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class HazyMechBase extends SubsystemBase {
    
    //Declaration of all motorcontrollers in subsystem
    private CANSparkMax lFrontSpark;
    private CANSparkMax rFrontSpark;
    private CANSparkMax lBackSpark;
    private CANSparkMax rBackSpark;

    //Declaration of PID controllers for the motorcontrollers
    private SparkMaxPIDController lFrontSparkPID;
    private SparkMaxPIDController rFrontSparkPID;
    private SparkMaxPIDController lBackSparkPID;
    private SparkMaxPIDController rBackSparkPID;
    
    //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyMechBase(){
        lFrontSpark = new CANSparkMax(RobotMap.LEFTFRONTSPARK, MotorType.kBrushless);
        rFrontSpark = new CANSparkMax(RobotMap.RIGHTFRONTSPARK, MotorType.kBrushless);
        lBackSpark = new CANSparkMax(RobotMap.LEFTBACKSPARK, MotorType.kBrushless);
        rBackSpark = new CANSparkMax(RobotMap.RIGHTBACKSPARK, MotorType.kBrushless);

        //PID setup
        /*
        lFrontSparkPID = lFrontSpark.getPIDController();
        lFrontSparkPID.setP(RobotMap.CHASSISLEFTFRONTP);
        lFrontSparkPID.setI(RobotMap.CHASSISLEFTFRONTI);
        lFrontSparkPID.setD(RobotMap.CHASSISLEFTFRONTD);
        lFrontSparkPID.setFF(RobotMap.CHASSISLEFTFRONTF);

        rFrontSparkPID = rFrontSpark.getPIDController();
        rFrontSparkPID.setP(RobotMap.CHASSISBACKFRONTP);
        rFrontSparkPID.setI(RobotMap.CHASSISBACKFRONTI);
        rFrontSparkPID.setD(RobotMap.CHASSISBACKFRONTD);
        rFrontSparkPID.setFF(RobotMap.CHASSISBACKFRONTF);

        lBackSparkPID = lBackSpark.getPIDController();
        lBackSparkPID.setP(RobotMap.CHASSISLEFTBACKP);
        lBackSparkPID.setI(RobotMap.CHASSISLEFTBACKI);
        lBackSparkPID.setD(RobotMap.CHASSISLEFTBACKD);
        lBackSparkPID.setFF(RobotMap.CHASSISLEFTBACKF);

        rBackSparkPID = rBackSpark.getPIDController();
        rBackSparkPID.setP(RobotMap.CHASSISRIGHTBACKP);
        rBackSparkPID.setI(RobotMap.CHASSISRIGHTBACKI);
        rBackSparkPID.setD(RobotMap.CHASSISRIGHTBACKD);
        rBackSparkPID.setFF(RobotMap.CHASSISRIGHTBACKF);
        */
    }
    
    //Mecanum drive function that is called by the default
    public void driveCartesian(double x, double y, double angle){
        x = clamp(x, -1.0,1.0);
        x = applyDeadband(x, RobotMap.DEADBAND);
        y = clamp(y, -1.0,1.0);
        y = applyDeadband(y, RobotMap.DEADBAND);

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

    private double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) 
            return (value - deadband) / (1.0 - deadband);
          else 
            return (value + deadband) / (1.0 - deadband);  
        } 
        else 
          return 0.0;
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