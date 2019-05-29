package nl.ru.ai.robots;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Challenge2 {
	static EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor mD = new EV3LargeRegulatedMotor(MotorPort.D);
	static ColorSensor cs = new ColorSensor();
	
	public static void crossBridgeAndFindPillar() {
		GyroSensor gs = new GyroSensor();
		LineFollower lf = new LineFollower(mA, mD, cs);
		int x = 0;
		while (true) {
			lf.followLine();
			float[] angle = gs.gyroSample();
			if (x == 0 && angle[0] > 10)
				x = 1;
			if (x == 1 && angle[0] < 10) {
				// findPillar(lf);
				Sound.twoBeeps();
				break;
			}
		}
	}

	public static void findPillar(LineFollower lf) {
		UltrasonicSensor us = new UltrasonicSensor();
		ColorSensor cs = new ColorSensor();
		while (true) {
			lf.followLine();
			float[] color = cs.colorSample();
			float[] distance = us.ultrasonicSample();
			if (distance[0] == Float.POSITIVE_INFINITY) {
				searchPillar(us, distance);
			}
			if (color[0] == 2) {
				// followRightLine
			} else if (color[0] == 5) {
				// followLeftLine
			}
		}
	}

	private static void searchPillar(UltrasonicSensor us, float[] distance) {
		EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor mD = new EV3LargeRegulatedMotor(MotorPort.D);
		mA.setSpeed(250);
		mD.setSpeed(250);
		while (distance[0] == Float.POSITIVE_INFINITY) {
			mD.stop(true);
			Delay.msDelay(50);
			mD.forward();
			mA.stop(true);
			Delay.msDelay(50);
		}
		mA.forward();
	}
}
