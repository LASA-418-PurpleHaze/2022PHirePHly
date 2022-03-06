package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandLift extends CommandBase{

    //Declare the controllers and subsystem used to control the command
    private HazyLift c_hazyLift;
    //Pass the subsystem and controllers used in command into the constructor for initialization
    public CommandLift(HazyLift lift){
        c_hazyLift = lift;
    }

    //The function that is called by the commandscheduler when command is called
    public void execute(){
        c_hazyLift.stupidLift();
    }
}
