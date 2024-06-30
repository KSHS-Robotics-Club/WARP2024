package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax m_rightLeadMotor;
  private CANSparkMax m_rightFollowerMotor;
  private CANSparkMax m_leftLeadMotor;
  private CANSparkMax m_leftFollowerMotor;

  public Drivetrain() {
    m_rightLeadMotor = new CANSparkMax(Constants.rightLeadDrivebasePort, MotorType.kBrushless);
    m_rightFollowerMotor = new CANSparkMax(Constants.rightFollowerDrivebasePort, MotorType.kBrushless);
    m_rightFollowerMotor.follow(m_rightLeadMotor);
    m_leftLeadMotor = new CANSparkMax(Constants.leftLeadDrivebasePort, MotorType.kBrushless);
    m_leftFollowerMotor = new CANSparkMax(Constants.leftFollowerDrivebasePort, MotorType.kBrushless);
    m_leftFollowerMotor.follow(m_leftLeadMotor);
  }

  public Command drive(DoubleSupplier left, DoubleSupplier right) {
    return Commands.run(() -> {
      m_leftLeadMotor.set(left.getAsDouble());
      m_rightLeadMotor.set(right.getAsDouble());
    });
  }
}
