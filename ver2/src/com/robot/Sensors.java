package com.robot;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Sensors
{

	public static final LightSensor _lightSensor = new LightSensor(SensorPort.S1);
	public static final UltrasonicSensor _sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	
	
	public static int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public static int getLightSensorVal_normal()
	{
		return _lightSensor.getNormalizedLightValue();
	}
	
	public static int getSonarVal()
	{
		return _sonarSensor.getDistance();
	}

}
