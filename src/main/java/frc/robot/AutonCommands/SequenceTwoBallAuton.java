package frc.robot.AutonCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

//local imports
import frc.robot.Subsystems.*;
import frc.robot.TeleOpCommands.*;

public class SequenceTwoBallAuton extends SequentialCommandGroup {

    //Pass the subsystem and controllers used in command into the constructor for initialization
    // public SequenceTwoBallAuton(HazyMechBase chassis, HazyShooter shooter, HazyIntake intake){
    //     addCommands(
    //         new CommandDropIntake(intake),
    //         new ParallelCommandGroup(
    //             new TimedCommandSpinIntake(intake), //the intake should be spinning throughout the whole sequence
    //             new SequentialCommandGroup (
                    
    //                 // new CommandShooterDefault(shooter),
    //                 new SequentialCommandGroup(
    //                     new CommandMoveForward(chassis),
    //                     // new TimedCommandTurnVision(chassis),
    //                     new CommandStopMecBase(chassis)
    //                     //new CommandTimedShoot(shooter)
    //                 ),
    //                 new CommandAutoShoot(shooter)
    //                 // new CommandTimedShootSlower(shooter)
    //             )
    //         ),

    //         //stop all subsystems after the balls are shot
    //         new ParallelCommandGroup(
    //             new CommandShooterDefault(shooter),
    //             new CommandIntakeDefault(intake)
    //         )
    //     );

    public SequenceTwoBallAuton(HazyMechBase chassis, HazyShooter shooter, HazyIntake intake){
        addCommands(
            new TimedAutonCommandDropIntake(intake),
            new ParallelCommandGroup(
                 //the intake should be spinning throughout the whole sequence
                new SequentialCommandGroup (
                    new TimedCommandSpinIntake(intake),
                    //new CommandTimedShoot(shooter),
                    // new CommandShooterDefault(shooter),
                    new SequentialCommandGroup(
                        new CommandMoveForward(chassis),
                        new TimedCommandTurnVision(chassis),
                        new CommandStopMecBase(chassis),
                        //new Command
                        //TimedCommandSpinOuttake(intake),
                        //new CommandIntakeDefault(intake),
                        new ParallelCommandGroup(
                            new TimedCommandSpinOuttake(intake),
                            new TimedCommandHighFeedBack(shooter)
                        ),
                        new CommandStopIntake(intake),
                        new ParallelCommandGroup(
                            new CommandTimedShoot(shooter),
                            new SequentialCommandGroup(
                                new CommandAutoWait(),
                                new TimedCommandSpinIntake(intake)
                            )
                            
                        )
                        
                    )
                    // new CommandTimedShootSlower(shooter)
                )
            ),

            //stop all subsystems after the balls are shot
            new ParallelCommandGroup(
                new CommandShooterDefault(shooter),
                new CommandIntakeDefault(intake)
            )
        );

        // addCommands(
        //     //new CommandDropIntake(intake),
        //     new ParallelCommandGroup(
        //         //new TimedCommandSpinIntake(intake),
        //         new SequentialCommandGroup (
        //             new CommandMoveForward(chassis),
        //             //new TimedCommandTurnVision(chassis),
        //             new CommandStopMecBase(chassis),
        //             new CommandTimedShoot(shooter)
        //         )
        //     ),
        //     new ParallelCommandGroup(
        //         new CommandShooterDefault(shooter),
        //         new CommandIntakeDefault(intake)
        //     )
        // );
    }
}