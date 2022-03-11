package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

//local imports
import frc.robot.Subsystems.*;
import frc.robot.*;
import frc.robot.TeleOpCommands.*;



public class SequenceTwoBallAuton extends SequentialCommandGroup {
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public SequenceTwoBallAuton(HazyMechBase chassis, HazyShooter shooter, HazyIntake intake){
        // addCommands(
        //     new CommandRaiseDropIntake(intake), //needs to be changed to command which only drops the intake
        //     new ParallelCommandGroup(
        //         new CommandDriveDistance(chassis, RobotMap.FIRSTMOVE),
        //         new CommandSpinIntake(intake),
        //         new CommandShoot(shooter),
        //         new CommandTurnVision(chassis)
        //     )
        // );
        
        addCommands(
            new CommandDropIntake(intake),

            new ParallelCommandGroup(
                new CommandSpinIntake(intake),
                new SequentialCommandGroup(
                    new CommandMoveForward(chassis),
                    new TimedCommandTurnVision(java.lang.System.currentTimeMillis(), chassis),
                    new CommandStopMecBase(chassis),
                    new CommandShoot(shooter)
                )
                // stop
            )

            // new ParallelCommandGroup(
            //     new CommandMoveForward(chassis),
            //     new TimedCommandSpinIntake(java.lang.System.currentTimeMillis(), intake)
            //     ),
            // new WaitCommand(2),
            // new CommandStopIntake(intake),
            // new TimedCommandTurnVision(java.lang.System.currentTimeMillis(), chassis),
            // //new WaitCommand(.5),
            // //new CommandStopMecBase(chassis),
            // new ParallelCommandGroup(
            //     new CommandShoot(shooter),
            //     new CommandSpinIntake(intake)
            // )
            
        );
       
    }
}