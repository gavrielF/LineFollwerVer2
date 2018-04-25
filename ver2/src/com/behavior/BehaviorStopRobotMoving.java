package com.behavior;

import com.logger.Logger;
import com.robot.Robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class BehaviorStopRobotMoving implements Behavior
{
	private Robot _roby;
	
	public BehaviorStopRobotMoving(Robot roby)
	{
		Logger.getInstance().logDebug("BehaviorStopRobotMoving ctor");
		_roby = roby;
	}
	
	@Override
	public boolean takeControl() 
	{
		return _roby.getSonarVal() < 20;		
	}

	@Override
	public void action() 
	{
		LCD.clear();
		LCD.drawString("bye bye - press enter", 0, 0);
		Button.waitForAnyPress();
		System.exit(0);		
	}

	@Override
	public void suppress() 
	{		
		
	}
	//http://thetechnicgear.com/2014/03/howto-create-line-following-robot-using-mindstorms/
	//https://github.com/jvia/nxt/blob/master/src/util/LineFollower.java
}
