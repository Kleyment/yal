package yal.arbre.expression;

public class Variable extends Expression {

	private String idf;
	private int deplacement;
	
	
	public Variable(String idf, int n) {
		super(n);
		this.idf = idf;
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public String operation() {
		return " Variable ";
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		StringBuilder var = new StringBuilder("20");
		
		var.append("lw $v0, ");
		var.append(deplacement);
		var.append("($s7)");
		var.append("\n");
		
		return var.toString();
	}

	@Override
	public String toString() {
		return "";
	}
	
}
