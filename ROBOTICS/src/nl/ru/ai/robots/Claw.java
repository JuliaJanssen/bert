package nl.ru.ai.robots;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Claw
{
  EV3MediumRegulatedMotor mC;
  String status;
  
  
  public Claw()
  {
    this.mC = new EV3MediumRegulatedMotor(MotorPort.C);
  }
  
  public void letGo()
  {
    this.mC.backward();
    Delay.msDelay(3000);
  }

  public void grab()
  {
    this.mC.forward();
    Delay.msDelay(3000);
  }

  public void angryGrab()
  {
	this.mC.setSpeed(5000);
    this.mC.forward();
    Delay.msDelay(500);
    this.mC.backward();
    Delay.msDelay(1000);
    this.mC.forward();
    Delay.msDelay(300);
    this.mC.backward();
    Delay.msDelay(500);
  }
}
