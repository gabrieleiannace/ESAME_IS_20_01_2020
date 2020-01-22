import control.GestioneShop;
import eccezioni.UsedDiscountException;
import eccezioni.WrongEntryException;
import entity.Cliente;
import entity.Sconto;
import entity.Spesa;

public class Main {

	public static void main(String[] args) {
		GestioneShop gestore = new GestioneShop();
		
		Cliente savio = new Cliente("savio98","ciccia","Salvatore","Guerrisi","3337572755","None");
		Cliente gabriele = new Cliente("gabrian","fagiolini","Gabriele","Iannace","3664103563","None");
		Spesa s1 = new Spesa(100);
		Spesa s2 = new Spesa();
		
		Sconto sconto1 = new Sconto();
		Sconto sconto2 = new Sconto();
		
		gestore.addCliente(savio);
		gestore.addCliente(gabriele);
		gestore.addSpesa(s1);
		gestore.addSpesa(s2);
		
		gestore.addSconto(sconto1);
		gestore.addSconto(sconto2);
		try {
			gestore.addSpesa(savio, s1);
		} catch (WrongEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			gestore.aggiungiScontoAlProfilo(savio, s1, sconto1);
			gestore.aggiungiScontoAlProfilo(savio, s1, sconto2);
		} catch (WrongEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsedDiscountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gestore.stampaReport(1);
	}
		

}
