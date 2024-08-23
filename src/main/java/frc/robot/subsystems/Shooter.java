package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private CANSparkMax m_motor = new CANSparkMax(Constants.shooterPort, MotorType.kBrushed);

  public Shooter() {}

  public Command shoot() {
    return run(() -> m_motor.setVoltage(12));
  }

  public Command intake() {
    return run(() -> m_motor.setVoltage(-12));
  }

  // Method to spin up the shooter (full speed)
  public Command spinUp() {
    return new InstantCommand(() -> m_motor.setVoltage(12), this);
  }

  // Method to slow down the shooter (stop)
  public Command slowDown() {
    return new InstantCommand(() -> m_motor.setVoltage(0), this);
  }
}
