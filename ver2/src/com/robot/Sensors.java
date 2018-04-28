package com.robot;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Sensors
{

	private final LightSensor _lightSensor;
	private final UltrasonicSensor _sonarSensor;
	
	public Sensors()
	{
		//SENSORS
		_lightSensor = new LightSensor(SensorPort.S1);
		_sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	}
	
	public int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public int getSonarVal()
	{
		return _sonarSensor.getDistance();
	}

}
