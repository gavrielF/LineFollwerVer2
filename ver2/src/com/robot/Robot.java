package com.robot;

import com.behavior.BehaviorMoveOnLine;
import com.behavior.BehaviorRelocationOnLine;
import com.behavior.BehaviorStopRobotMoving;
import com.behavior.FollewLine;
import com.logger.Logger;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;

public class Robot
{

	private final DifferentialPilot _pilot;
	private final LightSensor _lightSensor;
	private final UltrasonicSensor _sonarSensor;
	
	private RegulatedMotor _rightMotor;
	private RegulatedMotor _leftMotor;
	
	public Robot() throws Exception
	{
		PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
    	_rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
    	_leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
	
		_pilot = new DifferentialPilot(wheelDiameter, trackWidth, _leftMotor, _rightMotor, reverse);
		_pilot.setTravelSpeed(20); // cm/sec
		_pilot.setAcceleration(10);

		//SENSORS
		_lightSensor = new LightSensor(SensorPort.S1);
		_sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	

	}
	
	
	public void startTrackLine()
	{
		BehaviorRelocationOnLine relocationOnLine = new BehaviorRelocationOnLine(this);
		BehaviorMoveOnLine moveOnLine = new BehaviorMoveOnLine(this);
		BehaviorStopRobotMoving stopRobotMoving = new BehaviorStopRobotMoving(this);
		
		Behavior[] bArray = {relocationOnLine, moveOnLine, stopRobotMoving};
	    (new Arbitrator(bArray)).start();
	}
	
	public void startTrackLine_ver2()
	{
		Logger.getInstance().logDebug("startTrackLine_ver2");
		FollewLine follewLine = new FollewLine(this);
		BehaviorStopRobotMoving stopRobotMoving = new BehaviorStopRobotMoving(this);
		
		Behavior[] bArray = {follewLine, stopRobotMoving};
	    (new Arbitrator(bArray, true)).start();
	    Logger.getInstance().logDebug("startTrackLine_ver2 end");
	}
	
	public int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public int getSonarVal()
	{
		return _sonarSensor.getDistance();
	}
	
	public DifferentialPilot getPilot()
	{
		return _pilot;
	}

}
