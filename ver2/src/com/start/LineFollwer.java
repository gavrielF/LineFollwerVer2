package com.start;

import com.logger.*;
import com.robot.Robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.RegulatedMotor;
import lejos.util.PilotProps;

public class LineFollwer
{

	public static void main(String[] args)
	{
		try 
		{	//https://www.youtube.com/watch?v=ODAGVeeDagk
			LCD.clear();
			LCD.drawString("Hello", 0, 1);
			Sound.beep();
			LCD.drawString("continue [ENTER] ?", 0, 3);
			Button.ENTER.waitForPress();

			Logger.getInstance().logDebug("main - start");
			Robot rob = new Robot();
//			calibrateColors(rob);
			rob.startTrackLine_ver2();
			
			// leftMotor.setSpeed(900);
			// leftMotor.forward();
			// while(!Button.ENTER.isDown())
			// {
			//
			// }
			// leftMotor.stop();
			// rightMotor.setSpeed(900);
			// rightMotor.forward();
			// while(!Button.ENTER.isDown())
			// {
			//
			// }
			// rightMotor.stop();

		}
		catch(Exception e)
		{
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		}
		finally
		{
			Logger.getInstance().write();
		}	
	}
	
	private static void waitForEnter()
	{
		try
		{
			Sound.beep();
			Button.ENTER.waitForPressAndRelease();
		}
		catch(Exception ex)
		{
		}
	}

	
	public static void calibrateColors(Robot rob )
	{
		
		LCD.clear();
		LCD.drawString("Calibrate colors", 0, 0);
		
		LCD.drawString("White [ENTER]", 0, 2);
		waitForEnter();
		int white = rob.getLightSensorVal();
		Sound.beepSequenceUp();
		
		LCD.drawString("Black [ENTER]", 0, 3);
		waitForEnter();
		int black =  rob.getLightSensorVal();
		Sound.beepSequenceUp();

		Sound.beep();
		LCD.drawString("Green [ENTER]", 0, 4);
		waitForEnter();
		int green = rob.getLightSensorVal();
		Sound.beepSequenceUp();

		// Listing colors by decreasing reflective values
		LCD.clear();
		LCD.drawString("Color codes", 0, 0);
		LCD.drawString("white = " + white, 0, 2);
		LCD.drawString("green = " + green, 0, 3);
		LCD.drawString("black = " + black, 0, 4);
		waitForEnter();
		
	//	cut_black = (black + green) / 2.0f;
	//	cut_green = (green + white) / 2.0f;
	//	cut_bw = (black + white) / 2.0f;
	}


}
