package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import control.GestioneShop;
import entity.Cliente;
import entity.Sconto;
import entity.Spesa;

public class TestGestioneShop {

	//GestoreShop
	GestioneShop gestore;

	//Clienti
	Cliente cliente1;
	Cliente cliente2;
	Cliente cliente3;

	//Spese
	Spesa spesa1;
	Spesa spesa2;
	Spesa spesa3;

	//Sconti
	Sconto sconto1;
	Sconto sconto2;
	Sconto sconto3;


	@Before
	public void setUp() {
		gestore = new GestioneShop();

		cliente1 = new Cliente("gabrieleiannace", "typox41y00", "Gabriele", "Iannace", "3664103563", "4694896121732154");
		cliente2 = new Cliente("alfredo_panaro", "pippo123", "Alfredo", "Panaro", "3331236586", "347166081843949");
		cliente3 = new Cliente("mark97", "spiripeppettenusa", "Marco", "Iannace", "3484032699", "377616523632212");

		spesa1 = new Spesa(100);
		spesa2 = new Spesa(12);
		spesa3 = new Spesa(272);

		sconto1 = new Sconto();
		sconto2 = new Sconto();
		sconto3 = new Sconto();



		gestore.addSconto(sconto1);
		gestore.addSconto(sconto2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_01_GeneraReport() {
		//1. N maggiore del numero degli acquisti del cliente

		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);
		
		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente1, spesa2);
			gestore.addSpesa(cliente2, spesa3);
		}catch (Exception e) {
			e.printStackTrace();
		}


		assertEquals(0, gestore.generaReport(3).size());

	}

	@Test
	public void test_02_GeneraReport() {
		//2. Viene trovato almeno un cliente  (N = n)

		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente2, spesa3);
		}catch (Exception e) {
			//
		}

		assertEquals(1, gestore.generaReport(2).size());

	}

	@Test
	public void test_03_GeneraReport() {
		//3. Il numero N è minore del numero di spese di tutti i clienti

		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addCliente(cliente3);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);
		
		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente2, spesa3);
		}catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(3, gestore.generaReport(0).size());
	}

	@Test
	public void test_04_GeneraReport() {
		//4. Non vengono riscontrati sconti usati
		
		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addCliente(cliente3);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente3, spesa3);
		}catch (Exception e) {
			//
		}

		for(Map.Entry<Cliente, double[]> e : gestore.generaReport(5).entrySet()) {

			assertTrue(e.getValue()[1] == e.getValue()[0]);

		}
	}

	@Test
	public void test_05_GeneraReport() {
		//5. Vengono riscontrati degli sconti applicati al totale di un cliente
		
		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addCliente(cliente3);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente3, spesa3);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto1);
		}catch (Exception e) {
			//
		}
		
		/*
		 * 					Spesa = 100;
		 * 					sconto = 3%;
		 * 					TotaleScontato = 100 - 3% = 97
		 * 
		 * 
		 */
		
		assertTrue(gestore.generaReport(0).get(cliente1)[1] == 97); 

	}

	@Test
	public void test_06_GeneraReport() {
		//6. Viene aggiunto uno sconto ad una spesa che non appartiene al cliente selezionato
		
		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addCliente(cliente3);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente3, spesa3);
			gestore.aggiungiScontoAlProfilo(cliente2, spesa1, sconto1);
		}catch (Exception e) {
			//
		}

		assertTrue(gestore.generaReport(1).get(cliente2)[0] == gestore.generaReport(1).get(cliente2)[1]);

	}

	@Test
	public void test_07_GeneraReport() {
		//7. Più di uno sconto usato
		
		gestore.addCliente(cliente1);
		gestore.addCliente(cliente2);
		gestore.addCliente(cliente3);
		gestore.addSpesa(spesa1);
		gestore.addSpesa(spesa2);
		gestore.addSpesa(spesa3);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.addSpesa(cliente2, spesa2);
			gestore.addSpesa(cliente3, spesa3);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto1);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto2);
		}catch (Exception e) {
			//
		}
		/*
		 * 					Spesa = 100;
		 * 					sconto = 3%;
		 * 					TotaleScontato = 100 - 3% = 97
		 * 
		 * 
		 */
		
		//Il sistema ha ignorato il secondo sconto poichè non è stato applicato alla spesa in quanto già scontata
		
		assertTrue(gestore.generaReport(1).get(cliente1)[1] == 97);
	}

	@Test
	public void test_08_GeneraReport() {
		//8. Sconto 0%

		gestore.addCliente(cliente1);
		gestore.addSpesa(spesa1);

		Sconto sconto = new Sconto(LocalDate.now(), 0);
		gestore.addSconto(sconto);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto);
		}catch (Exception e) {
			//
		}
		
		assertTrue(gestore.generaReport(1).get(cliente1)[0] == gestore.generaReport(1).get(cliente1)[1]);
	}
	
	@Test
	public void test_09_GeneraReport() {
		//9. Sconto 100%
		
		gestore.addCliente(cliente1);
		gestore.addSpesa(spesa1);
		
		Sconto sconto = new Sconto(LocalDate.now(), 100);
		gestore.addSconto(sconto);

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto);
		}catch (Exception e) {
			//
		}
		
		assertTrue(gestore.generaReport(1).get(cliente1)[1] == 0);
	}
	
	@Test
	public void test_10_GeneraReport() {
		//10. Aggiunta di sconto insistente
		
		gestore.addCliente(cliente1);
		gestore.addSpesa(spesa1);
		
		Sconto sconto = new Sconto(LocalDate.now(), 100);
		//Non lo aggiungo al gestore

		try {
			gestore.addSpesa(cliente1, spesa1);
			gestore.aggiungiScontoAlProfilo(cliente1, spesa1, sconto);
		}catch (Exception e) {
			//
		}
		
		assertTrue(gestore.generaReport(1).get(cliente1)[1] == gestore.generaReport(1).get(cliente1)[0]);
	}
}
