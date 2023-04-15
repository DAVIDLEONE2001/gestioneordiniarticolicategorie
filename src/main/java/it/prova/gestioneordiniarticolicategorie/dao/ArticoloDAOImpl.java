package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo o) throws Exception {

		if (o == null) {
			throw new Exception("Problema valore input");
		}

		o = entityManager.merge(o);

	}

	@Override
	public void insert(Articolo o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.persist(o);

	}

	@Override
	public void delete(Articolo o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.remove(entityManager.merge(o));

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;

	}

	@Override
	public Articolo findByIdFetchEagher(Long id) throws Exception {
		TypedQuery<Articolo> query = entityManager.createQuery(
				"select u FROM Articolo u left join fetch u.categorie r where u.id = :idArticolo", Articolo.class);
		query.setParameter("idArticolo", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void deleteByIdPostScollegamento(Long id) throws Exception {
		entityManager.createNativeQuery("delete from articolo_categoria a where a.articolo_id = ?1").setParameter(1, id)
				.executeUpdate();
		entityManager.createNativeQuery("delete from articolo a where a.id = ?1").setParameter(1, id).executeUpdate();

	}

	@Override
	public Double getSommaPrezziDiUnaCategoria(Long id) throws Exception {
		String jpql = "SELECT SUM(a.prezzoSingolo) FROM Articolo a JOIN a.categorie c WHERE c.id =?1";
		TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
		query.setParameter(1, id);
		Double result = query.getSingleResult();
		return result;

	}

	@Override
	public List<Articolo> articoliStrani() throws Exception {
		TypedQuery<Articolo> query = entityManager.createQuery(
				"SELECT a FROM Articolo a JOIN a.ordine o WHERE o.dataSpedizione>o.dataScadenza ", Articolo.class);
		return query.getResultList();
	}



}
