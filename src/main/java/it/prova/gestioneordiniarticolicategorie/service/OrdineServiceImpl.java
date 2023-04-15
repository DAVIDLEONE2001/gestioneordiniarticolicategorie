package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.exception.OrdineConArticoliException;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	@Override
	public List<Ordine> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public void aggiorna(Ordine o) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			ordineDAO.update(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Ordine o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			ordineDAO.insert(o);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			if (!ordineDAO.get(id).getArticoli().isEmpty()) {
				throw new OrdineConArticoliException("Ordine con articoli");
			}
			ordineDAO.delete(ordineDAO.get(id));
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {

		this.ordineDAO = ordineDAO;

	}

	@Override
	public List<Ordine> ordiniDiUnaCategoria(Categoria categoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (categoria.getId() == null) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.ordiniDiUnaCategoria(categoria.getId());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public List<Categoria> distinteCategorieDiUnOrdine(Ordine ordine) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (ordine.getId() == null) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.distinteCategorieDiUnOrdine(ordine.getId());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public Ordine caricaOrdinePiuRecenteDiUnaCategoria(Categoria categoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (categoria.getId() == null) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.getOrdinePiuRecenteByCateoria(categoria.getId());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public List<String> caricaCodiciDiCategorieDiArtcioliDiOrdiniEffettuatiInMeseEAnno(int mese, int anno)
			throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (mese > 12 || mese < 1 || anno < 1000 || anno > 2999) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.getCodiciDiCategorieDiMeseEAnno(mese, anno);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public Double caricaSommaPrezziDiUnDesinatario(String nomeDestinatario) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (nomeDestinatario == null) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.getSumPrezziDiDesinatario(nomeDestinatario);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public List<String> listaDiIndirizziDiordiniAventiArticoliConSeriale(String segmentoSeriale) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		if (segmentoSeriale == null) {
			throw new RuntimeException("Errore input");
		}

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.listaDiIndirizziDiordiniAventiArticoliConSeriale(segmentoSeriale);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

}
