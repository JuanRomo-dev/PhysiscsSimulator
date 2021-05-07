package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	
	private Vector2D direction; 
	private double g; 
	
	public MovingTowardsFixedPoint(Vector2D direction, double aceleracion) {
		this.direction = new Vector2D(direction);
		this.g = aceleracion;
	}

	@Override
	public void apply(List<Body> bs) { //Se aplica una fuerza hacia un punto fijo c a todos los cuerpos
		for(Body b : bs) {
			b.addForce(direction.minus(b.position.direction().scale(g * b.mass)));
			b.aceleration = b.force.scale(1/b.mass);
		}
	}
	
	public String toString() { 
		return "Moving towards "+ direction +" with constant acceleration "+ g;
	}
}
