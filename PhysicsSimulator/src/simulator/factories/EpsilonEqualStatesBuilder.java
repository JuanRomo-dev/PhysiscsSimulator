package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator> {

	public EpsilonEqualStatesBuilder() {
		key = "epseq";
		desc = "Epsilon equal states builder";
	}

	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		double eps = 0.0; //Por defecto vale 0.0
		if(info.has("eps"))
			eps = info.getDouble("eps");
		
		return new EpsilonEqualStates(eps);
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("eps", "eps");
		return info;
	}

}
