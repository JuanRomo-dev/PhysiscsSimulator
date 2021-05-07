package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		key = "nlug";
		desc = "Newton´s law of universal gravitation";
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject info) {
		double G = 6.67*Math.pow(10, -11); //Por defecto vale 6.67E-11
		if(info.has("G"))
			G = info.getDouble("G");
		return new NewtonUniversalGravitation(G);
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject state = new JSONObject();
        
        state.put("G", "G");
        return state;
	}
}
