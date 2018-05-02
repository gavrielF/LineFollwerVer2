package controllers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;


//
//
//class workgoor1 implements BaseController
//{ 
//	private Motors motors = new Motors();
//	private Sensors sensor = new Sensors();
//	int light;
//	
//	public void run() 
//	{
//		light = sensor.getLightSensorVal();
//		if(light >= 35 && light <= 46)
//		{
//			motors.setPower(80, 80);
//		}
//		else if( light < 35 && light > 32)
//		{
//			motors.setPower(80, 40);
//		}
//		else if( light <= 32)
//		{
//			motors.setPower(50, 10);
//		}
//		else if( light > 46 && light < 49)
//		{
//			motors.setPower(40, 80);
//		}
//		else if( light >= 49)
//		{
//			motors.setPower(10, 50);
//		}
//			
//	}
//
//	@Override
//	public void finish()
//	{
//		// TODO Auto-generated method stub
//		
//	}
//}
//
//
//class workgood
//{ 
//	private Motors motors = new Motors();
//	private Sensors sensor = new Sensors();
//	int light;
//	int array[] = {32, 37, 39, 42, 44, 46, 48};
//	
//	public void run() 
//	{
//		light = sensor.getLightSensorVal();
//		
//		if( light <= array[0])
//		{
//			motors.setPower(50, 10);
//		}
//		
//		else if( light > array[0]  &&  light <  array[1])
//		{
//			motors.setPower(80, 40);
//		}
//		else if(light >= array[1] && light < array[2])
//		{
//			motors.setPower(80, 60);
//		}	
//		
//		else if(light >= array[2] && light <= array[3])
//		{
//			motors.setPower(80, 75);
//		}			
//		else if( light > array[3] && light < array[4])
//		{
//			motors.setPower(75, 80);
//		}
//		
//		else if( light >= array[4] && light <= array[5])
//		{
//			motors.setPower(60, 80);
//		}
//		else if( light > array[5] && light < array[6])
//		{
//			motors.setPower(40, 80);
//		}
//		
//		else if( light >=  array[6])
//		{
//			motors.setPower(10, 50);
//		}
//			
//	}
//}




	

