// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final Indexer m_indexer = new Indexer();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private boolean isHalfSpeed = false; //Half speed modification

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    m_indexer.setDefaultCommand(m_indexer
    .stop());
    m_shooter.setDefaultCommand(m_shooter
    .stop());

    // Toggle speed mode with A button

    m_driverController.a().onTrue(Commands.runOnce(() -> {
      isHalfSpeed = !isHalfSpeed;
      System.out.println("Speed mode: " + (isHalfSpeed ? "Half" : "Full"));
    }));

    // Modify the default command for the drivetrain

    m_drivetrain.setDefaultCommand(

        m_drivetrain.drive(

            () -> isHalfSpeed ? m_driverController.getLeftY() * 0.5 : m_driverController.getLeftY(),

            () -> isHalfSpeed ? m_driverController.getRightY() * 0.5 : m_driverController.getRightY()

        )

    );

    m_driverController.leftBumper().whileTrue(Commands.parallel(m_shooter.intake(), m_indexer.intake()));
    m_driverController.rightBumper()
        .whileTrue(m_shooter.shoot().withTimeout(2).andThen(Commands.parallel(m_shooter.shoot(), m_indexer.shoot())));
  }




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(String chosen) {
    // An example command will be run in autonomous
    switch (chosen) {
      case "Taxi":
        return Autos.Taxi(m_drivetrain, m_shooter);
      case "Shoot":
        return m_shooter.shoot().withTimeout(2).andThen(Commands.parallel(m_shooter.shoot(), m_indexer.shoot()));
      case "Shoot + Taxi":
        return m_shooter.shoot().withTimeout(2).andThen(Commands.parallel(m_shooter.shoot(), m_indexer.shoot()).withTimeout(2)).andThen(m_drivetrain.drive(() -> 1, () -> 1).withTimeout(1.5));
      default:
              return m_shooter.shoot().withTimeout(2).andThen(Commands.parallel(m_shooter.shoot(), m_indexer.shoot()).withTimeout(2)).andThen(m_drivetrain.drive(() -> -1, () -> - 1).withTimeout(1.5));
    }
  }
}
