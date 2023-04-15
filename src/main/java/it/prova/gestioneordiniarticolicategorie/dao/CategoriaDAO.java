package it.prova.gestioneordiniarticolicategorie.dao;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	
	public Categoria findByIdFetchEagher (Long id)throws Exception;
	public void deleteByIdPostScollegamento(Long id) throws Exception ;

}
