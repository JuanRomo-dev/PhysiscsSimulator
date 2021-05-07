package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected double mass;
	protected Vector2D velocity; //velocidad
	protected Vector2D force; //fuerza
	protected Vector2D position; //posicion
	Vector2D aceleration;
	
	public Body(String id, double masa, Vector2D velocity, Vector2D position) {
		this.id = id;
		this.mass = masa;
		this.velocity = new Vector2D(velocity);
		this.force = new Vector2D();
		this.position = new Vector2D(position);
	}

	void addForce(Vector2D force) {
		this.force = this.force.plus(force); //Añade la fuerza f al vector fuerza del cuerpo 
	}
	
	void resetForce() {
		this.force = new Vector2D(); //Pone el valor del vector de fuerza a (0,0)
	}
	
	void move(double t) { //mueve el cuerpo durante t segundos
		if(mass != 0) { //Si la masa es distinta a 0 calculamos la posicion y la velocidad nuevas con las formulas del movimiento
			position = position.plus(velocity.scale(t).plus(aceleration.scale(0.5).scale(t*t)));
			velocity = velocity.plus(aceleration.scale(t));		
		}
		else
			aceleration = new Vector2D(); //Si la masa es 0, se pone a 0 la aceleracion
	}
	
	public boolean equals(Body b) {
		return (this.id.equals(b.id));
	}
	
	public JSONObject getState() { //Se crea el cuerpo en formato json
		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("m", mass);
		json.put("p", position.asJSONArray());
		json.put("v", velocity.asJSONArray());
		json.put("f", force.asJSONArray());
		
		return json;
	}
	
	public String toString() {
		return getState().toString();
	}
	
	public String getId() {
		return id;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}
	
	public Vector2D getForce() {
		return force;
	}
	
	public Vector2D getPosition() {
		return position;
	}

	public double getMass() {
		return mass;
	}
	
}
