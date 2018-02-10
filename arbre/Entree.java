package yal.arbre;

public abstract class Entree {

	private String idf;
	
	
	public Entree (String idf) {
		this.idf = idf;
	}
	
	public String toString() {
		return idf;
	}
	
}
