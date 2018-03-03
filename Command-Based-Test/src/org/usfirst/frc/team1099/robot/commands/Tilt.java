package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Tilt extends Command {

	public static final double UP=-1;
	public static final double DOWN=1;
	private double direction =UP;
	public static final double STOP = 0;
    public Tilt(double direction) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intake);
    	this.direction= direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.tilt(direction);
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
