package yal.arbre;

public class Symbole {

	private String type;
	private int deplacement;
	private int numRegion;
	private int numImbrication;
	
	public Symbole(String type){
		this.type = type;
		if (type == "booleen" || type == "entier"){
			deplacement = 4;
		}
	}
	
	public void setDeplacement(int depl) {
		deplacement = depl;
	}
	
	public int getDeplacement(){
		return deplacement;
	}
}
