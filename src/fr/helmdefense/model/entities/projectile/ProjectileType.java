package fr.helmdefense.model.entities.projectile;

import fr.helmdefense.model.entities.EntityType;
import fr.helmdefense.model.entities.utils.EntityData;

public enum ProjectileType implements EntityType {
	ARROW,
	ROCKBALL,
	THROWING_AXE,
	SMALL_FIREBALL,
	LASER_BEAM;
	
	private EntityData data;
	
	@Override
	public void setData(EntityData data) {
		this.data = data;
	}

	@Override
	public EntityData getData() {
		return this.data;
	}
}