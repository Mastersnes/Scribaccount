package bdd.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.bean.Stock;
import model.vente.VenteGroupTableModel;
import bdd.BDD;
import bdd.table.Achat;
import bdd.table.VenteGroup;
import constantes.Phrases;
import constantes.TypeAchat;

public class StockDAO {
	public static Map<TypeAchat, List<Stock>> getStockageDestockage(final String idLivre) {
		boolean hasChanged = false;
		final Map<TypeAchat, List<Stock>> stocks = new HashMap<>();
		stocks.put(TypeAchat.ACHAT_STOCK, new ArrayList<Stock>());
		stocks.put(TypeAchat.PERTE_STOCK, new ArrayList<Stock>());

		final Map<String, Map<String, Achat>> achatsParMois = BDD.getInstance().getAchats();
		for (final Entry<String, Map<String, Achat>> entry : achatsParMois.entrySet()) {
			for (final Achat achat : entry.getValue().values()) {
				final TypeAchat type = TypeAchat.findById(achat.getIdTypeAchat());
				final List<Stock> achatPerte = stocks.get(type);
				if (achatPerte != null) {
					if (achat.getIdLivre() == null) {
						achat.setIdLivre("0");
						hasChanged = true;
					}
					final Stock stock = Stock.create(achat.getIdLivre(), achat.getNom(), entry.getKey(),
							achat.getQuantite(), achat.getPrix());
					if (achat.getIdLivre().equals(idLivre)) {
						achatPerte.add(stock);
					}
				}
			}
		}

		final Map<String, Map<String, VenteGroup>> ventesParMois = BDD.getInstance().getVentes();
		for (final Entry<String, Map<String, VenteGroup>> entry : ventesParMois.entrySet()) {
			for (final VenteGroup venteGroupe : entry.getValue().values()) {
				if (venteGroupe.getIdLivre().equals(idLivre) && "-1".equals(venteGroupe.getIdPresta())) {
					final BigDecimal total = VenteGroupTableModel.calculTotalGroup(venteGroupe);
					final Stock stock = Stock.create(venteGroupe.getIdLivre(), Phrases.VENTE_LIBRE, entry.getKey(),
							venteGroupe.getListVente().size(), total);
					final List<Stock> perte = stocks.get(TypeAchat.PERTE_STOCK);
					perte.add(stock);
				}
			}
		}

		if (hasChanged) {
			BDD.getInstance().save();
		}

		return stocks;
	}
}
