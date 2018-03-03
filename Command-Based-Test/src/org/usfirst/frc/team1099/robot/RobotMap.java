/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1099.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Joystick USB ports
	public static int leftJoystick = 0;
	public static final int rightJoystick = 1;
	public static final int gamepad = 2;
	
	// Drivetrain CAN IDs
	public static int leftDriveMotor = 6;		
	public static int leftFollowerMotor = 7;	
	
	public static int rightDriveMotor = 3;		
	public static int rightFollowerMotor = 4;	
	
	// Elevator CAN ID
	public static int elevatorMotor = 55;
	
	// Intake CAN IDs
	public static int leftIntake = 0;
	public static int rightIntake = 1;
	
	// Intake Tilt PWM ID
	public static int intakeTilt = 5;
	
	//gamepad buttons
	public static final int AButton = 1;
	public static final int BButton = 2;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int XButton = 3;
	public static final int YButton = 4;
	
}