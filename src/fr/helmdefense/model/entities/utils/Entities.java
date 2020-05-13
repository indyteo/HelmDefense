package fr.helmdefense.model.entities.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import fr.helmdefense.model.entities.Entity;

public class Entities {
	
	private static Map<Class<? extends Entity>, EntityData> entityData = YAMLLoader.loadEntityData();
	
	private Entities() {}
	
	public static EntityData getData(Class<? extends Entity> type) {
		return entityData.get(type);
	}
	
	public static void registerData(Class<? extends Entity> type, EntityData data) {
		entityData.put(type, data);
	}
	
	public static Class<? extends Entity> getClass(String path) {
		return entityData.entrySet().stream()
				.filter(e -> e.getValue().getPath().equalsIgnoreCase(path))
				.;
	}
	
	
}
