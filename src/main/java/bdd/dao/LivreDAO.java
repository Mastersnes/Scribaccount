package bdd.dao;

import java.util.Map;

import bdd.BDD;
import bdd.table.Livre;

public class LivreDAO {

	/**
	 * Renvoi un livre par id
	 * 
	 * @param id
	 * @return
	 */
	public static Livre getById(final String id) {
		final Map<String, Livre> livres = BDD.getInstance().getLivres();
		return livres.get(id);
	}

	/**
	 * Renvoi le premier id libre
	 * 
	 * @return
	 */
	public static String nextId() {
		final Map<String, Livre> livres = BDD.getInstance().getLivres();
		int id = 0;
		while (livres.containsKey(String.valueOf(id))) {
			id++;
		}
		return String.valueOf(id);
	}

}
