package nl.ru.ai.robots;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltrasonicSensor {
	EV3UltrasonicSensor ultrasonicSensor;
	SampleProvider ultrasonicProvider;
	float[] ultrasonicSample;
	
	public UltrasonicSensor() {
		ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);
		ultrasonicProvider = ultrasonicSensor.getDistanceMode();
		ultrasonicSample = new float[ultrasonicProvider.sampleSize()];
	}
	
	public float[] ultrasonicSample() {
		ultrasonicProvider.fetchSample(ultrasonicSample, 0);
		return ultrasonicSample;
	}
}