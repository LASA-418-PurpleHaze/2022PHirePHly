package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//local imports
import frc.robot.Subsystems.HazyLift;

public class SequenceBarTwo extends SequentialCommandGroup {
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public SequenceBarTwo(HazyLift lift){
        addCommands(
            new CommandBarTwoLiftUp(lift), 
            new CommandBarTwoLiftDown(lift)
        );  
    }
}