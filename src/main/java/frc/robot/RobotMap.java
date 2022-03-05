package frc.robot;

//Stores all constant values for the robot (ports, etc.)
public class RobotMap {
    // IO //
    public static final int CONTROLLERPORT = 0;
    public static final int LEFTJOYSTICKPORT = 1;
    public static final int RIGHTJOYSTICKPORT = 2;

    // Drive Train //

    public static final int LEFTFRONTSPARK = 9;
    public static final int RIGHTFRONTSPARK = 7;
    public static final int LEFTBACKSPARK = 12;
    public static final int RIGHTBACKSPARK = 2;

    public static final double CHASSISLEFTFRONTP = 0;
    public static final double CHASSISLEFTFRONTI = 0;
    public static final double CHASSISLEFTFRONTD = 0;
    public static final double CHASSISLEFTFRONTF = 0;

    public static final double CHASSISBACKFRONTP = 0;
    public static final double CHASSISBACKFRONTI = 0;
    public static final double CHASSISBACKFRONTD = 0;
    public static final double CHASSISBACKFRONTF = 0;

    public static final double CHASSISLEFTBACKP = 0;
    public static final double CHASSISLEFTBACKI = 0;
    public static final double CHASSISLEFTBACKD = 0;
    public static final double CHASSISLEFTBACKF = 0;

    public static final double CHASSISRIGHTBACKP = 0;
    public static final double CHASSISRIGHTBACKI = 0;
    public static final double CHASSISRIGHTBACKD = 0;
    public static final double CHASSISRIGHTBACKF = 0;

    // Shooter //
    //public static final int SHOOTERSPARKRIGHT = 6;
    public static final int SHOOTERSPARKLEFT = 5;
    public static final double SHOOTERSPEED = 1000;

    public static final double SHOOTERP = 1;
    public static final double SHOOTERI = 0;
    public static final double SHOOTERD = 0;
    public static final double SHOOTERFF = 0;

    // High Feeder //
    public static final int HIGHFEEDERTALON = 5;

    // Intake //
    public static final int INTAKESPINTALON = 0;
    public static final int INTAKEUPDOWNTALON = 1;
    public static final int INTAKEDOWNTICKS = -2000;
    public static final int INTAKEUPTICKS = 0;

    public static final double INTAKEUPDOWNP = 1;
    public static final int INTAKEUPDOWNI = 0;
    public static final int INTAKEUPDOWND = 0;
    public static final int INTAKEUPDOWNF = 0;
    public static final double DEADBAND = 0.1;
    

}