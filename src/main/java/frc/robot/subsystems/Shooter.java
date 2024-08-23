package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_motor = new CANSparkMax(Constants.shooterPort, MotorType.kBrushed);

  public Shooter() {}

  public Command shoot() {
    return run(() -> m_motor.setVoltage(12));
  }

  public Command intake() {
    return run(() -> m_motor.setVoltage(-12));
  }

  // Method to spin up the shooter (continuously run while the command is active)
  public Command spinUp() {
    return new RunCommand(() -> m_motor.setVoltage(12), this);
  }

  // Method to slow down the shooter (stops the motor when the command ends)
  public Command slowDown() {
    return new RunCommand(() -> m_motor.setVoltage(0), this);
  }
}
