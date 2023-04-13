package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDAO;
	
	@Override
	public List<Articolo> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			articoloDAO.setEntityManager(entityManager);

			return articoloDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			articoloDAO.setEntityManager(entityManager);

			return articoloDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public void aggiorna(Articolo o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			articoloDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			articoloDAO.update(o);
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
	public void inserisciNuovo(Articolo o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			articoloDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			articoloDAO.insert(o);
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

			articoloDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			articoloDAO.delete(articoloDAO.get(id));
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
	public void setArticoloDAO(ArticoloDAO articoloDAO) {

		this.articoloDAO = articoloDAO;
		
	}

}
