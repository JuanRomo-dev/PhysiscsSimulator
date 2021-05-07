package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body{

	private double lossFactor;
	private double lossFrequency;
	private double contador;
	
	public MassLossingBody(String id, Vector2D velocidad, Vector2D posicion, double masa, double lossFrequency, double lossFactor) {
		super(id, masa, velocidad, posicion);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
	}
	

	void move(double t) {
		super.move(t);
		contador += t;
		if(contador >= lossFrequency) { //Si el contador es mayor que lossFrequency, la masa se reduce
			contador = 0;
			mass = mass * (1 - lossFactor);
		}
	}
}
