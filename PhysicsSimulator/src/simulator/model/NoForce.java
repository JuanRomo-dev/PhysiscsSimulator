package simulator.model;

import java.util.List;

public class NoForce implements ForceLaws {
	

	@Override
	public void apply(List<Body> bs) { //Al no aplicar ninguna fuerza los cuerpos no se mueven (no hace nada la clase)
		
	}
	
	public String toString() {
		return "No Force";
	}
}