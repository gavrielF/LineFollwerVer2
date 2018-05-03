package com.robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class Utils
{

	public Utils()
	{
		// TODO Auto-generated constructor stub
	}

	
	public static void waitForEnter()
	{
		try
		{
			//LCD.drawString("Wait for Enter ", 0, 4);	
			Button.ENTER.waitForPressAndRelease();
			Sound.beep();
		}
		catch(Exception ex)
		{
		}
	}
	
	public static void waitForLeft()
	{
		try
		{
			Button.LEFT.waitForPressAndRelease();
			Sound.beep();
		}
		catch(Exception ex)
		{
		}
	}
}

