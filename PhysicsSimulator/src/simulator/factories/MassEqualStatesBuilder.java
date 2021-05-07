package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{

	public MassEqualStatesBuilder() {
		key = "masseq";
		desc = "Mass equals state builder";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		return new MassEqualStates();
	}

}
