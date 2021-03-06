package yal.arbre.expression;

import java.util.ArrayList;

public class Appel extends Expression {

	private String idf;
	private String type;
	private ArrayList<Expression> parametres;
	
	
	public Appel(String idf, int n) {
		super(n);
		this.idf=idf;
		parametres = null;
	}

	public Appel(String idf, ArrayList<Expression> par, int n) {
		super(n);
		this.idf=idf;
		parametres = par;
	}
	
	@Override
	public String getType() {
		return "entier";
	}

	@Override
	public String operation() {
		return " Appel ";
	}

	@Override
	public void verifier() {

	}

	@Override
	public String toMIPS() {
		StringBuilder appel = new StringBuilder(50);
		appel.append("# Appel de fonction "+idf+" \n");
		//appel.append("add $sp, $sp, -4 \n");
		appel.append("# On sauvegarde l'adresse de la prochaine instruction dans ra et on saute à l'adresse de la fonction \n");
		appel.append("jal "+idf+" \n");
		 
		appel.append("# On depile la valeur de retour de la fonction dans v0 \n");
		appel.append("add $sp, $sp, 4\n");
		appel.append("lw $v0, 0($sp)\n");
		
		return appel.toString();
	}

	@Override
	public String toString() {
		StringBuilder appel = new StringBuilder(20);
		int i;
		appel.append(idf);
		appel.append("(");
		if (parametres != null){
			for (i = 0; i < parametres.size() - 1; i++){
				appel.append(""+parametres.get(i).toString()+";");
			}
			appel.append(""+parametres.get(i++).toString());
		}
		appel.append(")");
		
		return appel.toString();
	}

}
