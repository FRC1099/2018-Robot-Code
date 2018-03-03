package org.usfirst.frc.team1099.robot.commands.auto;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	private int direction;
	private int position;
	
    public AutoTurn(int direction, int angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	this.direction = direction;
    	
    	// 1 inch of travel is 219 ticks
    	this.position = angle * AutoConfig.kTicksPerDegree;
    }

    public void setDirection(int direction) {
    	this.direction = direction;
    }
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if( direction == RIGHT ) {
    		// drive left wheel to turn right
    		Robot.drivetrain.posDrive(position, 0.0f);
    	} else {
    		Robot.drivetrain.posDrive(0.0f, position); 
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	double error = 0;
    	
    	if( direction == LEFT ) {
    		error =  Robot.drivetrain.getLeftEncoder() - position;
    	} else {
    		error = Robot.drivetrain.getRightEncoder() - position;
    	}
    	
    	return Math.abs(error) < AutoConfig.kThreshold;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
