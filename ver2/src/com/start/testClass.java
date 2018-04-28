package com.start;

import lejos.util.Stopwatch;

import com.robot.Robot;
import com.robot.Sensors;
import com.robot.Utils;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class testClass
{

	public testClass()
	{
	}

	public static void watchTest()
	{
		Stopwatch watch = new Stopwatch();
		System.out.println("Testing Pilot...");
		System.out.println("Testing watchTest...");
		
		watch.reset();	
		Delay.msDelay(3000);
		System.out.println(watch.elapsed() + "ms"); // 1000, 1024, 1204ms
	
		watch.reset();
		Delay.msDelay(200);
		System.out.println(watch.elapsed() + "ms"); // 81, 22, 19, 21ms

		watch.reset();
		Delay.msDelay(5000);
	}
	
	public static void calibrateColors(Integer waite, Integer black ,Integer middle)
	{
		Sensors s = new Sensors();
		LCD.clear();
		LCD.drawString("Calibrate colors", 0, 0);
		
		LCD.drawString("White [ENTER]", 0, 2);
		Utils.waitForEnter();
		int white = s.getLightSensorVal();
		Sound.beepSequenceUp();
		
		LCD.drawString("Black [ENTER]", 0, 3);
		Utils.waitForEnter();
		int black1 = s.getLightSensorVal();
		Sound.beepSequenceUp();

		Sound.beep();
		LCD.drawString("Green [ENTER]", 0, 4);
		Utils.waitForEnter();
		int green = s.getLightSensorVal();
		Sound.beepSequenceUp();

		// Listing colors by decreasing reflective values
		LCD.clear();
		LCD.drawString("Color codes", 0, 0);
		LCD.drawString("white = " + white, 0, 2);
		LCD.drawString("green = " + green, 0, 3);
		LCD.drawString("black = " + black1, 0, 4);
		Utils.waitForEnter();
		
	//	cut_black = (black + green) / 2.0f;
	//	cut_green = (green + white) / 2.0f;
	//	cut_bw = (black + white) / 2.0f;
	}

}
