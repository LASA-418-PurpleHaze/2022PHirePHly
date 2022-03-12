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
        addCommands(
            //new CommandDropIntake(intake),
            new ParallelCommandGroup(
                //new TimedCommandSpinIntake(intake),
                new SequentialCommandGroup(
                    new CommandMoveForward(chassis),
                    //new TimedCommandTurnVision(chassis),
                    new CommandStopMecBase(chassis)
                    //new CommandTimedShoot(shooter)
                )
                // stop
            ),
            new ParallelCommandGroup(
                new CommandShooterDefault(shooter),
                new CommandIntakeDefault(intake)
            )
        );
    }
}