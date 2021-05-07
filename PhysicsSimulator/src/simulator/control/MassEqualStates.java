package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator{

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) { //Comparamos si dos estados son iguales
		boolean equals = true;
		
		JSONArray l1, l2;
		l1 = s1.getJSONArray("bodies"); 
		l2 = s2.getJSONArray("bodies");
		
		if(s1.getDouble("time") != s2.getDouble("time") || l1.length() != l2.length()) //Comprobamos las claves time y la longitud
			equals = false;
		int i = 0;
		while(i<l1.length() && equals) {
			if(l1.getJSONObject(i).getDouble("mass") != l2.getJSONObject(i).getDouble("mass") //Comprobamos las claves mass y id
					|| !l1.getJSONObject(i).getString("id").equals(l2.getJSONObject(i).getString("id")));
                equals = false;
            i++;
		}
		return equals;
	}

}
