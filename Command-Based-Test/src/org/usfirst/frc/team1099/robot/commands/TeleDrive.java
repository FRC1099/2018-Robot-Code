package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleDrive extends Command {

    public TeleDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Joysticks always return -1 when you push forward, so we use the negative value
    	double lstick = -OI.lstick.getRawAxis(1);
    	double rstick = -OI.rstick.getRawAxis(1);
    	
    	// Mr Dackow suggested a non-linear input
    	// The function is quadratic but the range and domain remain the same.
    	// The joy stick inputs go from -1 to 1 and this
    	// function produces values from -1 to 1.
    	// The effect is that we have better control a low speeds.
    	lstick = Math.signum(lstick) * lstick * lstick;
    	rstick = Math.signum(rstick) * rstick * rstick;
    	
    	SmartDashboard.putNumber("Left Stick", lstick);
    	SmartDashboard.putNumber("Right Stick", rstick);
    	
    	Robot.drivetrain.vDrive(lstick, rstick);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
