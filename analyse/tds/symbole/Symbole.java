package yal.analyse.tds.symbole;

import yal.analyse.tds.TDS;

public abstract class Symbole {

	private String type;
	private int deplacement;	
	private int numeroRegion;
	private int numeroImbrication; 
	
	
	public Symbole(String type) {
		this.type = type;
		deplacement = - (TDS.getInstance().tailleZoneDesVariables()+4);
		numeroRegion = TDS.getInstance().numeroRegion();
		numeroImbrication = TDS.getInstance().numeroImbrication();
	}
	
	public String getType() {
		return type;
	}
	
	public int getDeplacement() {
		//System.out.println("getDeplacement() "+deplacement);
		return deplacement;
	}	
	
	public int numeroRegion() {
		return numeroRegion;
	}
	
	public int numeroImbrication() {
		return numeroImbrication;
	}

	@Override
	public String toString() {
		return "Symbole [type=" + type + ", deplacement=" + deplacement + ", numeroRegion=" + numeroRegion
				+ ", numeroImbrication=" + numeroImbrication + "]";
	}
	
}
