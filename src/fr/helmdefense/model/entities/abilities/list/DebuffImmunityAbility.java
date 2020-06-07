package fr.helmdefense.model.entities.abilities.list;

import fr.helmdefense.model.actions.ActionHandler;
import fr.helmdefense.model.actions.entity.EntitySpawnAction;
import fr.helmdefense.model.actions.game.GameTickAction;
import fr.helmdefense.model.entities.abilities.Ability;
import fr.helmdefense.model.entities.living.LivingEntity;
import fr.helmdefense.model.entities.utils.Tier;
import fr.helmdefense.model.entities.utils.Tier.Specification;

public class DebuffImmunityAbility extends Ability {
	LivingEntity entity;
	
	public DebuffImmunityAbility(Tier unlock, Specification tierSpecification) {
		super(unlock, tierSpecification);
	}
	
	@ActionHandler
	public void onSpawn(EntitySpawnAction action) {
		this.entity = (LivingEntity) action.getEntity();
		
	}
	
	@ActionHandler
	public void disableDebuff(GameTickAction action) {
		if ( this.entity.getFlags() != 0 )
			this.entity.removeFlags(this.entity.getFlags());
	}
}
