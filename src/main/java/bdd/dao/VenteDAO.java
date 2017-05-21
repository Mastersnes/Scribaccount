package bdd.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import bdd.BDD;
import bdd.table.VenteGroup;

public class VenteDAO {
	public static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
	public static final SimpleDateFormat formatter2 = new SimpleDateFormat("MMMM yyyy");

	/**
	 * Renvoi les vente du mois mentionné
	 * 
	 * @param month
	 * @return
	 */
	public static Map<String, VenteGroup> getByMonth(final String month) {
		final Map<String, Map<String, VenteGroup>> ventes = BDD.getInstance().getVentes();
		Map<String, VenteGroup> listVente = ventes.get(month);
		if (listVente == null) {
			listVente = new HashMap<>();
			ventes.put(month, listVente);
		}
		return listVente;
	}

	/**
	 * Transorme une date en mois + annee (exemple Juin 2016)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToMonth(final String date) {
		try {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(date));
			return formatter2.format(calendar.getTime());
		} catch (final ParseException e) {
			System.out.println("ERROR : Impossible de parser la date");
		}
		return null;
	}

	/**
	 * Renvoi le premier id disponible
	 * 
	 * @param newGroup
	 * @return
	 */
	public static String nextId(final VenteGroup newGroup) {
		int id = 0;
		while (newGroup.getListVente().containsKey(String.valueOf(id))) {
			id++;
		}
		return String.valueOf(id);
	}

}
