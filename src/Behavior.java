package nl.ru.ai.robots;

import lejos.hardware.device.MSC;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Behavior {

	EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
	EV3LargeRegulatedMotor mD = new EV3LargeRegulatedMotor(MotorPort.D);
	Claw claw = new Claw();
	
	
	public void happy() 
	{
		mA.backward();
		mD.backward();
		Delay.msDelay(500);
		mA.forward();
		mD.backward();
		Delay.msDelay(2300);
	}

	public void angry() 
	{
		mA.backward();
		mD.backward();
		Delay.msDelay(500);
		claw.angryGrab();
	}

}
