package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation(double _G){
		G = _G;
	}
	
	@Override
	public void apply(List<Body> bs) { //bi y bj son dos cuerpos que aplican una fuerza gravitacional uno sobre el otro
		for (int i = 0; i < bs.size(); i++) {
			Body bi = bs.get(i);
			if(bi.mass == 0.0) { //Si la masa es 0, el vector velocidad es (0, 0)
				bi.velocity.scale(0); 
			}else {
				for (int j = 0; j < bs.size(); j++) {
					Body bj = bs.get(j);
					if(bj.mass != 0 && !bj.equals(bi)) {
						Vector2D Fij = new Vector2D(); //Fuerza aplicada del cuerpo bj sobre el cuerpo bi
						if(bj.position.distanceTo(bi.position) != 0) {
							double force = G * bi.getMass() * bj.getMass()/(bj.position.distanceTo(bi.position) * bj.position.distanceTo(bi.position)); //Calculamos el modulo fuerza a traves de la ley de la gravitacion universal de newton
							Fij = bj.position.minus(bi.position).direction().scale(force); //Calculamos el vector fuerza
						}
						bi.addForce(Fij); //Agregamos la nueva fuerza 
					}
				}
				bi.aceleration = bi.force.scale(1/bi.mass); //Con la aplicacion de la fuerza cambia la aceleracion usando la segunda ley de newton (F = m*a)
			}
		}		
	}
	
	public String toString() {
		return "Newton´s Universal Gravitation with G="+G;
	}
}