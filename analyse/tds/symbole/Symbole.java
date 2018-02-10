package yal.analyse.tds.symbole;

import yal.analyse.tds.TDS;

public abstract class Symbole {

	private int deplacement;
	
	/* private int numRegion;
	private int numImbrication; */
	
	
	public Symbole() {
		deplacement = TDS.getInstance().tailleZoneDesVariables();
	}
	
	public int getDeplacement() {
		return deplacement;
	}	
	
}
