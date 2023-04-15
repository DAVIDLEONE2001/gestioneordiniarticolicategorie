package it.prova.gestioneordiniarticolicategorie.service;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineService extends IBaseService<Ordine> {

	public void setOrdineDAO(OrdineDAO ordineDAO);

	public List<Ordine> ordiniDiUnaCategoria(Categoria categoria) throws Exception;

	public List<Categoria> distinteCategorieDiUnOrdine(Ordine ordine) throws Exception;

	public Ordine caricaOrdinePiuRecenteDiUnaCategoria(Categoria categoria) throws Exception;

	public List<String> caricaCodiciDiCategorieDiArtcioliDiOrdiniEffettuatiInMeseEAnno(int mese, int anno)
			throws Exception;

	public Double caricaSommaPrezziDiUnDesinatario(String nomeDestinatario) throws Exception;
	
	public List<String> listaDiIndirizziDiordiniAventiArticoliConSeriale(String segmentoSeriale) throws Exception;
}
