/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1099.robot;

import static org.usfirst.frc.team1099.robot.commands.ElevatorCmd.*;
import static org.usfirst.frc.team1099.robot.commands.IntakeCmd.*;

import org.usfirst.frc.team1099.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// 1099 CONTROLLERS
	
	// Driver - Tank drive on two Attack Joysticks
	public static Joystick lstick = new Joystick(RobotMap.leftJoystick);
	public static Joystick rstick = new Joystick(RobotMap.rightJoystick);
	
	// Operator - XBox controller
	public static Joystick gamepad = new Joystick(RobotMap.gamepad);
	
	public OI() {
		
		// Elevator
		//Button aButton = new JoystickButton(gamepad, RobotMap.AButton);
		//Button bButton = new JoystickButton(gamepad, RobotMap.BButton);
		
		//aButton.whileHeld(new ElevatorControl(DOWN));	
		//bButton.whileHeld(new ElevatorControl(UP));
		
		// Actuator
		Button xButton = new JoystickButton(gamepad, RobotMap.XButton);
	    Button yButton = new JoystickButton(gamepad, RobotMap.YButton);
	    
		xButton.whileHeld(new Tilt(Tilt.DOWN));
		yButton.whileHeld(new Tilt(Tilt.UP));
		yButton.whenReleased(new Tilt(Tilt.STOP));
		xButton.whenReleased(new Tilt(Tilt.STOP));
		
	    // Intake 
		Button leftBumper = new JoystickButton(gamepad, RobotMap.leftBumper);
		Button rightBumper = new JoystickButton(gamepad, RobotMap.rightBumper);
		
		leftBumper.whileHeld(new IntakeControl(IN));
		rightBumper.whileHeld(new IntakeControl(OUT));

		
		
		
	}
}
