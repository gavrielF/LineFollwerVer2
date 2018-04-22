package com.start;

import com.logger.*;

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

    	
//		leftMotor.setSpeed(900);
//		leftMotor.forward();
//		while(!Button.ENTER.isDown())
//		{
//					
//		}
//		leftMotor.stop();
//		rightMotor.setSpeed(900);
//		rightMotor.forward();
//		while(!Button.ENTER.isDown())
//		{
//					
//		}
//		rightMotor.stop();	
    	
    	


		
		
    	Logger.getInstance().logDebug("ffffffffffff");
    	Logger.getInstance().logDebug("ggg4");
    	Logger.getInstance().logDebug("ggg5");
    	Logger.getInstance().logDebug("ggg6");

    	Logger.getInstance().logDebug("ggg7");
    	Logger.getInstance().logDebug("gfggs" , 34);
    	Logger.getInstance().logDebug("gfgfgfggfgfg" , 34);
    	Logger.getInstance().logDebug("gfggs" , 34);
    	Logger.getInstance().logDebug("gfggs" , 34);
    	Logger.getInstance().logDebug("44444444444444444" , 34);
    	Logger.getInstance().logDebug("fffffffffffffffffffffffffffff3442");
    	Logger.getInstance().logDebug("fffffffffffffffffffffdsfdf");
    	Logger.getInstance().logDebug("fgrs" , 4563111);
    	
    	Logger.getInstance().write();
	}

}
