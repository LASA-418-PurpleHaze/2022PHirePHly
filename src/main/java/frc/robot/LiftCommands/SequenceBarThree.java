package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//local imports
import frc.robot.Subsystems.HazyLift;

public class SequenceBarThree extends SequentialCommandGroup {
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public SequenceBarThree(HazyLift lift){
        addCommands(
            new CommandBarThreeTilt(lift), 
            new CommandBarThreeExtend(lift),
            new CommandBarThreeTiltBack(lift),
            new CommandBarThreePull(lift)
        );  
    }
}