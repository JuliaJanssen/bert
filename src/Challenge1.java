package nl.ru.ai.robots;

import java.util.Date;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;


public class Challenge1 {
	LineFollower lf;
	UltrasonicSensor us;
	GyroSensor gs;
	ColorSensor cs;
	float[] angle;
	float[] color;
	float[] distance;
	int turns = 0;
	int oldTurn = 0;
	float difference = 0;
	boolean pillarFound = false;
	long startTime;
	long elapsedTime = 0L;
	EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
	EV3LargeRegulatedMotor mD = new EV3LargeRegulatedMotor(MotorPort.D);
	
	public Challenge1() {
		cs = new ColorSensor();
		lf = new LineFollower(mA, mD, cs);
		gs = new GyroSensor();
		us = new UltrasonicSensor();
	}
	
	public void run() {
		while (!pillarFound) {
			lf.followLine();
			checkTurn();
			searchPillar();
//			if (turns == 5 && turns != oldTurn) {
//				lf.switchSide();
//				oldTurn = turns;
//			}
//			if (turns == 8 && turns != oldTurn) {
//				lf.switchSide();
//				oldTurn = turns;
//				searchPillar();
//			}
		}
//		color = cs.colorSample();
//		while (color[0] != 2.0) {
//			goToPillar();
//		}
		Sound.beepSequence();
		while (true) {
			lf.followLine();
		}
	}
	
	public void searchPillar() {
		while (!pillarFound) {
			startTime = System.currentTimeMillis();
			elapsedTime = 0;
			while (elapsedTime < 5*1000) {
				lf.followLine();
				elapsedTime = (new Date()).getTime() - startTime;
			}
			turn();
		}
		goToPillar();
	}
	
	private void goToPillar() {
		boolean onPillar = false;
		mA.setSpeed(200);
		mD.setSpeed(200);
		mA.forward();
		mD.forward();
		while (!onPillar) {
			color = cs.colorSample();
			if (color[0] == 2.0) {
				startTime = System.currentTimeMillis();
				while (elapsedTime < 0.1*1000) {
					elapsedTime = (new Date()).getTime() - startTime;
				}
				cs.colorSample();
				if (color[0] == 2.0)
					onPillar = true;
			}
			elapsedTime = 0;
		}
		System.out.println(color[0]);
		mA.stop(true);
		mD.stop(true);
	}

	public void turn() {
		angle = gs.gyroSample();
		float x = angle[0];
		distance = us.ultrasonicSample();
		mA.setSpeed(200);
		mD.setSpeed(200);
		mA.backward();
		mD.forward();
		Delay.msDelay(200);
		angle = gs.gyroSample();
		while (distance[0] > 0.5 && (angle[0]-x)%365 != 0) {
			distance = us.ultrasonicSample();
			angle = gs.gyroSample();
		}
		mA.stop(true);
		mD.stop(true);
		if (distance[0] < 0.5) {
			pillarFound = true;		
			Sound.beepSequenceUp();
		}
	}

	public void checkTurn() {
		angle = gs.gyroSample();
		if (angle[0] - difference > 85 || angle[0] - difference < -85) {
			turns++;		
			difference = angle[0];
		}
	}
}
