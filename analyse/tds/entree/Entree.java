package yal.analyse.tds.entree;

public abstract class Entree {

	private String idf;
	
	
	public Entree (String idf) {
		this.idf = idf;
	}

	public String getIdf() {
		return idf;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((idf == null) ? 0 : idf.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Entree other = (Entree) obj;
		
		if (idf == null) {
			if (other.idf != null) {
				return false;
			}
		} 
		else if (!idf.equals(other.idf)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return idf;
	}
	
}
