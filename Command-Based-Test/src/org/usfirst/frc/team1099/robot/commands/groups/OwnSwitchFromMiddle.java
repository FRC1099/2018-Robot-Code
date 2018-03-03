package org.usfirst.frc.team1099.robot.commands.groups;

import org.usfirst.frc.team1099.robot.Robot;
import org.usfirst.frc.team1099.robot.commands.auto.AutoElevator;
import org.usfirst.frc.team1099.robot.commands.auto.AutoForward;
import org.usfirst.frc.team1099.robot.commands.auto.AutoGrabber;
import org.usfirst.frc.team1099.robot.commands.auto.AutoResetEncoders;
import org.usfirst.frc.team1099.robot.commands.auto.AutoTilt;
import org.usfirst.frc.team1099.robot.commands.auto.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class OwnSwitchFromMiddle extends CommandGroup {

    public OwnSwitchFromMiddle(int switchSide) {
        
    	addParallel(new AutoTilt(), 10);
    	
    	
    	addSequential(new AutoResetEncoders(), .1);
    	addSequential(new AutoTurn(AutoTurn.RIGHT, 22), 1.0);
    	
    	addSequential(new AutoElevator(.7), 2);
    	addSequential(new AutoElevator(0.0), .5);
    	
    	addSequential(new AutoResetEncoders(), .1);
    	addSequential(new AutoForward(66), 6);
    	
    	addSequential(new AutoResetEncoders(), .1);
    	addSequential(new AutoTurn(AutoTurn.LEFT, 40), 1.0);
    	
    	addSequential(new AutoResetEncoders(), .1);
    	addSequential(new AutoForward(12), 1);
    	
    	addSequential(new AutoGrabber(0), 1);
    	
    }
}
