package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {
	
	List<Builder<T>> builderList;
	List<JSONObject> info;
	
	public BuilderBasedFactory(List<Builder<T>>builders) {
		this.builderList = builders;
		this.info = new ArrayList<JSONObject>();
	}

	@Override
	public T createInstance(JSONObject info) { //Crea los objetos de las clases del paquete simulator.model
		for (int i = 0; i < builderList.size(); i++) {
			if(builderList.get(i).createInstance(info) != null) {
				return builderList.get(i).createInstance(info);
			}
		}
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		for (Builder<T> builder : builderList) {
			info.add(builder.getBuilderInfo());
		}
		return info;
	}


}
