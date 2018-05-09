package com.start;

import com.logger.Logger;
import com.robot.Sensors;
import com.robot.Utils;

import controllers.BaseController;
import controllers.controllers;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

public class LineFollwer 
{

	public static BaseController contruller = null;
	public static controllers _factoryControlls = new controllers();
	
	
	public static int s_low = 0;
	public static int s_high = 0;
	
	public static void main(String[] args) 
	{
		try
		{		
			//get low and high
			getHighAndLow();
			//select the controler
			showOptions();	
			
			//while loog 		
			while (!Button.ESCAPE.isDown() && Sensors.getSonarVal() > 20)
				contruller.run();
			
			//finish the controller task
			contruller.finish();
		} 
		catch (Exception e) 
		{
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		} 
		finally
		{
			Logger.getInstance().write();
		}
	}
	
	private static void getHighAndLow()
	{
		LCD.clear();
		LCD.drawString("cali low", 0, 2);
		Utils.waitForEnter();
		s_low = new Integer(Sensors._lightSensor.getLightValue());
		LCD.drawString("cali high", 0, 3);
		Utils.waitForEnter();
		s_high = new Integer(Sensors._lightSensor.getLightValue());
		LCD.drawString("l:" + s_low + ", h:" + s_high, 0, 4);	
		LCD.drawString("Next..", 0, 5);
		Utils.waitForEnter();			
	}

	private static void showOptions()
	{
		Button.RIGHT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonReleased(Button b) {
				LCD.clear();
				LCD.drawString("noPid", 0, 2);
				LCD.drawString("-->PIDController", 0, 3);		
				LCD.drawString("Enter to start", 0, 4);
				contruller = _factoryControlls.getShape("PID", s_low, s_high);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});
		
		Button.LEFT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonReleased(Button b) {
				LCD.clear();
				LCD.drawString("-->noPid", 0, 2);
				LCD.drawString("PIDController", 0, 3);		
				LCD.drawString("Enter to start", 0, 4);
				contruller = _factoryControlls.getShape("noPID" ,s_low, s_high);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});
		
		//default
		contruller = _factoryControlls.getShape("noPID" ,s_low, s_high);
	
		LCD.clear();
		LCD.drawString("-->noPid", 0, 2);
		LCD.drawString("PIDController", 0, 3);		
		LCD.drawString("Enter to start", 0, 4);
		
		while (!Button.ENTER.isDown()) 
		{
			
		}
		LCD.clear();
		LCD.drawString("Start..", 0, 2);
	}

}

