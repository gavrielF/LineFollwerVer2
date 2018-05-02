package com.start;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.logger.*;
import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class LineFollwer
{

	public static void main(String[] args)
	{
		try
		{
			LCD.clear();
			LCD.drawString("Hello", 0, 1);
			Sound.beep();

			Button.LEFT.addButtonListener(new ButtonListener() {

				@Override
				public void buttonReleased(Button b)
				{
					// TODO Auto-generated method stub
					LCD.drawString("->", 0, 3);
				}

				@Override
				public void buttonPressed(Button b)
				{
					// TODO Auto-generated method stub

				}
			});

			Utils.waitForEnter();

			//BaseController contruller = new lightMajer();
//			BaseController contruller = new regular();
			BaseController contruller = new regular2();
		//	BaseController contruller = new regularImprove();
		//	BaseController contruller = new Calibration(1);

			while (!Button.ESCAPE.isDown() && Sensors.getSonarVal() > 20)
			{
				contruller.run();
			}

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

}

// ======================================================================
// ======================================================================
interface BaseController
{
	void run();

	void finish();
}

// ======================================================================
// ======================================================================
class lightMajer implements BaseController
{
	float sensorData;

	public void run()
	{
		LCD.clear();
		sensorData = Sensors.getLightSensorVal();
		LCD.drawString("majer: " + sensorData, 0, 2);
		Utils.waitForEnter();
	}

	@Override
	public void finish()
	{

	}
}

// ======================================================================
// ======================================================================
class regular implements BaseController
{
	private Motors	motors	= new Motors();

	int				light;
	int				_middle	= 43;

	public regular()
	{
	}

	public regular(int middle)
	{
		_middle = middle;
	}

	public void run()
	{
		light = Sensors.getLightSensorVal();
		if (light > _middle)
		{
			motors.setPower(100, 50);
		} else
		{
			motors.setPower(50, 100);
		}
	}

	@Override
	public void finish()
	{
	}
}

// ======================================================================
// ======================================================================
class regular2 implements BaseController
{ 
	private Motors motors = new Motors();

	int light;
	int error;
	int array[] = {30, 31, 37, 40, 44, 46, 48};
	
	public void run() 
	{
		light = Sensors.getLightSensorVal();
		error = array[3] - light;
		
		if( light <= array[0])
		{
			motors.setPower(50, 10);
		}
		else if( light >=  array[6])
		{
			motors.setPower(10, 50);
		}		
		else
		{
			motors.setPower(70 + 1.6f*error , 70 - 1.6f*error);
		}								
	}

	@Override
	public void finish()
	{		
	}
}

// ======================================================================
// ======================================================================
class regularImprove implements BaseController
{ 
	private Motors motors = new Motors();
	int light;
	int array[] = {30, 31, 37, 40, 44, 46, 48};
	
	public void run() 
	{
		light = Sensors.getLightSensorVal();
		
		if( light <= array[0])
		{
			motors.setPower(50, 10);
		}
		
		else if( light > array[0]  &&  light <  array[1])
		{
			motors.setPower(80, 20);
		}
		else if(light >= array[1] && light < array[2])
		{
			motors.setPower(100, 70);
		}	
		
		//
		else if(light >= array[2] && light <= array[3])
		{
			motors.setPower(100, 95);
		}			
		else if( light > array[3] && light < array[4])
		{
			motors.setPower(95, 100);
		}
		
		else if( light >= array[4] && light <= array[5])
		{
			motors.setPower(70, 100);
		}
		else if( light > array[5] && light < array[6])
		{
			motors.setPower(20, 80);
		}
		
		else if( light >=  array[6])
		{
			motors.setPower(10, 50);
		}
			
	}

	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
		
	}
}

// ======================================================================
// ======================================================================
class regularImproveSlow implements BaseController
{ 
	private Motors motors = new Motors();
	
	int light;
	int array[] = {32, 37, 39, 42, 44, 46, 48};
	
	public void run() 
	{
		light = Sensors.getLightSensorVal();
		
		if( light <= array[0])
		{
			motors.setPower(50, 10);
		}
		
		else if( light > array[0]  &&  light <  array[1])
		{
			motors.setPower(70, 20);
		}
		else if(light >= array[1] && light < array[2])
		{
			motors.setPower(90, 70);
		}	
		
		//
		else if(light >= array[2] && light <= array[3])
		{
			motors.setPower(100, 95);
		}			
		else if( light > array[3] && light < array[4])
		{
			motors.setPower(95, 100);
		}
		
		else if( light >= array[4] && light <= array[5])
		{
			motors.setPower(70, 90);
		}
		else if( light > array[5] && light < array[6])
		{
			motors.setPower(20, 70);
		}
		
		else if( light >=  array[6])
		{
			motors.setPower(10, 50);
		}
			
	}

	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
		
	}
}


// ======================================================================
// ======================================================================
class PIDController implements BaseController
{ 
	float TARGET = 44;
	final float P_CONTROL = 2;
	final float I_CONTROL = 1;
	final float D_CONTROL = 1;
	final float BASE_SPEED = 30; 
	float leftSpeed, rightSpeed; 
	float sensorData;
	float integral = 0;
	// float[] lastErrs = {0, 0, 0};
	float lastErr = 0; 
	float deriv = 0; 
	
	private Motors motors = new Motors();

	public PIDController(int mi)
	{
		TARGET = (float)mi;
	}
	
	public void run() 
	{
		sensorData = Sensors.getLightSensorVal();
		
		float err = TARGET - sensorData; 
		
		leftSpeed = 100 + P_CONTROL * err; 
		rightSpeed = 100 - P_CONTROL * err; 
		
		motors.setPower(leftSpeed, rightSpeed);
	}

	@Override
	public void finish()
	{
		// TODO Auto-generated method stub
		
	}
}
	

// ======================================================================
// ======================================================================

// ======================================================================
// ======================================================================

// ======================================================================
// ======================================================================

// ======================================================================
// ======================================================================
class Calibration implements BaseController
{
	private Motors			motors		= new Motors();
	private List<Integer>	_list		= new ArrayList<Integer>();
	final String			fileName	= "temp.txt";
	int						_kind;

	public Calibration(int kind)
	{
		_kind = kind;
	}

	public void run()
	{

		if (_list.size() > 3000)
		{
			Sound.beep();
			motors.setPower(0, 0);
			return;
		} else
			motors.setPower(20, 20);

		if (_kind == 1)
			_list.add(Sensors.getLightSensorVal());
		else
			_list.add(Sensors.getLightSensorVal2());
	}

	@Override
	public void finish()
	{
		motors.setPower(0, 0);
		printToFile();
	}
	
	public void printToFile()
	{
		FileOutputStream fileStream = null;
		try
		{
			fileStream = new FileOutputStream(new File("TestCali42.txt"));
		}
		catch (Exception e)
		{
			LCD.drawString("Can't make a file", 0, 0);
			System.exit(1);
		}

		DataOutputStream dataStream = new DataOutputStream(fileStream);

		for (int i = 0; i < _list.size(); i++)
		{
			try
			{
				dataStream.writeBytes(String.valueOf(_list.get(i)));
				dataStream.writeBytes(" ");
				fileStream.flush();
			}
			catch (IOException e)
			{
				LCD.drawString("Can't write to the file", 0, 1);
				System.exit(1);
			}
		}

		try
		{
			fileStream.close();
		}
		catch (IOException e)
		{
			LCD.drawString("Can't save the file", 0, 1);
			System.exit(1);
		}

		Button.waitForAnyPress();

	}
}
