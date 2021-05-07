package simulator.factories;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	public MassLosingBodyBuilder() {
		key = "mlb";
		desc = "Mass losing body builder";
	}

	@Override
	protected Body createTheInstance(JSONObject info) throws IllegalArgumentException{
		if(info.getJSONArray("v").length() != 2 || info.getJSONArray("p").length() != 2) //Lanzamos excepcion si los vectores de velocidad o posicion no tienen 2 posiciones
			throw new IllegalArgumentException();
		
		String id = info.getString("id"); //Nos guardamos el id
			
		JSONArray pos = info.getJSONArray("p");
		JSONArray vel = info.getJSONArray("v");
			
		double[] posC = new double[2]; //Creamos dos arrays de tipo double para almacenar las dos posiciones de la posicion y de la velocidad
		double[] velC = new double[2];
		for(int i = 0; i < 2; i++) { //Guardamos las dos posiciones
		posC[i] = pos.getDouble(i);
			}
		for(int i = 0; i < 2; i++) {
			velC[i] = vel.getDouble(i);
		}
			
		double mass = info.getDouble("m"); //Nos guardamos la masa
		Vector2D posF= new Vector2D(posC[0], posC[1]); //Creamos los vectores velociadad y posicion y les asignamos las posiciones de los vectores calculados anteriormente
		Vector2D velF= new Vector2D(velC[0], velC[1]);

		double lossFrequency = info.getDouble("freq"); //Creamos dos variables en las que nos guardamos la frecuencia y el factor
		double lossFactor = info.getDouble("factor");
			
		return new MassLossingBody(id, velF, posF, mass, lossFrequency, lossFactor);
	}

	
	@Override
	protected JSONObject createData() {
		JSONObject state = new JSONObject();
        
        state.put("id", "id");
        state.put("p", "position");
        state.put("v", "velocity");
        state.put("m", "m");
        state.put("freq", "freq");
        state.put("factor", "factor");

        return state;
	}
	
}