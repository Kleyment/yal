package yal.analyse.tds.symbole;

public class SymboleFonction extends Symbole {

	private String[] typesDesParametres;
	
	
	public SymboleFonction(String typeRetour) {
		super(typeRetour);
		typesDesParametres = null;
	}
	
	public SymboleFonction(String typeRetour, String... parametres) {
		super(typeRetour);
		typesDesParametres = new String[parametres.length];
		
		for (int i = 0; i < parametres.length; i ++) {
			typesDesParametres[i] = parametres[i];
		}
	}
	
	public String getTypeRetour() {
		return getType();
	}
	
}
