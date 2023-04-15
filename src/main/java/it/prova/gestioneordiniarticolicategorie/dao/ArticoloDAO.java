package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {
	
	public Articolo findByIdFetchEagher (Long id)throws Exception;
	public void deleteByIdPostScollegamento (Long id)throws Exception;
	public Double getSommaPrezziDiUnaCategoria (Long id)throws Exception;
	public List<Articolo> articoliStrani () throws Exception;

}
