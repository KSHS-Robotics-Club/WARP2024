// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  public static Command Taxi(Drivetrain drivetrain, Shooter shooter) {
    return shooter.shoot().andThen(drivetrain.drive(() -> 11, () -> 11).withTimeout(3)).andThen(drivetrain.stop());
  } 

  public static Command Shoot(Shooter shooter) {
    return shooter.shoot();

  } 
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
