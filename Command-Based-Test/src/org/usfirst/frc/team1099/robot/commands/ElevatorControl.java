package org.usfirst.frc.team1099.robot.commands;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorControl extends Command {

	ElevatorCmd command;
	public ElevatorControl(){
		
	}
	
    public ElevatorControl(ElevatorCmd command) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.command = command;
       	requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }


    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (command) {
    	case DOWN:
    		Robot.elevator.move(-.5);
    		break;
    	case UP:
    		Robot.elevator.move(.7);
    		break;
    	case STOP:
    		Robot.elevator.move(0);
    		break;
    	case HOLD:
    		Robot.elevator.move(.2);
    		break;
    	case USER:
    		double elevatorSpeed = -OI.gamepad.getRawAxis(1);
    		
    		// we tried to config the motor output, but got odd results
    		
    		//if (elevatorSpeed > .9){
    		//	elevatorSpeed = .9;
    		//}
    		
    		if (elevatorSpeed < -0.5){
    			elevatorSpeed = -0.5;
    		}
    		
    		Robot.elevator.move(elevatorSpeed);
    		break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	//TODO make this return true when either up or down reaches the end.
    	if (command  == ElevatorCmd.STOP){
    		return true;
    	}
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
