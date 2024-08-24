package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private CANSparkMax m_motor = new CANSparkMax(Constants.shooterPort, MotorType.kBrushed);

  public Shooter() {
    m_motor.setInverted(true);
  }

  public Command shoot() {
    return run(() -> m_motor.setVoltage(12));
  }

  public Command intake() {
    return run(() -> m_motor.setVoltage(-12));
  }
  public Command stop() {
    return runOnce(() -> m_motor.stopMotor());
  }
}