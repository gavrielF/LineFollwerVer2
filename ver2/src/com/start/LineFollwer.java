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
	
	private static int i = 0;
	
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
				i = i + 1;
				showSelection(i);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});
		
		Button.LEFT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonReleased(Button b) {
				i = i - 1;
				showSelection(i);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});	
			
		showSelection(0);
		
		while (!Button.ENTER.isDown()) { }
		
		LCD.clear();
		LCD.drawString("Start..", 0, 2);
	}
	
	private static void showSelection(int i)
	{
		i = i%4; // 0 or 1 the number of controller we want
		if (i<0) 
			i += 4;
		
		LCD.clear();
		LCD.drawString("noPid", 2, 2);
		LCD.drawString("noPid-WithOdey", 2, 3);
		LCD.drawString("PIDController", 2, 4);		
		LCD.drawString("PID-WithOdey", 2, 5);
		LCD.drawString("{Enter to start}", 0, 6);
		
		LCD.drawString("->", 0, i+2);
		
		switch (i)
		{
			case 0:
				contruller = _factoryControlls.getShape("noPID" ,s_low, s_high);
				break;
			case 1:
				contruller = _factoryControlls.getShape("noPID-odey" ,s_low, s_high);
				break;
			case 2:
				contruller = _factoryControlls.getShape("PID", s_low, s_high);
				break;
			case 3:
				contruller = _factoryControlls.getShape("PID-odey", s_low, s_high);
				break;
			default:
				LCD.drawString("error", 0, 4);
				break;
		}
	}

}

