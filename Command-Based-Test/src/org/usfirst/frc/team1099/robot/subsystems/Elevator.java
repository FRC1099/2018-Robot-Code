package org.usfirst.frc.team1099.robot.subsystems;

import static org.usfirst.frc.team1099.robot.commands.ElevatorCmd.*;

import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.ElevatorControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	   
	TalonSRX motor = new TalonSRX(RobotMap.elevatorMotor);
	private int kTimeoutMs = 10;
	
	public void initDefaultCommand() {
		
		// config the motor
		motor.configNominalOutputForward(0, kTimeoutMs);
		motor.configNominalOutputReverse(0, kTimeoutMs);
		motor.configPeakOutputForward(1, kTimeoutMs);
		motor.configPeakOutputReverse(-1, kTimeoutMs);
		
		
		setDefaultCommand(new ElevatorControl(USER));
		resetEncoder();
	}

	public void move(double speed) {
		motor.set(ControlMode.PercentOutput, speed);
		updateDashboard();
	}
	
	public double getElevatorEncoder() {
    	return motor.getSelectedSensorPosition(0);
    }
    
    public double getEncoder() {
    	return motor.getSelectedSensorPosition(0);
    }
    
    public void resetEncoder() {
    	motor.setSelectedSensorPosition(0,0,0);
    } 
    
    public void updateDashboard (){
    	SmartDashboard.putNumber("Elevator Encoder", getElevatorEncoder());
    	SmartDashboard.putNumber("Elevator Amps", motor.getOutputCurrent());
    	
    	SmartDashboard.putBoolean("Elevator Warning", (motor.getOutputCurrent() < 40));
    }
    
}
