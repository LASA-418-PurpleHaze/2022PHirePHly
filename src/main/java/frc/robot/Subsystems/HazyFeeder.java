package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.*;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HazyFeeder extends SubsystemBase {
    private CANSparkMax highFeeder;

    private boolean feederEnabled;

    public HazyFeeder () {
        highFeeder = new CANSparkMax(RobotMap.HIGHFEEDERSPARK, MotorType.kBrushed);
    }

    public void toggleHighFeeder () {

    }
}
