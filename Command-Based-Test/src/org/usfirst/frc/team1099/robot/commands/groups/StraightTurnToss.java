package org.usfirst.frc.team1099.robot.commands.groups;

import org.usfirst.frc.team1099.robot.commands.auto.AutoElevator;
import org.usfirst.frc.team1099.robot.commands.auto.AutoForward;
import org.usfirst.frc.team1099.robot.commands.auto.AutoGrabber;
import org.usfirst.frc.team1099.robot.commands.auto.AutoTilt;
import org.usfirst.frc.team1099.robot.commands.auto.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightTurnToss extends CommandGroup {

    public StraightTurnToss() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new AutoElevator(.7), 2);
    	addSequential(new AutoElevator(0), .5);
    	addSequential(new AutoForward(66), 6);
    	addSequential(new AutoTurn(AutoTurn.RIGHT, 45), 1.0);
    	addSequential(new AutoTilt(), 1.25);
    	addSequential(new AutoGrabber(0), 1);
    }
}