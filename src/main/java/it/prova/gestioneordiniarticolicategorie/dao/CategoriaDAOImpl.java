package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		o = entityManager.merge(o);

	}

	@Override
	public void insert(Categoria o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore input");
		}

		entityManager.persist(o);
	}

	@Override
	public void delete(Categoria o) throws Exception {
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
	public Categoria findByIdFetchEagher(Long id) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery(
				"select u FROM Categoria u left join fetch u.articoli r where u.id = :idCategoria", Categoria.class);
		query.setParameter("idCategoria", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

}
