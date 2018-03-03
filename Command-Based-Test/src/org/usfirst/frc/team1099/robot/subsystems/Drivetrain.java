package org.usfirst.frc.team1099.robot.subsystems;

import org.usfirst.frc.team1099.robot.OI;
import org.usfirst.frc.team1099.robot.RobotMap;
import org.usfirst.frc.team1099.robot.commands.TeleDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	TalonSRX leftDrive = new TalonSRX(RobotMap.leftDriveMotor);
	TalonSRX rightDrive = new TalonSRX(RobotMap.rightDriveMotor);

	TalonSRX leftFollower; 
	TalonSRX rightFollower;
	
	public Drivetrain() {
		
		leftFollower = new TalonSRX(RobotMap.leftFollowerMotor);
		rightFollower = new TalonSRX(RobotMap.rightFollowerMotor);

		leftFollower.follow(leftDrive);
		rightFollower.follow(rightDrive);
		
		rightDrive.setInverted(true);
		rightFollower.setInverted(true);
		rightDrive.setSensorPhase(true);
		
	    leftDrive.setSensorPhase(true);
	    
		initMotor(leftDrive);
		initMotor(rightDrive);

	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleDrive());
    }
    
    public void posDrive(double leftPos, double rightPos) {
    	
    	updateDashboard();
		leftDrive.set(ControlMode.Position, leftPos);
		rightDrive.set(ControlMode.Position, rightPos);
		
    }
        
    public void vDrive(double left, double right) {
    	
    	updateDashboard();
    	
    	if (OI.rstick.getRawButton(1)) {
    		left /= 2;
    		right /= 2;
    	}
    	
		leftDrive.set(ControlMode.PercentOutput, left);
		rightDrive.set(ControlMode.PercentOutput, right);
		
		// leftDrive.set(ControlMode.Velocity, left * 4096);
		// rightDrive.set(ControlMode.Velocity, right * 4096);
    }
    
    public void velocityDrive(double left, double right) {
    	leftDrive.set(ControlMode.Velocity, left);
    	rightDrive.set(ControlMode.Velocity, right); 
    	updateDashboard();
    }
    
    public int getLeftEncoder() {
    	return leftDrive.getSelectedSensorPosition(0);
    }
    
    public int getRightEncoder() {
    	return rightDrive.getSelectedSensorPosition(0);
    }
    
    public void resetEncoders() {
    	leftDrive.setSelectedSensorPosition(0, 0, 0);
    	rightDrive.setSelectedSensorPosition(0, 0, 0);
    	updateDashboard();
    }

    public void updateDashboard(){
    	SmartDashboard.putNumber("Left Encoder:", getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder:" ,getRightEncoder());
    }
    
    
    private static final int kTimeoutMs = 10;
    
    private void initMotor(TalonSRX t) {
    	
    	t.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    	t.configNominalOutputForward(0, kTimeoutMs);
    	t.configNominalOutputReverse(0, kTimeoutMs);
    	t.configPeakOutputForward(1, kTimeoutMs);
    	t.configPeakOutputReverse(-1, kTimeoutMs);
    	
    	t.config_kP(0, 0.15, kTimeoutMs);
    	t.config_kI(0, 0.0, kTimeoutMs);
    	t.config_kD(0, 0.0, kTimeoutMs);
    	t.config_kF(0, 0.05, kTimeoutMs);
    	
    	t.config_IntegralZone(0, 100, kTimeoutMs);
    }
    

}

