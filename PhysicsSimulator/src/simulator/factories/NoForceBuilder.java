package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		key = "ng";
		desc = "No force";
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject info) {
		return new NoForce();
	}
}