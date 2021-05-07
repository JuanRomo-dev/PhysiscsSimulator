package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class EpsilonEqualStates implements StateComparator{
	
	private double eps;

	public EpsilonEqualStates(double eps) {
		super();
		this.eps = eps;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean equals = true;
		
		JSONArray l1, l2;
		l1 = s1.getJSONArray("bodies");
		l2 = s2.getJSONArray("bodies");
		
		if(s1.getDouble("time") != s2.getDouble("time") || l1.length() != l2.length()) //Comprobamos las claves time y la longitud
			equals = false;
		int i = 0;
		JSONObject obj1, obj2;
		while(i<l1.length() && equals) {
			obj1 = l1.getJSONObject(i);
			obj2 = l2.getJSONObject(i);
			if(equals) { //Comprobamos que las claves m, p, y f son iguales modulo epsilon
				Vector2D v1 = new Vector2D(obj1.getJSONArray("v").getDouble(0), obj1.getJSONArray("v").getDouble(1));
				Vector2D v2 = new Vector2D(obj2.getJSONArray("v").getDouble(0), obj2.getJSONArray("v").getDouble(1));
				Vector2D p1 = new Vector2D(obj1.getJSONArray("p").getDouble(0), obj1.getJSONArray("p").getDouble(1));
				Vector2D p2 = new Vector2D(obj2.getJSONArray("p").getDouble(0), obj2.getJSONArray("p").getDouble(1));
				Vector2D f1 = new Vector2D(obj1.getJSONArray("f").getDouble(0), obj1.getJSONArray("f").getDouble(1));
				Vector2D f2 = new Vector2D(obj2.getJSONArray("f").getDouble(0), obj2.getJSONArray("f").getDouble(1));
				if(Math.abs(obj1.getDouble("m") - obj2.getDouble("m")) > eps || v1.distanceTo(v2) > eps || p1.distanceTo(p2) > eps || 
						f1.distanceTo(f2) > eps || !l1.getJSONObject(i).getString("id").equals(l2.getJSONObject(i).getString("id")))
					equals = false;
			}
            i++;
		}
		return equals;
	}

}
