package bdd;

import java.util.HashMap;
import java.util.Map;

import utils.JsonUtil;
import utils.Rattrapage;
import bdd.table.Achat;
import bdd.table.CoupleLP;
import bdd.table.Livre;
import bdd.table.Prestataire;
import bdd.table.VenteGroup;

import com.google.gson.Gson;

public class BDD {
	private static final Gson GSON = new Gson();
	private static BDD instance;

	/**
	 * Achat par annee et par id
	 */
	private final Map<String, Map<String, Achat>> achats = new HashMap<>();
	/**
	 * Vente par annee, par livre-typeVente et par id
	 */
	private final Map<String, Map<String, VenteGroup>> ventes = new HashMap<>();
	/**
	 * Prestataires par id
	 */
	private final Map<String, Prestataire> prestataires = new HashMap<>();
	/**
	 * Livres par id
	 */
	private final Map<String, Livre> livres = new HashMap<>();
	/**
	 * Couple Livre Prestataire par id
	 */
	private final Map<String, CoupleLP> redevances = new HashMap<>();

	private BDD() {
	}

	public static BDD getInstance() {
		if (instance == null) {
			if (!load()) {
				instance = new BDD();
			} else {
				Rattrapage.rattrapageLivreConcerne(instance);
			}
		}
		return instance;
	}

	/**
	 * @return the achats
	 */
	public Map<String, Map<String, Achat>> getAchats() {
		return achats;
	}

	/**
	 * @return the ventes
	 */
	public Map<String, Map<String, VenteGroup>> getVentes() {
		return ventes;
	}

	/**
	 * @return the prestataires
	 */
	public Map<String, Prestataire> getPrestataires() {
		return prestataires;
	}

	public Map<String, CoupleLP> getRedevances() {
		return redevances;
	}

	/**
	 * @return the livres
	 */
	public Map<String, Livre> getLivres() {
		return livres;
	}

	public void save() {
		final String json = GSON.toJson(instance);
		JsonUtil.getInstance().save(json);
	}

	private static boolean load() {
		final String json = JsonUtil.getInstance().load();
		JsonUtil.getInstance().secureSave(json);
		instance = GSON.fromJson(json, BDD.class);
		return instance != null;
	}
}
