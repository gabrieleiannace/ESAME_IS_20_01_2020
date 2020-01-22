package entity;

import java.util.ArrayList;

public class Cliente {
	
	//Lista degli sconti usufruiti dal cliente
		ArrayList<Sconto> listaSconti = new ArrayList<Sconto>();
		
	//Lista delle spese effettuate dal cliente
		ArrayList<Spesa> listaSpese = new ArrayList<Spesa>();
		
	private String username;
	private String passowrd;
	private String nome;
	private String cognome;
	private int numeroSpese;
	private String numeroTelefono;
	private String numeroCarta;
	
	
	//COSTRUTTORE: Con parametri.
	public Cliente(String username, String password, String nome, String cognome, String numeroTelefono, String numeroCarta) {
		this.username = username;
		this.passowrd = password;
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
		this.numeroCarta = numeroCarta;
		
		//Il numero delle spese di un cliente al momento dell'inizializzazione è settato sempre a zero!
		this.numeroSpese = 0;
	}
	
	//--------------------------------------Funzioni di Add-----------------------------------------------
	
	public void addSpesa(Spesa s) {
		listaSpese.add(s);
		
		//Incremento il numero di spese effettuate quando viene aggiunta una spesa alla propria lista
		this.numeroSpese += 1;
	}
	
	public void addSconti(Sconto s) {
		listaSconti.add(s);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	//--------------------------------------Funzioni di Get-----------------------------------------------
	
	public String getUsername() {
		return this.username;
	}
	
	//Questa funzione può essere utile in caso l'utente (o il sistema) voglia visualizzare la password
	//e magari riprestinarla/cambiarla
	public String getPassword() {
		return this.passowrd;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public String getNumeroTelefono() {
		return this.numeroTelefono;
	}
	
	public String getNumeroCarta() {
		return this.numeroCarta;
	}
	
	//Questa funzione è utile al sistema per il report e per l'utente che vuole essere aggiornato sul numero
	//di spese che ha effettuato
	public int getNumeroSpese() {
		return this.numeroSpese;
	}
	
	
	//Funzione di ritorno della lista sconti
	public ArrayList<Sconto> getListaSconti(){
		return this.listaSconti;
	}
	
	//Funzione di ritorno della lista spese
	public ArrayList<Spesa> getListaSpesa(){
		return this.listaSpese;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	//--------------------------------------Funzioni di Set-----------------------------------------------
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.passowrd = password;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		//Qui farò una gestione degli errori per la gestione del casting!!!!!!!
		this.numeroTelefono = numeroTelefono;
	}
	
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	//----------------------------------------------------------------------------------------------------
	
	//To String
	public String toString() {
		return this.nome + " " + this.cognome;
	}
	
	
	//!!!!!!!!!!!!Attenzione! Rimuovere anche lo sconto associato alla spesa!!!!!!!!!!!!!!!!!!!!!!!
	
	
	
	
	
	
	//In caso di smarrimento di un pacco un cliente può eventualmente chiamare il centro assistenza
	//e farsi rimuovere la spesa (procedendo con un eventuale rimborso)
	public void cancellaSpesa(Spesa s) {
		int index = this.listaSpese.indexOf(s);
		this.listaSpese.remove(index);
		this.decrementaNumeroSpese();
	}
	
	//Questo metodo può essere utile ad un eventuale moderatore di sistema
		public void decrementaNumeroSpese() {
			this.numeroSpese--;
		}
		
		public void stampaListaSpesa() {
			System.out.println(this.nome + " ha in lista : ");
			for(Spesa spesa : this.listaSpese) {
				System.out.println(spesa);
			}
		}
}
