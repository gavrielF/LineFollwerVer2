package com.behavior;

import com.logger.Logger;
import com.robot.Robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class FollewLine implements Behavior
{
	private Robot _roby;
	private boolean suppress = false;
	
	static float cut_black = 31.0f;
	static float cut_white = 53.0f;
	static float cut_bw = 46;

	static int scanDir = 1;
	
	public FollewLine(Robot roby) 
	{
		_roby = roby;
		Logger.getInstance().logDebug("FollewLine ctor");
	}


	@Override
	public boolean takeControl() 
	{
		return true;		
	}

	@Override
	public void action() 
	{
		//Logger.getInstance().logDebug("FollewLine - action start");	
		
		boolean onTheLine = true;
		
		while (!suppress)
		{
			onTheLine = canSeeLine();
			if (onTheLine)
				_roby.getPilot().forward();
			else
			{
				_roby.getPilot().stop();      
				// set angle to 10 degrees, same direction as last successful sweep
				double scanAngle = scanDir*1.6; 
				// start scanning until we find BLACK
				while (!onTheLine)
				{
					// begin rotate robot but return immediately (
					_roby.getPilot().rotate(scanAngle, true);
					// loop until BLACK or scan complete
					while ((!onTheLine) && (_roby.getPilot().isMoving()))
					{
							onTheLine = canSeeLine();
					}
					if (!onTheLine)						
					{   // no luck here, increase angle & scan in other direction
						scanDir *= (-1);
						scanAngle = scanDir*Math.abs(1.6*scanAngle);
					}
				}
				// found BLACK so stop robot and end else part
				_roby.getPilot().stop();  
			}
		}

		suppress = false;
	//	Logger.getInstance().logDebug("FollewLine - action end");	
	}

	@Override
	public void suppress() 
	{		
		suppress = true;
	}
	
	private boolean canSeeLine()
	{
		int value = _roby.getLightSensorVal();
		Thread.yield();
		if (value < cut_bw)
		{
			//Logger.getInstance().logDebug("canSeeLine yes", value);	
			return true;
		}
		else
		{
		//	Logger.getInstance().logDebug("canSeeLine no",value);
			return false;
		}
	}
	
	

}
