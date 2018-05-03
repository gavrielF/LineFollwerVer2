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

public class LineFollwer {

	public static BaseController contruller = null;

	public static void main(String[] args) 
	{
		try
		{
			LCD.clear();
			LCD.drawString("cali low", 0, 2);
			Utils.waitForEnter();
			int low = Sensors._lightSensor.getLightValue();
			LCD.drawString("cali high", 0, 3);
			Utils.waitForEnter();
			int high = Sensors._lightSensor.getLightValue();
			LCD.drawString("l:" + low + " h:" + high, 0, 4);
			
			LCD.drawString("Next..", 0, 5);
			Utils.waitForEnter();		
			
			showOptions(low, high);	
			
			// contruller = new lightMajer();
			// contruller = new Calibration(1);	
		

			while (!Button.ESCAPE.isDown() && Sensors.getSonarVal() > 20) {
				contruller.run();
			}

			contruller.finish();

		} catch (Exception e) {
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		} finally {
			Logger.getInstance().write();
		}
	}
	
	private static void showOptions(int low, int high)
	{
		Button.RIGHT.addButtonListener(new ButtonListener() {
			@Override
			public void buttonReleased(Button b) {
				LCD.clear();
				LCD.drawString("noPid", 0, 2);
				LCD.drawString("-->PIDController", 0, 3);		
				LCD.drawString("Enter to start", 0, 4);
				contruller = new PIDController(low, high);
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
				contruller = new noPid(low, high);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});
		
		//default
		contruller = new noPid(low, high);
	
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

// ======================================================================
// ======================================================================
interface BaseController {
	void run();

	void finish();
}

// ======================================================================
// ======================================================================
class lightMajer implements BaseController {
	float sensorData;

	public void run() {
		LCD.clear();
		sensorData = Sensors.getLightSensorVal();
		LCD.drawString("majer: " + sensorData, 0, 2);
		Utils.waitForEnter();
	}

	@Override
	public void finish() {

	}
}

// ======================================================================
// ======================================================================
class noPid implements BaseController {
	private Motors motors = new Motors();

	private int _light;
	private int _error;
	private int _black, _white;
	private int _middle;

	int array[] = { 30, 32, 33, 39, 42, 44, 45 };

	public noPid(int black, int white) {
		_black = black;
		_white = white;
		_middle = (_black + _white) / 2;
	}

	public void run() {
		_light = Sensors.getLightSensorVal();
		_error = _middle - _light;

		if (_light <= _black + 1) {
			motors.setPower(10, 50);
		} else if (_light >= _white - 1) {
			motors.setPower(50, 10);

		} else {
			motors.setPower(70 - 1.6f * _error, 70 + 1.6f * _error);
		}
	}

	@Override
	public void finish() {
		motors.setPower(0, 0);
	}
}

// ======================================================================
// ======================================================================
class PIDController implements BaseController {
	double leftSpeed, rightSpeed;
	double integral = 0;

	float sensorData;
	double middle = 0;
	double kp = 0;
	double ki = 0;
	double kd = 0;
	double turn = 0;
	double error = 0;

	double lastError = 0;
	double derivative = 0;

	int tp = 70;

	private Motors motors = new Motors();

	public PIDController(int black, int white) {
		middle = (white + black) / 2;

		double Kc = 250;
		double pc = 0.1;
		double dt = 0.020;

		kp = (0.67) * (Kc);

		ki = (2 * (kp) * (dt)) / (pc);

		kd = ((kp) * (pc)) / ((8) * (dt));
	}

	public void run() {
		sensorData = Sensors._lightSensor.getLightValue();

		if ((middle - sensorData) == middle - sensorData || (error > 0 && (middle - sensorData) < 0)
				|| (error < 0 && (middle - sensorData) > 0))
			integral = 0;

		error = middle - sensorData;

		integral = integral + error;

		derivative = error - lastError;

		turn = (kp * error) + (ki * integral) + (kd * derivative);
		turn = turn / 100;

		leftSpeed = tp - turn;
		rightSpeed = tp + turn;

		motors.setPower(leftSpeed, rightSpeed);

		lastError = error;
	}

	@Override
	public void finish() {
		motors.setPower(0, 0);
	}
}

// ======================================================================
// ======================================================================
class Calibration implements BaseController {
	private Motors motors = new Motors();
	private List<Integer> _list = new ArrayList<Integer>();
	final String fileName = "temp.txt";
	int _kind;

	public Calibration(int kind) {
		_kind = kind;
	}

	public void run() {

		if (_list.size() > 3000) {
			Sound.beep();
			motors.setPower(0, 0);
			return;
		} else
			motors.setPower(15, 15);

		if (_kind == 1)
			_list.add(Sensors.getLightSensorVal());
		else
			_list.add(Sensors.getLightSensorVal2());
	}

	@Override
	public void finish() {
		motors.setPower(0, 0);
		printToFile();
	}

	public void printToFile() {
		FileOutputStream fileStream = null;
		try {
			fileStream = new FileOutputStream(new File("TestCali42.txt"));
		} catch (Exception e) {
			LCD.drawString("Can't make a file", 0, 0);
			System.exit(1);
		}

		DataOutputStream dataStream = new DataOutputStream(fileStream);

		for (int i = 0; i < _list.size(); i++) {
			try {
				dataStream.writeBytes(String.valueOf(_list.get(i)));
				dataStream.writeBytes(" ");
				fileStream.flush();
			} catch (IOException e) {
				LCD.drawString("Can't write to the file", 0, 1);
				System.exit(1);
			}
		}

		try {
			fileStream.close();
		} catch (IOException e) {
			LCD.drawString("Can't save the file", 0, 1);
			System.exit(1);
		}
	}
}
