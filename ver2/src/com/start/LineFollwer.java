package com.start;

import lejos.nxt.Button;
import lejos.robotics.RegulatedMotor;
import lejos.util.PilotProps;

public class LineFollwer {

	public static void main(String[] args) throws Exception
	{
		
		PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));

    	
		leftMotor.setSpeed(900);
		leftMotor.forward();
		while(!Button.ENTER.isDown())
		{
					
		}
		leftMotor.stop();
		rightMotor.setSpeed(900);
		rightMotor.forward();
		while(!Button.ENTER.isDown())
		{
					
		}
		rightMotor.stop();	
	}

}
