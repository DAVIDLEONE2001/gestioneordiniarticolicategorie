package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public List<Ordine> ordiniDiUnaCategoria(Long IdCategoria);

	public List<Categoria> distinteCategorieDiUnOrdine(Long idOrdine) throws Exception;

	public Ordine getOrdinePiuRecenteByCateoria(Long idCategoria) throws Exception;

	public List<String> getCodiciDiCategorieDiMeseEAnno(int mese, int anno) throws Exception;

	public Double getSumPrezziDiDesinatario(String nomeDestinatario) throws Exception;
	
	public List<String> listaDiIndirizziDiordiniAventiArticoliConSeriale (String segmentoSeriale)throws Exception;
	


}
