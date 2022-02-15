package frc.robot;

import edu.wpi.first.wpilibj2.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class HazyMechBase implements Subsystem{
    private CANSparkMax lFrontSpark;
    private CANSparkMax rFrontSpark;
    private CANSparkMax lBackSpark;
    private CANSparkMax rBackSpark;

    private RelativeEncoder lFrontEncoder;
    private RelativeEncoder rFrontEncoder;
    private RelativeEncoder lBackEncoder;
    private RelativeEncoder rBackEncoder;

    private SparkMaxPIDController lFront_pidController;
    private SparkMaxPIDController rFront_pidController;
    private SparkMaxPIDController lBack_pidController;
    private SparkMaxPIDController rBack_pidController;

    private boolean turnDelay;
    private double distance;
    private double offset;
    private double milStart;
    private boolean delayed;

    private double tx;
    private double ty;
    private double ta;
    private boolean tv;

    private double angleofTarget;

    private Solenoid solenoidToLight;

    public HazyMechBase(){
        lFrontSpark = new CANSparkMax(RobotMap.LEFTFRONTSPARK, MotorType.kBrushless);
        rFrontSpark = new CANSparkMax(RobotMap.RIGHTFRONTSPARK, MotorType.kBrushless);
        lBackSpark = new CANSparkMax(RobotMap.LEFTBACKSPARK, MotorType.kBrushless);
        rBackSpark = new CANSparkMax(RobotMap.RIGHTBACKSPARK, MotorType.kBrushless);
        
        lFrontEncoder = lFrontSpark.getEncoder();
        lFrontEncoder.setInverted(false);
        lFront_pidController.setP(RobotMap.DRIVEP);
        lFront_pidController.setI(RobotMap.DRIVEI);
        lFront_pidController.setD(RobotMap.DRIVED);

        rFrontEncoder = rFrontSpark.getEncoder();
        rFrontEncoder.setInverted(false);
        rFront_pidController.setP(RobotMap.DRIVEP);
        rFront_pidController.setI(RobotMap.DRIVEI);
        rFront_pidController.setD(RobotMap.DRIVED);

        lBackEncoder = lBackSpark.getEncoder();
        lBackEncoder.setInverted(false);
        lBack_pidController.setP(RobotMap.DRIVEP);
        lBack_pidController.setI(RobotMap.DRIVEI);
        lBack_pidController.setD(RobotMap.DRIVED);

        rBackEncoder = rBackSpark.getEncoder();
        rBackEncoder.setInverted(false);
        rBack_pidController.setP(RobotMap.DRIVEP);
        rBack_pidController.setI(RobotMap.DRIVEI);
        rBack_pidController.setD(RobotMap.DRIVED);
    }

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
    
        lFrontSpark.set(-wheelSpeeds[0]);
        rFrontSpark.set(wheelSpeeds[1]);
        lBackSpark.set(-wheelSpeeds[2]);
        rBackSpark.set(wheelSpeeds[3]);
    }
    
    private double clamp (double input, double low, double high){ //utility function for drive cartesian
        if (input > high)
            return high;
        else if (input < low)
            return low;
        return input;
    }

    private double clamp(double input){
        if (input > RobotMap.MAXVISIONSPEED)
            return RobotMap.MAXVISIONSPEED; 
        else if (input < -RobotMap.MAXVISIONSPEED)
            return -RobotMap.MAXVISIONSPEED;
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

    public void updateData() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tv = table.getEntry("tv").getDouble(0)==1.0 ? true : false;
        tx = table.getEntry("tx").getDouble(0);
        ty = table.getEntry("ty").getDouble(0);
        ta = table.getEntry("ta").getDouble(0);
        offset = tx;
    }

    public void getTargetDistance() {
        if (!tv) {
            distance = 0;
        }
        angleofTarget =  ty;
        distance = (RobotMap.HEIGHTOFTARGET - RobotMap.HEIGHTOFCAMERA) / Math.tan(Math.toRadians(RobotMap.ANGLEOFCAMERA + angleofTarget));
    }
      
    public void turnToTarget() {
        solenoidToLight.set(true);
        
        if (delayed) {
            milStart = java.lang.System.currentTimeMillis();
            delayed = false;
        }
        if (java.lang.System.currentTimeMillis() > milStart + RobotMap.VISIONDELAY) {
            double turnPower = RobotMap.VISIONVELTURN * (offset-RobotMap.RIGHTSIDEOFFSET);
            rFront_pidController.setReference(turnPower, ControlType.kVelocity);
            lFront_pidController.setReference(turnPower, ControlType.kVelocity);
            rBack_pidController.setReference(turnPower, ControlType.kVelocity);
            lBack_pidController.setReference(turnPower, ControlType.kVelocity); 
        }
    }

    public void goToTarget(){
        solenoidToLight.set(true);
        
        if (delayed) {
            milStart = java.lang.System.currentTimeMillis();
            delayed = false;
        }

        if (java.lang.System.currentTimeMillis() > milStart + RobotMap.VISIONDELAY) {
            double travelDistance;
            if (distance == -1.0)
                travelDistance = 0.0;
            else 
                travelDistance = RobotMap.SHOOTDISTANCE - distance;

            //System.out.println(java.lang.System.currentTimeMillis()-lastData);
            double turnPower = clamp(RobotMap.VISIONTURN * offset);
            if (turnPower > -0.105 && turnPower < 0.0 && Math.abs(offset) >= 10.0)
                turnPower = -0.105;
            else if (turnPower < 0.105 && turnPower > 0.0 && Math.abs(offset) >= 10.0)
                turnPower = 0.105;
            
            if (Math.abs(offset) < 10.0)
                turnPower = 0.0;
            
            double forwardPower = clamp(-travelDistance * RobotMap.VISIONSPEED);
            System.out.println("turn: " + turnPower + " forward: " + forwardPower);
            driveCartesian(0, -forwardPower, -turnPower); 
        }
    }

    public void toggleDelayed(){
        delayed = true;
    }
    
    public void toggleTurnDelay(){
        turnDelay = true;
    }

    public void initDefaultCommand(){
        driveCartesian(0,0,0);
    }
}