package nl.ru.ai.robots;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class LineFollower {
	int x = 0;
	EV3LargeRegulatedMotor mA;
	EV3LargeRegulatedMotor mD;
	float offset;
	float whiteFloat;
	float blackFloat;
	float tp = 300f;
	float kp = 500f;
	float kd = 100f;
	float error = 0f;
	float turn = 0f;
	float mASpeed = 0f;
	float mDSpeed = 0f;
	float lastError = 0f;
	float derivative = 0f;
	float dT = 0.06613f;
	ColorSensor cs;

	public LineFollower(EV3LargeRegulatedMotor mA, EV3LargeRegulatedMotor mD, ColorSensor cs) {
		this.mA = mA;
		this.mD = mD;
		this.cs = cs;
		float[] white = cs.redSample();
		whiteFloat = white[0];
		Sound.twoBeeps();
		Button.ESCAPE.waitForPressAndRelease();
		float[] black = cs.redSample();
		blackFloat = black[0];
		Sound.twoBeeps();
		Button.ESCAPE.waitForPressAndRelease();
		
		offset = (whiteFloat + blackFloat) / 2;

	}

	public void followLine() {
		float[] lightValue = cs.redSample();
		error = lightValue[0] - this.offset;
		derivative = (error - lastError) / dT;
		turn = (kp * error) + (kd * derivative);
		if (lightValue[0] > whiteFloat - 0.001 || lightValue[0] < blackFloat + 0.01) {
			if (lightValue[0] > whiteFloat - 0.001) {
				if (x == 0)
					mASpeed = -150;
				else
					mDSpeed = -150;
			} 
			else if (lightValue[0] < blackFloat + 0.01) {
				if (x == 0)
					mDSpeed = -200;
				else
					mASpeed = -200;
			}
		} 
		else {
			if (x == 0) {
				mASpeed = tp - turn;
				mDSpeed = tp + turn;
			} 
			else {
				mASpeed = tp + turn;
				mDSpeed = tp - turn;
			}
		}
		mA.setSpeed(mASpeed);
		mD.setSpeed(mDSpeed);
		if (mASpeed > 0)
			mA.forward();
		else if (mASpeed < 50)
			mA.backward();

		if (mDSpeed > 0)
			mD.forward();
		else if (mDSpeed < 50)
			mD.backward();
		lastError = error;
	}
	
	public void switchSide() {
		if (x == 0) {
			mA.setSpeed(300);
			mD.setSpeed(200);
			x = 1;
			Delay.msDelay(300);
		}
		else {
			mD.setSpeed(300);
			mA.setSpeed(200);
			x = 0;
			Delay.msDelay(300);
		}
	}
}
