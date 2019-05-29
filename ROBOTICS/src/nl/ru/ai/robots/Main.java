package nl.ru.ai.robots;

import java.io.File;

import javax.swing.plaf.synth.SynthSeparatorUI;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class Main {
	public static void main(String[] args) {
//		ColorSensor cs = new ColorSensor();
//		while (true) {
//			float[] color = cs.colorSample();
//			System.out.println(color[0]);
//		}
		
		Challenge1 c1 = new Challenge1();
		c1.run();
		//Behavior behavior = new Behavior();
		//behavior.happy();
		//behavior.angry();
		
		
		
		
//		Claw claw = new Claw();
//		claw.letGo();
//		claw.angryGrab();
	}
}