package nl.ru.ai.robots;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {
	EV3ColorSensor colorSensor;
	SampleProvider redProvider;
	SampleProvider colorProvider;
	float[] redSample;
	float[] colorSample;
	
	public ColorSensor() {
		colorSensor = new EV3ColorSensor(SensorPort.S2);
		redProvider = colorSensor.getRedMode();
		redSample = new float[redProvider.sampleSize()];
		colorProvider = colorSensor.getColorIDMode();
		colorSample = new float[colorProvider.sampleSize()];
	}
	
	public float[] redSample() {
		redProvider.fetchSample(redSample, 0);
		return redSample;
	}
	
	public float[] colorSample() {
		colorProvider.fetchSample(colorSample, 0);
		return colorSample;
	}
}
