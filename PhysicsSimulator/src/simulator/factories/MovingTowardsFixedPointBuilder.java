package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder() {
		key = "mtfp";
		desc = "Moving towards a fixed point";
	}
	@Override
	protected ForceLaws createTheInstance(JSONObject info) throws IllegalArgumentException{
		Vector2D c = new Vector2D(0, 0);
			
		if(info.has("c")) {
			if(info.getJSONArray("c").length() != 2) //Lanzamos excepcion si los vectores de velocidad o posicion no tienen 2 posiciones
				throw new IllegalArgumentException();
			c = new Vector2D(info.getJSONArray("c").getDouble(0), info.getJSONArray("c").getDouble(1));
		}
		
		double g = 9.81; //Por defecto vale 9.81
		if(info.has("g"))
			g = info.getDouble("g");
			
		return new MovingTowardsFixedPoint(c, g);
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject state = new JSONObject();
        state.put("c", "c");
        state.put("g", "g");
        return state;
	}
}