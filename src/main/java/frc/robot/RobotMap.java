package frc.robot; //folder the file is in

//Stores all constant values for the robot (ports, etc.)
public class RobotMap {
    public static final boolean PRINTSENABLED = false;

    // OI //
    public static final int CONTROLLERPORT = 0;
    public static final int LEFTJOYSTICKPORT = 1;
    public static final int RIGHTJOYSTICKPORT = 2;

    public static final int LEFTTRIGGERAXIS = 2;
    public static final int RIGHTTRIGGERAXIS = 3;

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

    public static final int INTAKEDROPPOSITION = -1000;
    public static final double INTAKEHALFRAISEPOSITION = -900;
    public static final int INTAKERAISEPOSITION = -500;

    public static final double INTAKEDROPSPEED = 0.5;
    public static final double INTAKERAISESPEED = -0.7;

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
    public static final double SHOOTERSPEED = 14750; //Actual shooter speed should be 14750 but the right one always spins faster for some reason so it needs a lower setpoint
    public static final double RIGHTSHOOTERSPEED = 12300;
    public static final double AUTOSHOOTSPEED = 13750;
    public static final double SHOOTERLOWSPEED = 8500;
    public static final double LEFTSHOOTERP = 0.015;
    public static final double RIGHTSHOOTERP = 0.015;
    public static final double LEFTSHOOTERI = 0;
    public static final double RIGHTSHOOTERI = 0;
    public static final double SHOOTERD = 0.03;
    public static final double LEFTSHOOTERF =((.483 * 1023) / 14750); //12/18730) * (14750/8192); //
    public static final double RIGHTSHOOTERF = ((.483 * 1023) / 14750)*.92; //12/18730) * (14750/8192); //

    public static final double SHOOTERMAX = 1;
    public static final double HIGHFEEDERSPEED = .7;

    // Lift //
    public static final int LIFTMOTORPORTLEFT = 12;
    public static final int LIFTMOTORPORTRIGHT = 4;
    public static final int TILTMOTORPORT = 2;

    public static final int HALENCONDERTICKSPERROTATION = 8192;

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
    public static final double MAXLIFTHEIGHT = 3.4;
    public static final double MAXMAXLIFTHEIGHT = 5.45;
    public static final double MINLIFTHEIGHT = 0.05;
    public static final double MINTILT = 0.05;
    public static final double MAXTILT = 7.5;

    public static final double MAINTAINPOSVOLTAGE = 0;

    // Autonomous //  
    public static final double FIRSTMOVE = 0;
    public static final double FIRSTTURN = 0;
    public static final int AUTONTAXIDISTANCE = 74;
    public static final double MAXCHASSISPIDSPEED = 0.35;

    // Vision //
    public static final int BAUDRATE = 115200;
    public static final int VISIONDELAY = 200;
    public static final double SHOOTDISTANCE = 180;
    public static final double MAXVISIONSPEED = .4;
    public static final double VISIONVELTURN = 0.5;
    public static final double VISIONTURN = .001;
    public static final double VISIONSPEED = .05;
    public static final double VISIONTARGETHEIGHT = 104.0;
    public static final double LIMELIGHTHEIGHT = 36.0;
    public static final double LIMELIGHTANGLE = 12;
    public static final double SHOOTOFFSET = 3.75;
    public static final double VISIONDISTANCEERRORRANGE = 1;
    public static final int VISIONOFFSETDIVISOR = 22; //The max horizontal offset you expect to receive, because you want the max offset to only give a power of 1 or -1 as these are the max powers the motorcontrollers accept
}