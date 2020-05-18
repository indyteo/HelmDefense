package fr.helmdefense.model.entities.attackers;

import fr.helmdefense.model.entities.utils.coords.Location;

public class UrukHai extends Attacker {

	public UrukHai(Location loc) {
		super(loc);	
	}
	
	public UrukHai(double x, double y) {
		this(new Location(x, y));
	}

	
}
