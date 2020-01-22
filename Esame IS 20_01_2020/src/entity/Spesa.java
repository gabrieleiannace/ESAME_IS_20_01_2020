package entity;

import java.time.LocalDate;

public class Spesa {

	//L'id di una spesa è univoco del cliente e quindi autoincrementale

	private int id;
	static private int contatore = 0;


	private double totaleSpeso;
	private LocalDate data;
	private boolean scontato;
	private String codiceScontoUsato;
	private double totaleScontato;
	private boolean ordinato;

	
	//COSTRUTTORE: Senza parametri.
	
	public Spesa() {
		contatore++;
		this.id = contatore;

		
		//Il totale speso sarà anche settabile per poter simulare l'interazione software
		this.totaleSpeso = 0.0;
		
		//Il totale scontato se il prodotto non è scontato è pari al totaleSpeso
		this.totaleScontato = this.totaleSpeso;

		this.scontato = false;
		this.codiceScontoUsato = null;
		
		//Setto sempre la data di inizializzazione come l'attuale
		this.data = LocalDate.now();
		
		this.ordinato = false;
	}
	
	//COSTRUTTORE: Con parametri.
	
	public Spesa(double totaleSpeso) {
		
		contatore++;
		this.id = contatore;

		
		this.totaleSpeso = totaleSpeso;
		
		//Il totale scontato se il prodotto non è scontato è pari al totaleSpeso
		this.totaleScontato = this.totaleSpeso;

		this.scontato = false;
		this.codiceScontoUsato = null;
		
		//Setto sempre la data di inizializzazione come l'attuale
		this.data = LocalDate.now();
		
		this.ordinato = false;
	}
	
	//--------------------------------------Funzioni di Get-----------------------------------------------

	public int getId() {
		return this.id;
	}
	
	public double getTotaleSpeso() {
		return this.totaleSpeso;
	}

	public double getTotaleScontato() {
		return this.totaleScontato;
	}
	
	public boolean isScontato() {
		return (this.scontato || this.codiceScontoUsato == null);
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	public String getCodiceScontoUsato() {
		return this.codiceScontoUsato;
	}
	
	public boolean isOrdinato() {
		return this.ordinato;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	//--------------------------------------Funzioni di Set-----------------------------------------------
	
								/* Attenzione: Non devo poter mai settare l'ID  */
	
	public void setScontato(boolean scontato) {
		this.scontato = scontato;
	}
	
	public void setCodiceScontoUsato(String codice) {
		this.codiceScontoUsato = codice;
		
		//Abilito poi lo sconto
		this.scontato = true;
	}
	
	public void setTotaleSpeso(Double totaleSpeso) {
		this.totaleSpeso = totaleSpeso;
		
		if(!this.scontato)
			this.totaleScontato = this.totaleSpeso;
	}
	
	public void setTotaleScontato(Double totaleScontato) {
		this.totaleScontato = totaleScontato;
	}
	
	public void setOrdinato(boolean ordinato) {
		this.ordinato = ordinato;
	}
	
	
	//----------------------------------------------------------------------------------------------------
	
	public String toString() {
		return String.valueOf(this.id);
	}
}
