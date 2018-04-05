package yal.arbre.expression;

import java.util.ArrayList;

public class Tableau extends Expression {
	
	private String idf;
	private String type;
	private Expression exprIndice;
	
	public Tableau(String idf, Expression exprIndice, int n) {
		super(n);
		this.idf=idf;
		this.exprIndice = exprIndice;
	}
	
	@Override
	public String getType() {
		return "entier";
	}

	@Override
	public String operation() {
		return " Tableau ";
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

}
