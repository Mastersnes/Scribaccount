package bdd.dao;

import java.util.Map;

import bdd.BDD;
import bdd.table.Prestataire;
import constantes.Phrases;
import constantes.Support;

public class PrestataireDAO {
	private static Prestataire libre;

	/**
	 * Renvoi le prestataire par id
	 * 
	 * @param id
	 * @return
	 */
	public static Prestataire getById(final String id) {
		if ("-1".equals(id)) {
			return getLibre();
		}
		final Map<String, Prestataire> prestataires = BDD.getInstance().getPrestataires();
		return prestataires.get(id);
	}

	private static Prestataire getLibre() {
		if (libre == null) {
			libre = Prestataire.create("-1", Phrases.VENTE_LIBRE, Support.LIVRE.getId());
		}
		return libre;
	}

	/**
	 * Renvoi le prochain id libre
	 * 
	 * @return
	 */
	public static String nextId() {
		final Map<String, Prestataire> prestataires = BDD.getInstance().getPrestataires();
		int id = 0;
		while (prestataires.containsKey(String.valueOf(id))) {
			id++;
		}
		return String.valueOf(id);
	}

}
