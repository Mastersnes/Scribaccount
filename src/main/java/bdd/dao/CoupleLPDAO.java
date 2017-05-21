package bdd.dao;

import java.math.BigDecimal;
import java.util.Map;

import bdd.BDD;
import bdd.table.CoupleLP;
import bdd.table.Livre;
import bdd.table.Prestataire;

public class CoupleLPDAO {

	/**
	 * Renvoi un couple par idLivre, idPresta
	 * 
	 * @param id
	 * @return
	 */
	public static CoupleLP getById(final String idLivre, final String idPresta) {
		final Map<String, CoupleLP> couples = BDD.getInstance().getRedevances();
		return couples.get(idLivre + "-" + idPresta);
	}

	/**
	 * Creer chaque couple livre prestataire si il n'existe pas
	 * 
	 * @param idLivre
	 * @param idPresta
	 */
	public static void create(final String idLivre, final String idPresta) {
		final Map<String, Livre> livres = BDD.getInstance().getLivres();
		final Map<String, Prestataire> prestataires = BDD.getInstance().getPrestataires();
		if (idLivre != null) {
			if (idPresta != null) {
				createIfNotExist(idLivre, idPresta);
			} else {
				for (final String prestataire : prestataires.keySet()) {
					createIfNotExist(idLivre, prestataire);
				}
			}
		} else {
			if (idPresta != null) {
				for (final String livre : livres.keySet()) {
					createIfNotExist(livre, idPresta);
				}
			} else {
				for (final String prestataire : prestataires.keySet()) {
					for (final String livre : livres.keySet()) {
						createIfNotExist(livre, prestataire);
					}
				}
			}
		}
	}

	/**
	 * Creer le couple livre prestataire si il n'existe pas
	 * 
	 * @param idLivre
	 * @param idPresta
	 */
	private static void createIfNotExist(final String idLivre, final String idPresta) {
		final Map<String, CoupleLP> couples = BDD.getInstance().getRedevances();
		final String key = idLivre + "-" + idPresta;
		if (!couples.containsKey(key)) {
			couples.put(key, CoupleLP.create(idLivre, idPresta, BigDecimal.ZERO));
		}
	}

	public static void remove(final String idLivre, final String idPresta) {
		final Map<String, Livre> livres = BDD.getInstance().getLivres();
		final Map<String, Prestataire> prestataires = BDD.getInstance().getPrestataires();
		if (idLivre != null) {
			if (idPresta != null) {
				removeIfExist(idLivre, idPresta);
			} else {
				for (final String prestataire : prestataires.keySet()) {
					removeIfExist(idLivre, prestataire);
				}
			}
		} else {
			if (idPresta != null) {
				for (final String livre : livres.keySet()) {
					removeIfExist(livre, idPresta);
				}
			} else {
				for (final String prestataire : prestataires.keySet()) {
					for (final String livre : livres.keySet()) {
						removeIfExist(livre, prestataire);
					}
				}
			}
		}
	}

	public static void removeIfExist(final String idLivre, final String idPresta) {
		final Map<String, CoupleLP> couples = BDD.getInstance().getRedevances();
		final String key = idLivre + "-" + idPresta;
		if (couples.containsKey(key)) {
			couples.remove(key);
		}
	}
}
