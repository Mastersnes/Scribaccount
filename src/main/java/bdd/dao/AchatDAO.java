package bdd.dao;

import java.util.HashMap;
import java.util.Map;

import bdd.BDD;
import bdd.table.Achat;

public class AchatDAO {
	/**
	 * Renvoi les achats du mois mentionné
	 * 
	 * @param month
	 * @return
	 */
	public static Map<String, Achat> getByMonth(final String month) {
		final Map<String, Map<String, Achat>> achats = BDD.getInstance().getAchats();
		Map<String, Achat> listAchat = achats.get(month);
		if (listAchat == null) {
			listAchat = new HashMap<>();
			achats.put(month, listAchat);
		}
		return listAchat;
	}

	/**
	 * Renvoi le premier id disponible
	 * 
	 * @param newGroup
	 * @return
	 */
	public static String nextId(final Map<String, Achat> achatGroup) {
		int id = 0;
		while (achatGroup.containsKey(String.valueOf(id))) {
			id++;
		}
		return String.valueOf(id);
	}

}
