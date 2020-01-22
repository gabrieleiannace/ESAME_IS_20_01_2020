package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import eccezioni.UsedDiscountException;
import eccezioni.WrongEntryException;
import entity.Cliente;
import entity.Sconto;
import entity.Spesa;

public class GestioneShop {

	//Liste
	ArrayList<Cliente> listaClienti = null;
	ArrayList<Spesa> listaSpese = null;
	ArrayList<Sconto> listaSconti = null;


	//COSTRUTTORE: Senza parametri.

	public GestioneShop() {
		//Inizializzo le liste.
		this.listaClienti = new ArrayList<Cliente>();
		this.listaSpese = new ArrayList<Spesa>();
		this.listaSconti = new ArrayList<Sconto>();
	}

	//--------------------------------------Inserimento----------------------------------------------------

	public void addCliente(Cliente c) {
		this.listaClienti.add(c);
	}

	public void addSpesa(Spesa s) {
		this.listaSpese.add(s);
	}

	public void addSconto(Sconto s) {
		this.listaSconti.add(s);
	}

	public void addSpesa(Cliente c, Spesa s)throws WrongEntryException {
		if(this.listaClienti.contains(c) && !s.isOrdinato()) {
			if(this.listaSpese.contains(s)) {
				c.addSpesa(s);
				s.setOrdinato(true);
			}
			else
				throw new WrongEntryException();
		}
		else
			throw new WrongEntryException();
	}
	//----------------------------------------------------------------------------------------------------

	public boolean isSpesaEsistente(Spesa s) {
		return this.listaSpese.contains(s);
	}
	public boolean isClienteEsistente(Cliente c) {
		return this.listaClienti.contains(c);
	}
	public boolean isScontoEsistente(Sconto s) {
		return this.listaSconti.contains(s);
	}
	public void refreshSpesa(Spesa s) {

		if(s.isScontato()) {

			String codiceSconto = s.getCodiceScontoUsato();

			for(Sconto sconto : this.listaSconti) 

				if(sconto.getCodice() == codiceSconto) {

					double percentuale = sconto.getPercentuale();
					s.setTotaleScontato(s.getTotaleSpeso()/100 * (100-percentuale));

				}
		}
	}

	//----------------------------------------------------------------------------------------------------

	public void aggiungiScontoAlProfilo(Cliente cliente,Spesa spesa, Sconto sconto) throws WrongEntryException, UsedDiscountException{

		if(this.isClienteEsistente(cliente) && this.isScontoEsistente(sconto) && this.isSpesaEsistente(spesa)) {

			if(!cliente.getListaSconti().contains(sconto) && cliente.getListaSpesa().contains(spesa))

				for(Spesa spesaScontata : cliente.getListaSpesa()) {

					spesaScontata.setCodiceScontoUsato(sconto.getCodice());
					refreshSpesa(spesaScontata);

				}else
					throw new UsedDiscountException();

		}else
			throw new WrongEntryException();

	}
	
	public double getSommaTotale(Cliente c) {
		double sommaTotale = 0;
		
		for(Spesa s : c.getListaSpesa()) {
			
			sommaTotale += s.getTotaleSpeso();	
		}
		
		return sommaTotale;
	}
	public double getSommaTotaleScontata(Cliente c) {
		double sommaTotaleScontata = 0;
		
		for(Spesa s : c.getListaSpesa()) {
			
			sommaTotaleScontata += s.getTotaleScontato();	
		}
		
		return sommaTotaleScontata;
	}
	
	
	public HashMap<Cliente, double[]> generaReport(int N){
		
		HashMap<Cliente,double[]> reportMap = new HashMap<Cliente, double[]>();
		
		for(Cliente cliente : this.listaClienti) {
			
			if(cliente.getNumeroSpese() >= N) {
				
				double totali[] = new double[2];
				
				totali[0] = this.getSommaTotale(cliente);
				totali[1] = this.getSommaTotaleScontata(cliente);
				
				reportMap.put(cliente, totali);
				
				
			}
			
		}
		
		return reportMap;
		
	}
	
	public void stampaReport(int N) {
		
		HashMap<Cliente, double[]> risultatiMap = this.generaReport(N);
		
		for(Map.Entry<Cliente, double[]> entry : risultatiMap.entrySet()) {
			
			
			System.out.println(entry.getKey() + " ha effettuato: " + entry.getKey().getNumeroSpese()
					+ " spese" + " pagando:");
			System.out.println(entry.getValue()[1] + 
					"€ / " + entry.getValue()[0] + "€ risparimando così "
					+ String.valueOf(entry.getValue()[0] - entry.getValue()[1]).substring(0, 3) + "€");
			
		}
		
	}
		

	//----------------------------------------------------------------------------------------------------

	//--------------------------------------Stampe--------------------------------------------------------

	public void stampaSpese() {
		for(Spesa spesa: this.listaSpese) {
			System.out.println(spesa);
		}

	}
	public void StampaClienti() {
		for(Cliente cliente: this.listaClienti) {
			System.out.println(cliente);
		}

	}

	//----------------------------------------------------------------------------------------------------

}
