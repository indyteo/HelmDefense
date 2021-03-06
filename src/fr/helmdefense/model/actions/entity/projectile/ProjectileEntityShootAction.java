package fr.helmdefense.model.actions.entity.projectile;

import fr.helmdefense.model.actions.ActionHandler;
import fr.helmdefense.model.entities.living.LivingEntity;
import fr.helmdefense.model.entities.projectile.Projectile;

/**
 * Action triggered when a projectile is shot by an entity.
 * 
 * <p>Note: You can retrieve the shooter by doing
 * <blockquote><pre>@{@link ActionHandler}
 *public void onProjectileEntityShoot({@link ProjectileEntityShootAction} action) {
 *	{@link LivingEntity} shooter = action.{@link #getEntity() getEntity}().{@link Projectile#getSource() getSource}();
 *}</pre></blockquote>
 * 
 * @author	indyteo
 * @see		ProjectileEntityAction
 */
public class ProjectileEntityShootAction extends ProjectileEntityAction {
	public ProjectileEntityShootAction(Projectile projectile) {
		super(projectile);
	}
}