package yal.analyse.tds.symbole;

public class SymboleFonction extends Symbole {

	private String typeRetour;

	public SymboleFonction(String typeRetour) {
		super();
		this.typeRetour = typeRetour;
	}
	
	public String getTypeRetour() {
		return typeRetour;
	}
	
}
