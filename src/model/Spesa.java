package model;

public class Spesa {

	private float prezzo = -1;
	private String tipologia = null;
	
	public Spesa(float prezzo, String tipologia) {
		this.prezzo = prezzo;
		this.tipologia = tipologia;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public String getTipologia() {
		return tipologia;
	}
}
