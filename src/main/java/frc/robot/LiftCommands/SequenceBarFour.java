package frc.robot.LiftCommands; //folder the file is in

//wpilib imports
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//local imports
import frc.robot.Subsystems.HazyLift;

public class SequenceBarFour extends SequentialCommandGroup {
    
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public SequenceBarFour(HazyLift lift){
        addCommands(
            new CommandBarFourTilt(lift), 
            new CommandBarFourLiftDown(lift)
        );  
    }
}