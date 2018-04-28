package com.robot;

import lejos.nxt.Button;
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
			Button.ENTER.waitForPressAndRelease();
			Sound.beep();
		}
		catch(Exception ex)
		{
		}
	}
}
