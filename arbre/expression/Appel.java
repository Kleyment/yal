package yal.arbre.expression;

import java.util.ArrayList;

public class Appel extends Expression {

	private String idf;
	private String type;
	
	
	public Appel(int n) {
		super(n);
	}

	public Appel(ArrayList<Expression> par, int n) {
		super(n);
	}
	
	@Override
	public String getType() {
		return type;
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
		
		return appel.toString();
	}

	@Override
	public String toString() {
		StringBuilder appel = new StringBuilder(20);
		
		appel.append(idf);
		appel.append("(");
		// paramètres
		appel.append(")");
		
		return appel.toString();
	}

}
