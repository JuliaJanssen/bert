package nl.ru.ai.robots;

import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;

public class GyroSensor {
	EV3GyroSensor gyroSensor;
	SampleProvider gyroProvider;
	float[] gyroSample;
	
	public GyroSensor() {
		gyroSensor = new EV3GyroSensor(SensorPort.S1);
		gyroProvider = gyroSensor.getAngleMode();
		gyroSample = new float[gyroProvider.sampleSize()];
	}
	
	public float[] gyroSample() {
		gyroProvider.fetchSample(gyroSample, 0);
		return gyroSample;
	}
}
