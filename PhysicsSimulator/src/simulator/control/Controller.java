package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {

	private PhysicsSimulator simulator;
	private Factory<Body> bodies;
	
	public Controller(PhysicsSimulator simulator, Factory<Body> bodies) {
		this.simulator = simulator;
		this.bodies = bodies;
	}
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray body = jsonInput.getJSONArray("bodies");
		for (int i = 0; i < body.length(); i++)
			simulator.addBody(bodies.createInstance(body.getJSONObject(i))); //Añadimos cada cuerpo leido al simulador
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws DistinctStatesException {
			JSONObject obj = null;
			JSONArray array = null;
			boolean isNotNull = false; //Variable para saber si expOut es null o no
			
			if(expOut != null) {
				 obj = new JSONObject (new JSONTokener(expOut));
				 array = obj.getJSONArray("states");
				 isNotNull = true;
			}
			
			PrintStream p = new PrintStream(out);
			p.println("{");
			p.println("\"states\": [");
			p.println(simulator.toString());
			simulator.advance();
			for (int i = 1; i <= n; ++i) { //Mostramos todos los estados del simulador durante n pasos
				p.print("," + simulator.toString() + "\n");
				if(isNotNull && !cmp.equal(array.getJSONObject(i), simulator.getState())) { //Lanzamos una excepcion si los cuerpos son distintos
					String excep = "In the step: " + i + " the state \n" + array.getJSONObject(i).toString() + "and\n" + simulator.getState().toString() + "\nare distinct\n";
					throw new DistinctStatesException(excep);
				}
				simulator.advance();
			}

			p.println("]");
			p.println("}");
	}
	
}
