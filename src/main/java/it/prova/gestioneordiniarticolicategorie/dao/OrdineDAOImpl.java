package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
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

	@Override
	public List<Ordine> ordiniDiUnaCategoria(Long IdCategoria) {

		return entityManager.createQuery("select o from Ordine o join o.articoli a join a.categorie c where c.id= ?1 ",
				Ordine.class).setParameter(1, IdCategoria).getResultList();
	}

	@Override
	public List<Categoria> distinteCategorieDiUnOrdine(Long idOrdine) throws Exception {
		return entityManager
				.createQuery("select distinct c from Ordine o join o.articoli a join a.categorie c where o.id= ?1 ",
						Categoria.class)
				.setParameter(1, idOrdine).getResultList();
	}

	@Override
	public Ordine getOrdinePiuRecenteByCateoria(Long idCategoria) throws Exception {

		String queryString = "SELECT o FROM Ordine o JOIN o.articoli a JOIN a.categorie c WHERE c.id = :idCategoria AND o.dataSpedizione = (SELECT MAX(o2.dataSpedizione) FROM Ordine o2 JOIN o2.articoli a2 JOIN a2.categorie c2 WHERE c2.id = :idCategoria)";
		TypedQuery<Ordine> query = entityManager.createQuery(queryString, Ordine.class);
		query.setParameter("idCategoria", idCategoria);
		return query.getSingleResult();

	}

	@Override
	public List<String> getCodiciDiCategorieDiMeseEAnno(int mese, int anno) throws Exception {
		String queryString = "SELECT distinct a.descrizione FROM Ordine o JOIN o.articoli a JOIN a.categorie c WHERE year(o.dataSpedizione) = ?1 and month(o.dataSpedizione) = ?2 ";
		TypedQuery<String> query = entityManager.createQuery(queryString, String.class);
		query.setParameter(1, anno);
		query.setParameter(2, mese);
		return query.getResultList();
	}

	@Override
	public Double getSumPrezziDiDesinatario(String nomeDestinatario) throws Exception {
		String queryString = "SELECT SUM(a.prezzoSingolo) FROM Articolo a JOIN a.ordine o WHERE o.nomeDestinatario like ?1";;
		TypedQuery<Double> query = entityManager.createQuery(queryString, Double.class);
		query.setParameter(1, nomeDestinatario);
		
		return query.getSingleResult();
	}

	@Override
	public List<String> listaDiIndirizziDiordiniAventiArticoliConSeriale(String segmentoSeriale) throws Exception {
		String queryString = "SELECT distinct o.indirizzoSpedizione FROM Ordine o JOIN o.articoli a WHERE a.numeroSeriale like ?1";
		TypedQuery<String> query = entityManager.createQuery(queryString, String.class);
		query.setParameter(1, "%"+segmentoSeriale+"%");
		return query.getResultList();
	}


}
