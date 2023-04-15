package it.prova.gestioneordiniarticolicategorie.service;

import it.prova.gestioneordiniarticolicategorie.dao.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaService extends IBaseService<Categoria> {

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public Categoria caricaSingoloElementoConArticoli(Long id) throws Exception;
	
	public void aggiungiArticoloACategoriaEsistente(Categoria categoria, Articolo articolo)throws Exception;
	
	public void rimuoviCategoriaPrevioScollegamento(Long id) throws Exception;

}
