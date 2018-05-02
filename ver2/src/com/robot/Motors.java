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
	
	public void setPower(float left, float right)
	{
		if(left < 0)
			_leftMotor.backward();
		else
			_leftMotor.forward();
		
		if(right < 0)
			_leftMotor.backward();
		else
			_leftMotor.forward();
		
		
		_leftMotor.setPower((int)(Math.abs(left)));
		_rightMotor.setPower((int)(Math.abs(right)));

	}

}
