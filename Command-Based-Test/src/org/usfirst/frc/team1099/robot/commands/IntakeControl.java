package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team1099.robot.commands.IntakeCmd.*;

/**
 *
 */
public class IntakeControl extends Command {

	IntakeCmd command;
	private double leftUserControl = 0.0;
	private double rightUserControl = 0.0;
	
	public IntakeControl() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }
	
    public IntakeControl(IntakeCmd cmd) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
        command = cmd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
      	switch (command) {
    	case IN:
    		Robot.intake.spinInward(.7);
    		break;
    	case OUT:
    		Robot.intake.spinOutward(.5);
    		break;
    	case STOP:
    		Robot.intake.stop();
    		break;
    	case USER:
    		
    		leftUserControl = OI.gamepad.getRawAxis(2);
    		rightUserControl = OI.gamepad.getRawAxis(3);
    		
    		if ( leftUserControl > .10) {
    			Robot.intake.spin(-leftUserControl, leftUserControl);
    		} else if ( rightUserControl > .10){
    			Robot.intake.spin(rightUserControl, -rightUserControl);
    		} else {
    			Robot.intake.stop();
    		}
    		
    		break;
      	}
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
