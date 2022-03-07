package frc.robot; //folder the file is in

//Stores all constant values for the robot (ports, etc.)
public class RobotMap {
    // OI //
    public static final int CONTROLLERPORT = 0;
    public static final int LEFTJOYSTICKPORT = 1;
    public static final int RIGHTJOYSTICKPORT = 2;

    // Drive Train //
    public static final int LEFTFRONTSPARK = 9;
    public static final int RIGHTFRONTSPARK = 7;
    public static final int LEFTBACKSPARK = 3;
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

<<<<<<< Updated upstream
    public static final double DEADBAND = 0.1;
=======
    // Intake //
    public static final int INTAKESPINTALON = 0;
    public static final int INTAKEUPDOWNTALON = 1;
    public static final int INTAKEDOWNTICKS = -1200;
    public static final int INTAKEUPTICKS = 3;

    public static final double INTAKEUPDOWNP = .5;
    public static final double INTAKEUPDOWNI = 0;
    public static final double INTAKEUPDOWND = 0;
    public static final double INTAKEUPDOWNF = 0;
    public static final double DEADBAND = 0.1;

    // Shooter //
    public static final int SHOOTERSPARKLEFT = 0;
    public static final int SHOOTERSPARKRIGHT = 0;
    public static final int HIGHFEEDERSPARK = 0;
    public static final double SHOOTERP = 0;
    public static final double SHOOTERI = 0;
    public static final double SHOOTERD = 0;
    public static final double SHOOTERF = 0;
    public static final double SHOOTERMAX = 1;
    public static final double SHOOTERSPEED = 3000;
    public static final double HIGHFEEDERSPEED = 0.3;

    // Lift //
    public static final int LIFTMOTORPORTLEFT = 0;
    public static final int LIFTMOTORPORTRIGHT = 0;
    public static final int TILTMOTORPORT = 0;

    public static final double TILTP = 0;
    public static final double TILTI = 0;
    public static final double TILTD = 0;
    public static final double TILTF = 0;
    public static final double LIFTP = 0;
    public static final double LIFTI = 0;
    public static final double LIFTD = 0;
    public static final double LIFTF = 0;

    public static final double BARONEUPTICKS = 0;
    public static final double BARONEDOWNTICKS = 0;
    public static final double BARTWOUPTICKS = 0;
    public static final double BARTWODOWNTICKS = 0;
    public static final double BARTHREETILTTICKS = 0;
    public static final double BARTHREEDOWNTICKS = 0;
    public static final double BARFOURTILTTICKS = 0;
    public static final double BARFOURDOWNTICKS = 0;

    // Autonomous //
    public static final double FIRSTMOVE = 0;
    public static final double FIRSTTURN = 0;

    // Vision //
    public static final int BAUDRATE = 115200;
    public static final int VISIONDELAY = 500;
    public static final double SHOOTDISTANCE = 0;
    public static final double MAXVISIONSPEED = .5;
    public static final double VISIONVELTURN = 15.0;
    public static final double VISIONTURN = .001;
    public static final double VISIONSPEED = .007;

>>>>>>> Stashed changes
}