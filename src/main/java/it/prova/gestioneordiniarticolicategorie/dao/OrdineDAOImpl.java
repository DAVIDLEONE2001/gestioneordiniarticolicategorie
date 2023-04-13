package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		o = entityManager.merge(o);

	}

	@Override
	public void insert(Ordine o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.persist(o);

	}

	@Override
	public void delete(Ordine o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

}
