package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.IntakeControl;
import static org.usfirst.frc.team1099.robot.commands.IntakeCmd.USER;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    TalonSRX leftIntakeMotor = new TalonSRX(RobotMap.leftIntake);
    TalonSRX rightIntakeMotor = new TalonSRX(RobotMap.rightIntake);
    
    Victor actuator = new Victor(RobotMap.intakeTilt);
    
    DigitalInput lowerSwitch = new DigitalInput(0);
    
    public Intake() {      
        
    }
    
    public void tilt(double speed){
    	actuator.set(speed);
    }
    
    public void spinInward(double speed) {
    	spin(-speed, speed);
    }
    
    public void spinOutward(double speed) {
    	spin(speed, -speed);
    }
    
    public void stop() {
    	spin(0.0, 0.0);
    }
    
    public void spin(double left, double right) {
    	leftIntakeMotor.set(ControlMode.PercentOutput, left);
    	rightIntakeMotor.set(ControlMode.PercentOutput, right);
    }
    
    public double getLeftIntakeAmps() {
    	double outAmpsLeft = leftIntakeMotor.getOutputCurrent();
    	return outAmpsLeft;
    }
    
    public double getRightIntakeAmps() {
    	double outAmpsRight = rightIntakeMotor.getOutputCurrent();
    	return outAmpsRight;
    }   
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeControl(USER));
    }
    
}

