package frc.robot; //folder the file is in

//Stores all constant values for the robot (ports, etc.)
public class RobotMap {
    // OI //
    public static final int CONTROLLERPORT = 0;
    public static final int LEFTJOYSTICKPORT = 1;
    public static final int RIGHTJOYSTICKPORT = 2;

    // Drive Train //
    public static final int LEFTFRONTSPARK = 9;
    public static final int RIGHTFRONTSPARK = 5;
    public static final int LEFTBACKSPARK = 3;
    public static final int RIGHTBACKSPARK = 11;

    public static final double CHASSISLEFTFRONTP = 1;
    public static final double CHASSISLEFTFRONTI = 0;
    public static final double CHASSISLEFTFRONTD = 0;
    public static final double CHASSISLEFTFRONTF = 0;

    public static final double CHASSISBACKFRONTP = 1;
    public static final double CHASSISBACKFRONTI = 0;
    public static final double CHASSISBACKFRONTD = 0;
    public static final double CHASSISBACKFRONTF = 0;

    public static final double CHASSISLEFTBACKP = 1;
    public static final double CHASSISLEFTBACKI = 0;
    public static final double CHASSISLEFTBACKD = 0;
    public static final double CHASSISLEFTBACKF = 0;

    public static final double CHASSISRIGHTBACKP = 1;
    public static final double CHASSISRIGHTBACKI = 0;
    public static final double CHASSISRIGHTBACKD = 0;
    public static final double CHASSISRIGHTBACKF = 0;

    public static final double DEADBAND = 0.25;

    // Intake //
    public static final int INTAKESPINTALON = 0;
    public static final int INTAKEUPDOWNTALON = 1;
    public static final int INTAKEDOWNTICKS = -1600;
    public static final int INTAKEUPTICKS = -200;

    public static final double INTAKEUPP = 0.65;
    public static final double INTAKEUPI = 0;
    public static final double INTAKEUPD = 0;
    public static final double INTAKEUPF = 0;

    public static final double INTAKEDOWNP = 0.7;
    public static final double INTAKEDOWNI = 0;
    public static final double INTAKEDOWND = 2;
    public static final double INTAKEDOWNF = 0;

    // Shooter //
    public static final int SHOOTERTALONLEFT = 6;
    public static final int SHOOTERTALONRIGHT = 8;
    public static final int HIGHFEEDERSPARK = 10;
    public static final double SHOOTERSPEED = 15000;
    public static final double SHOOTERLOWSPEED = 8500;
    public static final double SHOOTERP = 0.015;
    public static final double SHOOTERI = 0.00008;
    public static final double SHOOTERD = 0;
    public static final double SHOOTERF = (3/1561) * (SHOOTERSPEED/4096);
    public static final double SHOOTERMAX = 1;
    public static final double HIGHFEEDERSPEED = 0.7;

    // Lift //
    public static final int LIFTMOTORPORTLEFT = 12;
    public static final int LIFTMOTORPORTRIGHT = 4;
    public static final int TILTMOTORPORT = 2;

    public static final double TILTP = 5;
    public static final double TILTI = 0;
    public static final double TILTD = 0;
    public static final double TILTF = 0;


    public static final double LIFTP = 30;
    public static final double LIFTI = 0;
    public static final double LIFTD = 4;
    public static final double LIFTF = 0;

    public static final double BARTWOUP = 2.45;
    public static final double BARTWODOWN = 0;
    public static final double BARTHREETILT = 0.05;
    public static final double BARTHREEEXTEND = 0;
    public static final double BARTHREETILTBACK = 0;
    public static final double BARTHREEPULL = 0;
    public static final double MAXLIFTHEIGHT = -3.3;
    public static final double MINLIFTHEIGHT = -0.05;
    public static final double MAINTAINPOSVOLTAGE = 0;

    // Autonomous //
    public static final double FIRSTMOVE = 0;
    public static final double FIRSTTURN = 0;
    public static final int AUTONTAXIDISTANCE = 45;

    // Vision //
    public static final int BAUDRATE = 115200;
    public static final int VISIONDELAY = 200;
    public static final double SHOOTDISTANCE = 133;
    public static final double MAXVISIONSPEED = .3;
    public static final double VISIONVELTURN = 0.5;
    public static final double VISIONTURN = .001;
    public static final double VISIONSPEED = .007;
}