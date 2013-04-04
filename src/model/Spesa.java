package model;

import java.math.BigDecimal;

public class Spesa {

	private BigDecimal prezzo = null;
	private String tipologia = null;
	
	public Spesa(BigDecimal prezzo, String tipologia) {
		this.prezzo = prezzo;
		this.tipologia = tipologia;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public String getTipologia() {
		return tipologia;
	}
}
