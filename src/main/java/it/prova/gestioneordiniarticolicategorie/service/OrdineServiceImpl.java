package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
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

}
