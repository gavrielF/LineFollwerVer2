package com.behavior;

import com.robot.Robot;

import lejos.robotics.subsumption.Behavior;


public class BehaviorMoveOnLine implements Behavior
{
	private Robot _roby;
	
	public BehaviorMoveOnLine(Robot roby)
	{
		_roby = roby;
	}
		
	
	@Override
	public boolean takeControl() 
	{	
		return _roby.getLightSensorVal() <=42;	
	}

	@Override
	public void action() 
	{
//		_roby.moveforward();
//        while(_roby.getLightSensorVal() <= 42) Thread.yield(); //action complete when not on line
//		
	}

	@Override
	public void suppress() 
	{
		//_roby.stopMove();
		
	}

}
