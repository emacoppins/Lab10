package it.polito.tdp.bar.model;

public class Tavolo {
	private Integer posti;
	private Event occupato;
	/**
	 * @param posti
	 * @param occupato
	 */
	public Tavolo(Integer posti, Event occupato) {
		this.posti = posti;
		this.occupato = occupato;
	}
	
	public Integer getPosti() {
		return posti;
	}
	public void setPosti(Integer posti) {
		this.posti = posti;
	}
	public Event getOccupato() {
		return occupato;
	}
	public void setOccupato(Event occupato) {
		this.occupato = occupato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((occupato == null) ? 0 : occupato.hashCode());
		result = prime * result + ((posti == null) ? 0 : posti.hashCode());
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
		Tavolo other = (Tavolo) obj;
		if (occupato == null) {
			if (other.occupato != null)
				return false;
		} else if (!occupato.equals(other.occupato))
			return false;
		if (posti == null) {
			if (other.posti != null)
				return false;
		} else if (!posti.equals(other.posti))
			return false;
		return true;
	}

	
	
	
}


