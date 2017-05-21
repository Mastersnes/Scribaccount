package utils;

import java.util.Map;

import bdd.BDD;
import bdd.table.Achat;
import bdd.table.Livre;
import constantes.TypeAchat;

public class Rattrapage {

	/**
	 * Rattrapage des données suite à un bug Lorsque le type d'achat n'est ni
	 * Achat de stock ni perte de stock Il n'y a pas de livre concerné
	 * 
	 * @param instance
	 */
	public static void rattrapageLivreConcerne(final BDD instance) {
		final Map<String, Livre> livres = BDD.getInstance().getLivres();
		final Livre firstLivre = (Livre) livres.values().toArray()[0];
		final String livreParDefaut = firstLivre != null ? firstLivre.getId() : null;

		final Map<String, Map<String, Achat>> achats = instance.getAchats();
		for (final Map<String, Achat> achatsParId : achats.values()) {
			for (final Achat achat : achatsParId.values()) {
				final TypeAchat typeAchat = TypeAchat.find(achat.getIdTypeAchat());
				if ((typeAchat != TypeAchat.ACHAT_STOCK) && (typeAchat != TypeAchat.PERTE_STOCK)) {
					achat.setIdLivre(null);
				} else if (achat.getIdLivre() == null) {
					achat.setIdLivre(livreParDefaut);
				}
			}
		}
	}

}
