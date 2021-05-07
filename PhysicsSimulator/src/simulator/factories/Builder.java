package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public abstract class Builder <T>{

	protected String key;
	protected String desc;
	
	public Builder() {
		
	}
	public T createInstance(JSONObject info) {
		T builder = null;
		if(key != null && key.equals(info.getString("type")))
			builder = createTheInstance(info.getJSONObject("data"));
		return builder;
	}
	
	protected abstract T createTheInstance(JSONObject jsonObject);
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", key);
		info.put("desc", desc);
		info.put("data", createData());
		
		return info;
	}
	
	protected JSONObject createData() {
		return new JSONObject();
	}
}
