package com.start;

import com.logger.*;
import com.robot.Robot;
import com.robot.Utils;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.EncoderMotor;
import lejos.robotics.RegulatedMotor;
import lejos.util.PilotProps;
import lejos.util.Stopwatch;

public class LineFollwer
{

	public static void main(String[] args)
	{
		try 
		{
			LCD.clear();
			LCD.drawString("Hello", 0, 1);
			Sound.beep();
			LCD.drawString("continue [ENTER] ?", 0, 3);
			Utils.waitForEnter();
			Logger.getInstance().logDebug("main - start");
			
			testClass2 test = new testClass2();
			// the code
			while (!Button.ESCAPE.isDown())
			{
				test.run();			
			}

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
	
}

class testClass2 
{ 
	public void run() 
	{
		//testClass.watchTest();
	}
}
	
	
