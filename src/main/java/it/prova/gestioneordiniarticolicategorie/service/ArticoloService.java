package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface ArticoloService extends IBaseService<Articolo> {

	public void setArticoloDAO (ArticoloDAO articoloDAO);
	
	public void aggiungiCategoriaAArticoloEsistente(Categoria categoria, Articolo articolo)throws Exception;
	
	public Articolo caricaSingoloElementoConCategorie (Long id) throws Exception;
	
	public void rimuoviArticoloPrevioScollegamento (Long id)throws Exception;
	
	public Double ottieniSommaPrezziDiArticoliDiUnaCategoria(Categoria categoria) throws Exception ;
	
	public List<Articolo> getArticoliConErroriDOrdine() throws Exception;
	
	
}
