package entity;

import java.time.LocalDate;

public class Sconto {

	private String codice;
	private LocalDate dataScadenza;
	private double percentuale;


	//COSTRUTTORE: Senza parametri.

	public Sconto() {

		//Attenzione: Genero un codice casuale per evitare che sia prevedibile da qualche cliente
		// questa generazione randomica va poi gestita nella classe GestioneShop

		this.codice = Long.toHexString(Double.doubleToLongBits(Math.random()));


		this.dataScadenza = LocalDate.of(2020, 12, 31);

		//Di default la percentuale di sconto è il 3%
		this.percentuale = 3.0;

	}

	//COSTRUTTORE: Con parametri.

	public Sconto(LocalDate dataScadenza, double percentuale) {

		this.dataScadenza = dataScadenza;
		if(percentuale > 100)
			this.percentuale = 100;
		else
			this.percentuale = percentuale;
	}

	//--------------------------------------Funzioni di Get-----------------------------------------------

	public String getCodice() {
		return this.codice;
	}

	public LocalDate getDataScadenza() {
		return this.dataScadenza;
	}

	public double getPercentuale() {
		return this.percentuale;
	}

	//----------------------------------------------------------------------------------------------------

	//--------------------------------------Funzioni di Set-----------------------------------------------

	/* Attenzione: Per ovvi motivi non è concesso settare il codice  */

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public void setPercentuale(double percentuale) {
		if(percentuale > 100)
			this.percentuale = 100;
		else
			this.percentuale = percentuale;
	}


	//Attenzione: In caso di sovrapposizioni di codice il GestoreShop può richiedere un altro random code
	public void generateRandomCode() {
		this.codice = Long.toHexString(Double.doubleToLongBits(Math.random()));
	}

	//----------------------------------------------------------------------------------------------------

}
