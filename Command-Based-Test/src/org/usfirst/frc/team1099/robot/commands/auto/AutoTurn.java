package org.usfirst.frc.team1099.robot.commands.auto;

import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int LSPIN = 2;
	public static final int RSPIN = 3;

	private int direction;
	private int position;

	private int leftStartPos = 0;
	private int rightStartPos = 0;

	private int leftTarget = 0;
	private int rightTarget = 0;

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
		leftStartPos = Robot.drivetrain.getLeftEncoder();
		rightStartPos = Robot.drivetrain.getRightEncoder();

		leftTarget = leftStartPos;
		rightTarget = rightStartPos;

		switch (direction) {
		case RIGHT:
			leftTarget += position;
			break;
		case LEFT:
			rightTarget += position;
			break;
		case RSPIN:
			leftTarget = leftStartPos + position / 2;
			rightTarget = rightStartPos - position / 2;
			break;
		case LSPIN:
			leftTarget = leftStartPos - position / 2;
			rightTarget = rightStartPos + position / 2;
			break;
		default:
			leftTarget = leftStartPos;
			rightTarget = rightStartPos;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.drivetrain.posDrive(leftTarget, rightTarget);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		double error = 0;

		if (direction == RIGHT || direction == RSPIN) {
			// turning to the right, the left wheel is driving
			error = Robot.drivetrain.getLeftEncoder() - leftStartPos;
		} else {
			error = Robot.drivetrain.getRightEncoder() - rightStartPos;
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
