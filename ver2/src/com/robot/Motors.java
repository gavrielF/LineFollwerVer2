package com.robot;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class Motors
{
	private NXTMotor _rightMotor;
	private NXTMotor _leftMotor;
	
	public Motors()
	{
		_rightMotor = new NXTMotor(MotorPort.B);
		_leftMotor = new NXTMotor(MotorPort.C);
	}
	
	public void setPower(float right, float left)
	{
		_rightMotor.setPower((int)right);
		_leftMotor.setPower((int)left);

	}

}
