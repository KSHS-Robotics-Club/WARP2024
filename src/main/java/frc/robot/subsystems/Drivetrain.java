package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
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
    m_rightLeadMotor = new CANSparkMax(Constants.rightLeadDrivebasePort, MotorType.kBrushed);
    m_rightFollowerMotor = new CANSparkMax(Constants.rightFollowerDrivebasePort, MotorType.kBrushed);
    m_rightFollowerMotor.follow(m_rightLeadMotor);
    m_leftLeadMotor = new CANSparkMax(Constants.leftLeadDrivebasePort, MotorType.kBrushed);
    m_leftFollowerMotor = new CANSparkMax(Constants.leftFollowerDrivebasePort, MotorType.kBrushed);
    m_leftFollowerMotor.follow(m_leftLeadMotor);
    m_leftLeadMotor.setInverted(false);
  }

  public Command drive(DoubleSupplier left, DoubleSupplier right) {
    return Commands.run(() -> {
      var leftnew = MathUtil.applyDeadband(left.getAsDouble(), 0.05);
      var rightnew = MathUtil.applyDeadband(right.getAsDouble(), 0.05);
      m_leftLeadMotor.set(leftnew);
      m_rightLeadMotor.set(rightnew);
    }, this);
  }
}
