package it.prova.gestioneordiniarticolicategorie.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.OrdineService;

public class MyTest {

	public static void main(String[] args) throws Exception {

		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {

			// *****************INIZIO TEST ORDINE******************************

			System.out.println("Nella tabella ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi");
			testInsertOrdine(ordineServiceInstance);
			testUpdateOrdine(ordineServiceInstance);
			System.out.println("Nella tabella ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi");

			// *****************FINE TEST ORDINE********************************

			// *****************INIZIO TEST ARTICOLO****************************

			System.out.println(
					"Nella tabella articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi");
			testInsertArticolo(ordineServiceInstance, articoloServiceInstance);
			testUpdateArticolo(ordineServiceInstance, articoloServiceInstance);
			System.out.println(
					"Nella tabella articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi");

			// *****************FINE TEST ARTICOLO******************************

			// *****************INIZIO TEST CATEGORIA****************************

			System.out.println(
					"Nella tabella categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi");
			testInsertCategoria(categoriaServiceInstance);
			testUpdateCategoria(categoriaServiceInstance);
			System.out.println(
					"Nella tabella categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi");

			// *****************FINE TEST CATEGORIA******************************

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	// *****************INIZIO TEST ORDINE****************************

	private static void testInsertOrdine(OrdineService ordineServiceIstance) throws Exception {

		System.out.println("\n***********INIZIO TEST testInsertOrdine************\n");

		Ordine ordine = new Ordine("Mario", "Via Roma 12", LocalDate.parse("2020-05-20"),
				LocalDate.parse("2020-05-20"));

		ordineServiceIstance.inserisciNuovo(ordine);
		if (ordine.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testInsertOrdine: ordine non aggiunto**********\n");

		}

		ordineServiceIstance.rimuovi(ordine.getId());

		System.out.println("\n***********FINE TEST testInsertOrdine: PASSED************\n");

	}

	private static void testUpdateOrdine(OrdineService ordineServiceIstance) throws Exception {

		System.out.println("\n***********INIZIO TEST testUpdateOrdine************\n");

		Ordine ordine = new Ordine("Mario", "Via Roma 12", LocalDate.parse("2020-05-20"),
				LocalDate.parse("2020-05-20"));

		String nomeOrdine = ordine.getNomeDestinatario();

		ordineServiceIstance.inserisciNuovo(ordine);
		if (ordine.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateOrdine: ordine non aggiunto**********\n");

		}

		ordine.setNomeDestinatario("Mimmo");
		ordineServiceIstance.aggiorna(ordine);

		Ordine ordineReload = ordineServiceIstance.caricaSingoloElemento(ordine.getId());
		String nomeOrdineNuovo = ordineReload.getNomeDestinatario();

		if (nomeOrdineNuovo.equals(nomeOrdine)) {

			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateOrdine: ordine non aggiornato**********\n");
		}

		ordineServiceIstance.rimuovi(ordine.getId());

		System.out.println("\n***********FINE TEST testUpdateOrdine: PASSED************\n");

	}

	// *****************FINE TEST ORDINE****************************

	// *****************INIZIO TEST ARTICOLO****************************

	private static void testInsertArticolo(OrdineService ordineServiceIstance, ArticoloService articoloServiceIstance)
			throws Exception {

		System.out.println("\n***********INIZIO TEST testInsertArticolo************\n");

		Ordine ordine = new Ordine("Mario", "Via Roma 12", LocalDate.parse("2020-05-20"),
				LocalDate.parse("2020-05-20"));

		Articolo articolo = new Articolo("Pannolini", "ABZXC098760", 1200D, LocalDate.now(), ordine);

		ordineServiceIstance.inserisciNuovo(ordine);
		articoloServiceIstance.inserisciNuovo(articolo);
		if (ordine.getId() == null || articolo.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testInsertArticolo: ordine o articolo non aggiunto**********\n");

		}

		articoloServiceIstance.rimuovi(articolo.getId());

		ordineServiceIstance.rimuovi(ordine.getId());

		System.out.println("\n***********FINE TEST testInsertArticolo: PASSED************\n");

	}

	private static void testUpdateArticolo(OrdineService ordineServiceIstance, ArticoloService articoloServiceIstance)
			throws Exception {

		System.out.println("\n***********INIZIO TEST testUpdateArticolo************\n");

		Ordine ordine = new Ordine("Mario", "Via Roma 12", LocalDate.parse("2020-05-20"),
				LocalDate.parse("2020-05-20"));

		Articolo articolo = new Articolo("Pannolini", "ABZXC098760", 1200D, LocalDate.now(), ordine);

		ordineServiceIstance.inserisciNuovo(ordine);
		articoloServiceIstance.inserisciNuovo(articolo);
		if (ordine.getId() == null || articolo.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateArticolo: ordine o articolo non aggiunto**********\n");

		}

		String descriptionArticolo = articolo.getDescrizione();

		articolo.setDescrizione("Giocattoli");

		articoloServiceIstance.aggiorna(articolo);

		Articolo articoloReload = articoloServiceIstance.caricaSingoloElemento(articolo.getId());

		String descriptionArticoloNew = articoloReload.getDescrizione();

		if (descriptionArticoloNew.equals(descriptionArticolo)) {

			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateArticolo: articolo non aggiornato**********\n");

		}

		articoloServiceIstance.rimuovi(articolo.getId()); // Rimozione di un articolo legato ad un ordine (non a delle
															// categorie)----fatto

		ordineServiceIstance.rimuovi(ordine.getId());

		System.out.println("\n***********FINE TEST testUpdateArticolo: PASSED************\n");

	}

	// *****************FINE TEST ARTICOLO****************************

	// *****************INIZIO TEST CATEGORIA****************************

	private static void testInsertCategoria(CategoriaService categoriaServiceIstance) throws Exception {

		System.out.println("\n***********INIZIO TEST testInsertCategoria************\n");

		Categoria categoria = new Categoria("Prodotti per la casa", "PRSTPLC");

		categoriaServiceIstance.inserisciNuovo(categoria);
		if (categoria.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testInsertCategoria: ordine o articolo non aggiunto**********\n");

		}

		categoriaServiceIstance.rimuovi(categoria.getId());

		System.out.println("\n***********FINE TEST testInsertCategoria: PASSED************\n");

	}

	private static void testUpdateCategoria(CategoriaService categoriaServiceIstance) throws Exception {

		System.out.println("\n***********INIZIO TEST testUpdateCategoria************\n");

		Categoria categoria = new Categoria("Prodotti per la casa", "PRSTPLC");

		categoriaServiceIstance.inserisciNuovo(categoria);
		if (categoria.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateCategoria: ordine o articolo non aggiunto**********\n");

		}

		String descrizione = categoria.getDescrizione();

		categoria.setDescrizione("Cura della persona");

		categoriaServiceIstance.aggiorna(categoria);

		Categoria categoriaReload = categoriaServiceIstance.caricaSingoloElemento(categoria.getId());

		String descrizioneNew = categoriaReload.getDescrizione();

		if (descrizioneNew.equals(descrizione)) {
			throw new RuntimeException(
					"\n****************TEST FAILED testUpdateCategoria: categoria non aggiornata**********\n");

		}

		categoriaServiceIstance.rimuovi(categoria.getId());

		System.out.println("\n***********FINE TEST testUpdateCategoria: PASSED************\n");

	}

	private static void testAggiungiArticoloACategoriaEsistente(CategoriaService categoriaServiceIstance,
			ArticoloService articoloServiceIstance, OrdineService ordineServiceIstance) throws Exception {

		System.out.println("\n***********INIZIO TEST testInsertCategoria************\n");

		Categoria categoria = new Categoria("Prodotti per la casa", "PRSTPLC");

		categoriaServiceIstance.inserisciNuovo(categoria);

		Ordine ordine = new Ordine("Mario", "Via Roma 12", LocalDate.parse("2020-05-20"),
				LocalDate.parse("2020-05-20"));

		ordineServiceIstance.inserisciNuovo(ordine);

		Articolo articolo = new Articolo("Pannolini", "ABZXC098760", 1200D, LocalDate.now(), ordine);

		articoloServiceIstance.inserisciNuovo(articolo);

		if (categoria.getId() == null || ordine.getId() == null || articolo.getId() == null) {
			throw new RuntimeException(
					"\n****************TEST FAILED testInsertCategoria: ordine o articolo o categoria non aggiunti**********\n");

		}

		categoriaServiceIstance.rimuovi(categoria.getId());

		System.out.println("\n***********FINE TEST testInsertCategoria: PASSED************\n");

	}

	// *****************FINE TEST CATEGORIA****************************
}

/*
 * Inserimento nuovo ordine ----fatto Aggiornamento ordine esistente ---- fatto
 * Inserimento nuovo articolo ----fatto Aggiornamento articolo
 * esistente----fatto Rimozione di un articolo legato ad un ordine (non a delle
 * categorie)----fatto Inserimento nuova categoria----fatto Aggiornamento
 * categoria esistente----fatto Aggiungi articolo a categoria---- Aggiungi
 * categoria ad un articolo Rimozione articolo (previo scollegamento dalle
 * categorie) Rimozione categoria (previo scollegamento dagli articoli)
 * Rimozione ordine (nel caso in cui sia collegato ad almeno un articolo
 * lanciare eccezione custom)
 */