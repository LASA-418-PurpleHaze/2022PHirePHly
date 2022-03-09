package frc.robot.TeleOpCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.HazyIntake;
import frc.robot.Subsystems.HazyLift;
import frc.robot.Subsystems.HazyMechBase;
import frc.robot.Subsystems.HazyShooter;

public class CommandResetAllEncoders extends CommandBase {
    private HazyMechBase c_hazyMechBase;
    private HazyIntake c_hazyIntake;
    // private HazyShooter c_hazyShooter;
    private HazyLift c_hazyLift;

    public CommandResetAllEncoders (HazyMechBase mechBase, HazyIntake intake, HazyLift lift) {
        mechBase.resetEncoders();
        intake.resetEncoders();
        lift.resetEncoders();
    }
}
