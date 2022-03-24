package frc.robot.Subsystems; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

//REV imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//local imports
import frc.robot.*;

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

    //Declaration of variables used by vision
    private NetworkTableInstance limeTable; //Not sure why we're using a serial port instead of a network table to get Limelight data. Consider changing later
    
    private boolean delayed;
    private boolean turnDelay;
    private double milStart;

    private double tv;
    private double offset;
    private double ty;
    private double ta;

    private final double targetHeight = 104.0;
    private final double limeHeight = 36.0;
    private final double limeAngle = 7.5;
    private double distance;

    //Constructor includes PID value setup for motorcontrollers and initialization of all motors in subsystem
    public HazyMechBase(){
        lFrontSpark = new CANSparkMax(RobotMap.LEFTFRONTSPARK, MotorType.kBrushless);
        rFrontSpark = new CANSparkMax(RobotMap.RIGHTFRONTSPARK, MotorType.kBrushless);
        lBackSpark = new CANSparkMax(RobotMap.LEFTBACKSPARK, MotorType.kBrushless);
        rBackSpark = new CANSparkMax(RobotMap.RIGHTBACKSPARK, MotorType.kBrushless);

        lFrontSpark.restoreFactoryDefaults();
        rFrontSpark.restoreFactoryDefaults();
        lBackSpark.restoreFactoryDefaults();
        rBackSpark.restoreFactoryDefaults();

        lFrontSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
        lBackSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rFrontSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rBackSpark.setIdleMode(CANSparkMax.IdleMode.kBrake);

        //PID setup (we may be able to change this where one motor on each side is a leader an the other one follows it)
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

        //solenoidToLight = new Solenoid(PneumaticsModuleType.REVPH,5);
        //visionPort = new SerialPort(RobotMap.BAUDRATE, SerialPort.Port.kMXP);  
        lFrontSparkPID.setOutputRange(-.35, .35);
        lBackSparkPID.setOutputRange(-.35, .35);
        rFrontSparkPID.setOutputRange(-.35, .35);
        rBackSparkPID.setOutputRange(-.35, .35);

        resetEncoders();
    }

    public CANSparkMax getLFrontSparkMax(){
        return lFrontSpark;
    }
    public CANSparkMax getRFrontSparkMax(){
        return rFrontSpark;
    }
    public CANSparkMax getLBackSparkMax(){
        return lBackSpark;
    }
    public CANSparkMax getRBackSparkMax(){
        return rBackSpark;
    }
    
    public void resetEncoders(){
        lFrontSpark.getEncoder().setPosition(0);
        lBackSpark.getEncoder().setPosition(0);
        rFrontSpark.getEncoder().setPosition(0);
        rBackSpark.getEncoder().setPosition(0);
    }



    // Joystick Driving Functions //
    
    //Mecanum drive function that is called by the default
    public void driveCartesian (double x, double y, double angle) {
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

    

    // Vision Functions //

    //Turns the robot to face the target and drives it to the correct shooting distance
    public void goToTarget () {
        //solenoidToLight.set(true);
        
        //Sets up a delay of length RobotMap.VISIONDELAY between the time the button is pressed and the robot starts following vision 
        if (delayed) {
            milStart = java.lang.System.currentTimeMillis();
            delayed = false;
        }

        //Basically a primitive, custom coded, primary and auxiliary closed loop (PID) control. 
        //The primary loop is moving the robot to the shooting distance while the auxiliary loop is turning the robot to face the target
        if (java.lang.System.currentTimeMillis() > milStart + RobotMap.VISIONDELAY) {
            double travelDistance;
            if (distance == -1.0)
                travelDistance = 0.0;
            else
                travelDistance = RobotMap.SHOOTDISTANCE - distance;
            // System.out.println(java.lang.System.currentTimeMillis()-lastData);
            // double turnPower = clamp(RobotMap.VISIONTURN * (offset/22), RobotMap.MAXVISIONSPEED, -RobotMap.MAXVISIONSPEED);
            double turnPower = RobotMap.VISIONTURN * (offset/22);
            if (turnPower > -0.105 && turnPower < 0.0 && Math.abs(offset) >= 10.0) 
                turnPower = -0.105;
            else if (turnPower < 0.105 && turnPower > 0.0 && Math.abs(offset) >= 10.0)
                turnPower = 0.105;
            SmartDashboard.putNumber("limelight", distance);
            //This checks if the robot is "close enough" to facing the target as the real error will rarely ever be 0 exactly.
            //We don't need the error to be 0 exactly, we just need the robot to face the target with some allowable room for error
            if (Math.abs(offset) < 10.0)
                turnPower = 0.0;
            
            double forwardPower = clamp(-travelDistance * RobotMap.VISIONSPEED, RobotMap.MAXVISIONSPEED, -RobotMap.MAXVISIONSPEED);
            // System.out.println("turn: " + turnPower + " forward: " + forwardPower);
            driveCartesian(0, forwardPower, -turnPower);
        }
    }

    //Only turns the robot to face the target
    public void turnToTarget () {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        // System.out.println("Error: " + distance);

        //SmartDashboard.putNumber("limelight", distance);
        //Sets up a delay of length RobotMap.VISIONDELAY between the time the button is pressed and the robot starts following vision 
        if (delayed) {
          milStart = java.lang.System.currentTimeMillis();
          delayed = false;
        }

        if (java.lang.System.currentTimeMillis() > milStart + RobotMap.VISIONDELAY) {
            double turnPower = RobotMap.VISIONVELTURN * (offset / 22);
            driveCartesian(0, 0, -turnPower);
            // lFrontSpark.set(turnPower);
            // rFrontSpark.set(turnPower);
            // lBackSpark.set(turnPower);
            // rBackSpark.set(turnPower);

            // rightFrontTalon.set(ControlMode.Velocity,turnPower);
            // rightBackTalon.set(ControlMode.Velocity,turnPower);
            // leftFrontTalon.set(ControlMode.Velocity,turnPower);
            // leftBackTalon.set(ControlMode.Velocity,turnPower);
        }
      }

    //Turns RingLights Off
    public void lightOff () {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    //Sets delay to true
    public void toggleDelayed () {
        delayed = true;
    }

    //Sets turn delay to true
    public void toggleTurnDelay () {
        turnDelay = true;
    }

    public void readData () {
        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        offset = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        distance = (targetHeight - limeHeight)/(Math.tan( Math.PI/180 * (limeAngle+ty)));

        // System.out.println("our offset is " + offset + " and distance is " + distance);
    }
    
    
    
    // Autonomous Functions //

    //Tells the robot to move forward "x" feet
    //Convert "x" rotations to feet
    public void moveFeet(double x){
        lFrontSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        lBackSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        rFrontSparkPID.setReference(x, CANSparkMax.ControlType.kPosition);
        rBackSparkPID.setReference(x, CANSparkMax.ControlType.kPosition);
    }

    public boolean didMoveFeet () {
        //System.out.println("encoder in didmoveforward: " + lFrontSpark.getEncoder().getPosition());
        return lFrontSpark.getEncoder().getPosition() <= -(RobotMap.AUTONTAXIDISTANCE-1);
    }

    public boolean didMoveFeet (double ticks, CANSparkMax spark) {
        //System.out.println("encoder in didmoveforward: " + lFrontSpark.getEncoder().getPosition());
        return spark.getEncoder().getPosition() <= -(ticks-1) || spark.getEncoder().getPosition() >= (ticks+1);
    }

    //Tells the robot to turn "x" degrees
    //Convert "x" rotations to degrees
    public void turnDegrees (double x) {
        lFrontSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        lBackSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        rFrontSparkPID.setReference(x, CANSparkMax.ControlType.kPosition);
        rBackSparkPID.setReference(x, CANSparkMax.ControlType.kPosition); 
    }

    // Utility driving functions //

    //Keeps input value between the high and low values
    private double clamp (double input, double low, double high){ //utility function for drive cartesian
        if (input > high)
            return high;
        else if (input < low)
            return low;
        return input;
    }

    //Ignores inputs from the joysticks below a certain value in case the joystick isn't zero'd correctly
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