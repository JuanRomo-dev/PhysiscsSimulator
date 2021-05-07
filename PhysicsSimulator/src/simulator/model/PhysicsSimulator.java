package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	private double real_time;
	private ForceLaws law;
	private List<Body> bodies;
	private List<SimulatorObserver> observers;
	double current_time;
	
	public PhysicsSimulator(double time, ForceLaws law) throws IllegalArgumentException{
		if (time > 0)
			this.real_time = time;
		else
			throw new IllegalArgumentException();
		if(law != null)
			this.law = law;
		else
			throw new IllegalArgumentException();
		current_time = 0.0;
		bodies = new ArrayList<>();
		
	}
	
	public void advance() { //Se aplica un paso a la simulacion
		for (Body body : bodies) {
			body.resetForce(); 
		}
		law.apply(bodies);
		for(Body body : bodies) {
			body.move(real_time); //Se mueven todos los cuerpos con un determinado tiempo
		}
		current_time += real_time; //Se incrementa el tiempo actual
		for (SimulatorObserver o : observers) {
			o.onAdvance(bodies, current_time);
		}
	}
	
	public void addBody(Body b) { //Se añade un cuerpo al simulador
		if (!bodies.contains(b))
            bodies.add(b);
        else
            throw new IllegalArgumentException();
		for (SimulatorObserver o : observers) {
			o.onBodyAdded(bodies, b);
		}
	}
	
	public void reset() {
		bodies.clear();
		current_time = 0.0;
		for (SimulatorObserver o : observers) {
			o.onReset(bodies, current_time, real_time, null);
		}
	}
	
	public void setDeltaTime(double dt) {
		if(dt <= 0) {
			throw new IllegalArgumentException();
		}
		this.real_time = dt;
		
	}
	
	public void setForceLawsLaws(ForceLaws forceLaws) {
		if (forceLaws == null) {
			throw new IllegalArgumentException();
		}
		this.law = forceLaws;
	}
	
	public JSONObject getState() { //Devuelve un objeto json representado todo el estado del simulador
		JSONObject state = new JSONObject();
        JSONArray bodys = new JSONArray();

        for(int i = 0; i < bodies.size(); i++) {
        	bodys.put(bodies.get(i).getState());
        }
        
        state.put("time", current_time);
        state.put("bodies", bodys);
        return state;
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!observers.contains(o)) {
			observers.add(o);
		}
		o.onRegister(bodies, current_time, real_time, null); //lawDesc
	}
	
	public String toString() {
		return getState().toString();
	}
}