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
    private boolean delayed;
    private boolean turnDelay;
    private double milStart;

    private double tv;
    private double offset;
    private double ty;
    private double ta;

    private double distance;



    private double turnDerivative, turnIntegral, turnPreviousError, turnSetPoint, turnError, turnP, turnI, turnD;
    private double moveDerivative, moveIntegral, movePreviousError, moveSetPoint, moveError, moveP, moveI, moveD;

    private double forwardVoltage, turnVoltage;
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
        lFrontSparkPID.setOutputRange(-RobotMap.MAXCHASSISPIDSPEED, RobotMap.MAXCHASSISPIDSPEED);
        lBackSparkPID.setOutputRange(-RobotMap.MAXCHASSISPIDSPEED, RobotMap.MAXCHASSISPIDSPEED);
        rFrontSparkPID.setOutputRange(-RobotMap.MAXCHASSISPIDSPEED, RobotMap.MAXCHASSISPIDSPEED);
        rBackSparkPID.setOutputRange(-RobotMap.MAXCHASSISPIDSPEED, RobotMap.MAXCHASSISPIDSPEED);

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

    public double[] getCurrentWheelPos () {
        double[] pos = new double[4];
        pos[0] = lFrontSpark.getEncoder().getPosition();
        pos[1] = rFrontSpark.getEncoder().getPosition();
        pos[2] = lBackSpark.getEncoder().getPosition();
        pos[3] = rBackSpark.getEncoder().getPosition();
        return pos;
    }



    // Joystick Driving Functions //
    
    //Mecanum drive function that is called by the default
    public void driveCartesian (double x, double y, double angle) {
        x = clamp(x, -1.0, 1.0);
        x = applyDeadband(x, RobotMap.DEADBAND);
        y = clamp(y, -1.0, 1.0);
        y = applyDeadband(y, RobotMap.DEADBAND);

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

    public double PIDmove() {
        moveError = RobotMap.SHOOTDISTANCE - distance;
        moveIntegral += (moveError*0.02);
        moveIntegral = (moveError - movePreviousError) / 0.02;
        return moveP*movePreviousError + moveI*moveIntegral + moveD*moveDerivative;
    }

    public double PIDturn() {
        turnError = offset;
        turnIntegral += (turnError*0.02);
        turnIntegral = (turnError - turnPreviousError) / 0.02;
        return turnP*turnPreviousError + turnI*turnIntegral + turnD*turnDerivative;
    }
    
    public void PIDGoToTarget() {
        readData();
        forwardVoltage = PIDmove();
        turnVoltage = PIDturn();

        driveCartesian(0, forwardVoltage, -turnVoltage);
    }


    // Vision Functions //

    /*

    Note from Nathan on 4/4/22:
    Instead of feeding the limelight distance into the pid loop we can take the (shooting distance - limelight distance) * encoder ticks per foot and then feed that position into the pid loop.

    */
    //Turns the robot to face the target and drives it to the correct shooting distance
    public void goToTarget () {
        //solenoidToLight.set(true);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
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

            //System.out.println("vision distance " + distance);
            //System.out.println("travel: " + travelDistance);
            // PHrint.p("travel distance " + travelDistance);

            // double turnPower = clamp(RobotMap.VISIONTURN * (offset/22), RobotMap.MAXVISIONSPEED, -RobotMap.MAXVISIONSPEED);
            double turnPower = RobotMap.VISIONVELTURN * (offset/RobotMap.VISIONOFFSETDIVISOR);  
            // if(offset < 4){

            // } 
            // turnPower = clamp(turnPower, -.105, .105);
            SmartDashboard.putNumber("limelight", distance);
            
            double forwardPower = clamp(travelDistance * RobotMap.VISIONSPEED, -RobotMap.MAXVISIONSPEED, RobotMap.MAXVISIONSPEED);
            if (Math.abs(travelDistance) < RobotMap.VISIONDISTANCEERRORRANGE) {
                forwardPower = 0;
            }
            PHrint.p("turn power " + turnPower);
            //print.p("turn: " + turnPower + " forward: " + forwardPower);

            driveCartesian(0, forwardPower, -turnPower);
        }
    }

    //Only turns the robot to face the target
    public void turnToTarget () {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        //print.p("Error: " + distance);

        //SmartDashboard.putNumber("limelight", distance);
        //Sets up a delay of length RobotMap.VISIONDELAY between the time the button is pressed and the robot starts following vision 
        if (delayed) {
          milStart = java.lang.System.currentTimeMillis();
          delayed = false;
        }

        if (java.lang.System.currentTimeMillis() > milStart + RobotMap.VISIONDELAY) {
            double turnPower = RobotMap.VISIONVELTURN * (offset / RobotMap.VISIONOFFSETDIVISOR);
            PHrint.p("Turn Power: " + turnPower);
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
        offset = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0) + RobotMap.SHOOTOFFSET;
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        PHrint.p("ty " + ty + " offsest " + offset);
        distance = (RobotMap.VISIONTARGETHEIGHT - RobotMap.LIMELIGHTHEIGHT) / (Math.tan( (Math.PI/180) * (RobotMap.LIMELIGHTANGLE + ty)));

        PHrint.p(distance);
    }
    
    
    
    // Autonomous Functions //

    //Tells the robot to move forward "x" feet
    //Convert "x" rotations to feet
    public void moveFeet(double x) {
        lFrontSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        rFrontSparkPID.setReference(x, CANSparkMax.ControlType.kPosition);
        lBackSparkPID.setReference(-x, CANSparkMax.ControlType.kPosition);
        rBackSparkPID.setReference(x, CANSparkMax.ControlType.kPosition);
    }

    public void moveFeet(double x, double[] initalWheelPos){
        lFrontSparkPID.setReference(initalWheelPos[0] - x, CANSparkMax.ControlType.kPosition);
        rFrontSparkPID.setReference(initalWheelPos[1] + x, CANSparkMax.ControlType.kPosition);
        lBackSparkPID.setReference(initalWheelPos[2] - x, CANSparkMax.ControlType.kPosition);
        rBackSparkPID.setReference(initalWheelPos[3] + x, CANSparkMax.ControlType.kPosition);
    }

    public boolean didMoveFeet () {
        //print.p("encoder in didmoveforward: " + lFrontSpark.getEncoder().getPosition());
        return lFrontSpark.getEncoder().getPosition() <= -(RobotMap.AUTONTAXIDISTANCE-1);
    }

    public boolean didMoveFeet (double[] initialWheelPos) {
        //print.p("encoder in didmoveforward: " + lFrontSpark.getEncoder().getPosition());
        return lFrontSpark.getEncoder().getPosition() <= initialWheelPos[0]-(RobotMap.AUTONTAXIDISTANCE-1);
    }

    public boolean didMoveFeet (double ticks, CANSparkMax spark) {
        //print.p("encoder in didmoveforward: " + lFrontSpark.getEncoder().getPosition());
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

    public void putData () {
        SmartDashboard.putNumber("Left Front Position", lFrontSpark.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Front Position", rFrontSpark.getEncoder().getPosition());
        SmartDashboard.putNumber("Left Back Position", lBackSpark.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Back Position", rBackSpark.getEncoder().getPosition());

        SmartDashboard.putNumber("Left Front amps", lFrontSpark.getOutputCurrent());
        SmartDashboard.putNumber("Right Front amps", rFrontSpark.getOutputCurrent());
        SmartDashboard.putNumber("Left Back amps", lBackSpark.getOutputCurrent());
        SmartDashboard.putNumber("Right Back amps", rBackSpark.getOutputCurrent());
    }
}