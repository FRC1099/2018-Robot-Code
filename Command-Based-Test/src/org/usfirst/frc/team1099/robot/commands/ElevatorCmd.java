package org.usfirst.frc.team1099.robot.commands;

public enum ElevatorCmd {	
		UP,   //Does what it says.
		DOWN, //Go down, applying current, gravity assisted.
		STOP, //This applies no current to the motor
		
			   // Zero voltage on the CIM seems to hold effectively
		HOLD,  //TODO Implement this to apply enough current to hold the cube in place
		
		USER
		
}
