package org.usfirst.frc.team1099.robot.commands.auto;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoForward extends Command {

	private double position;
	private double leftPos;
	private double rightPos;
	
    public AutoForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }
    
    public AutoForward(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.position = AutoConfig.kTicksPerInch * distance;
    	SmartDashboard.putNumber("Stop Ticks", position);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPos = Robot.drivetrain.getLeftEncoder();
    	double rightPos = Robot.drivetrain.getRightEncoder();
    	
    	double lVelocity = .25;
    	double rVelocity = lVelocity;
    	
    	if (leftPos > rightPos) {
    		rVelocity *= 1.1;
    	}
    	
    	if (leftPos < rightPos) {
    		rVelocity *= .9;
    	}
    	
    	
    	// velocity units are ticks per 100ms?

    	Robot.drivetrain.vDrive(lVelocity, rVelocity);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	
    	// lets just check the left encoder
    	double leftPos = Robot.drivetrain.getLeftEncoder();
    	
    	// assume forward motion
        return leftPos > position; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
