package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;
	
	@Override
	public List<Categoria> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
	}

	@Override
	public void aggiorna(Categoria o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			categoriaDAO.update(o);
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
	public void inserisciNuovo(Categoria o) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			categoriaDAO.insert(o);
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

			categoriaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			categoriaDAO.delete(categoriaDAO.get(id));
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
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {

		this.categoriaDAO = categoriaDAO;
		
	}

	@Override
	public Categoria caricaSingoloElementoConArticoli(Long id) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);

			return categoriaDAO.findByIdFetchEagher(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);

		}
		
	}

	@Override
	public void aggiungiArticoloACategoriaEsistente(Categoria categoria, Articolo articolo) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {

			categoriaDAO.setEntityManager(entityManager);
			entityManager.getTransaction().begin();
			Categoria categoriaTemp = categoriaDAO.get(categoria.getId());
			categoriaTemp.getArticoli().add(articolo);
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;

		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

}
