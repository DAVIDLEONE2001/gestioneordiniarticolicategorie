package it.prova.gestioneordiniarticolicategorie.service;

import it.prova.gestioneordiniarticolicategorie.dao.MyDAOFactory;

public class MyServiceFactory {

	// rendiamo questo factory SINGLETON
	private static OrdineService ORDINE_SERVICE_INSTANCE;
	private static ArticoloService ARTICOLO_SERVICE_INSTANCE;
	private static CategoriaService CATEGORIA_SERVICE_INSTANCE;

	public static OrdineService getOrdineServiceInstance() {
		if (ORDINE_SERVICE_INSTANCE == null)
			ORDINE_SERVICE_INSTANCE = new OrdineServiceImpl();

		ORDINE_SERVICE_INSTANCE.setOrdineDAO(MyDAOFactory.getOrdineDAOInstance());
//		ORDINE_SERVICE_INSTANCE.setOrdineDAO(MyDAOFactory.getOrdineDAOInstance());
		return ORDINE_SERVICE_INSTANCE;
	}

	public static ArticoloService getArticoloServiceInstance() {
		if (ARTICOLO_SERVICE_INSTANCE == null)
			ARTICOLO_SERVICE_INSTANCE = new ArticoloServiceImpl();

		ARTICOLO_SERVICE_INSTANCE.setArticoloDAO(MyDAOFactory.getArticoloDAOInstance());
//		ARTICOLO_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
		return ARTICOLO_SERVICE_INSTANCE;
	}
	
	public static CategoriaService getCategoriaServiceInstance() {
		if (CATEGORIA_SERVICE_INSTANCE == null)
			CATEGORIA_SERVICE_INSTANCE = new CategoriaServiceImpl();

		CATEGORIA_SERVICE_INSTANCE.setCategoriaDAO(MyDAOFactory.getCategoriaDAOInstance());
//		ARTICOLO_SERVICE_INSTANCE.setSportDAO(MyDAOFactory.getSportDAOInstance());
		return CATEGORIA_SERVICE_INSTANCE;
	}

}
