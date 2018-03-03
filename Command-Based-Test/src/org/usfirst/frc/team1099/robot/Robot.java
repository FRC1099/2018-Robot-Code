/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1099.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1099.robot.commands.auto.AutoForward;
import org.usfirst.frc.team1099.robot.commands.auto.AutoTurn;
import org.usfirst.frc.team1099.robot.commands.groups.DriveForward;
import org.usfirst.frc.team1099.robot.commands.groups.BackAndForth;
import org.usfirst.frc.team1099.robot.commands.groups.DoNothing;
import org.usfirst.frc.team1099.robot.commands.groups.Forward180;
import org.usfirst.frc.team1099.robot.commands.groups.OwnSwitchFromMiddle;
import org.usfirst.frc.team1099.robot.commands.groups.StraightTurnToss;
import org.usfirst.frc.team1099.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1099.robot.subsystems.Elevator;
import org.usfirst.frc.team1099.robot.subsystems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Elevator elevator = new Elevator();
	public static final Intake intake = new Intake();
	
	public static OI oi;

	Command autonomousCommand = new DoNothing();
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	CameraServer cam0 = CameraServer.getInstance();
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		oi = new OI();
		cam0.startAutomaticCapture();
		
		// Put Autocommands on the dashboard
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addObject("Drive Forward", new DriveForward());
		chooser.addObject("Turn 90 L", new AutoTurn(AutoTurn.LEFT, 90));
		
		SmartDashboard.putData("Auto:", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
				
		autonomousCommand = chooser.getSelected();
		
		autonomousCommand = new StraightTurnToss();
		
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public static final boolean LEFT = true;
	public static final boolean RIGHT = false;
	
	private static final int SWITCH = 0;
	private static final int SCALE = 1;
	
	public static boolean getSwitchSide() {
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		
		if (gameData.length() > 0) {
			if (gameData.charAt(SWITCH) == 'L') {
				return LEFT;
			}
			else {
				return RIGHT;
			}
		}
		return RIGHT;
	}
	
	public static boolean getScaleSide() {
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		
		if (gameData.length() > 0) {
			if (gameData.charAt(SCALE) == 'L') {
				return LEFT; 
			}
			else {
				return RIGHT;
			}
		}
		return RIGHT;
	}

	
}
