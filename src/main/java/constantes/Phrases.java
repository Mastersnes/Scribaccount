package constantes;

public class Phrases {
	// Params
	public final static String VENTE_LIBRE = "Vente Libre";
	public static final String LIVRE = "Livre";
	public static final String EBOOK = "Ebook";
	public static final String LOGICIEL = "Logiciel";
	public static final String COPYRIGHT = "Copyright";
	public static final String MATERIEL = "Matériel";
	public static final String ACHAT_STOCK = "Achat de stock";
	public static final String PERTE_STOCK = "Perte de stock";
	public static final String EXCEPTIONELLE = "Exceptionnelle";
	public static final String PRESTATAIRE = "Préstataire";
	public static final String SUPPORT = "Support";
	public static final String REDEVANCE = "Redevance";
	public static final String TITRE = "Titre";

	// Ecrans
	public static final String CONSULTATION_COMPTABLE = "Consultation comptable";
	public static final String ACHAT = "Achat";
	public static final String VENTE = "Vente";
	public static final String PARAMETRES = "Paramétres";
	public static final String COMPTE_RESULTAT = "Compte de resultat";
	public static final String BILAN = "Bilan";
	public static final String GESTION_STOCK = "Gestion des stocks";
	public static final String ACCUEIL = "Accueil";

	// Achat/Vente
	public static final String NOM = "Nom";
	public static final String TYPE_ACHAT = "Type d'achat";
	public static final String QUANTITE = "Quantité";
	public static final String PRIX = "Prix";
	public static final String TRANSITION_PERTE_ACHAT_STOCK = " pour ";
	public static final String DATE = "Date";
	public static final String TOTAL = "Total";
	public static final String TRANSITION_LIVRE_PRESTA = "-";
	public static final String NOUVEL_ACHAT = "Nouvel Achat";
	public static final String BOUTON_PRECEDENT = "<";
	public static final String ACHAT_POUR_MOIS = "Achats pour le mois de : ";
	public static final String BOUTON_SUIVANT = ">";
	public static final String EURO = " €";
	public static final String NOUVEAU_LIVRE = "Nouveau Livre";
	public static final String NOUVEAU_PRESTA = "Nouveau Préstataire";
	public static final String STOCK_POUR = "Stock pour : ";
	public static final String RESTE_STOCK = "Reste de stock : ";
	public static final String NOUVELLE_VENTE = "Nouvelle vente";
	public static final String VENTE_POUR_MOIS = "Ventes pour le mois de : ";
	public static final String DEUX_POINT = ": ";
	public static final String LIVRE_CONCERNE = "Livre concerné";
	public static final String OK = "OK";
	public static final String MODIFIER = "Modifier";
	public static final String SUPPRIMER = "Supprimer";

	/**
	 * Fait suivre le libellé par deux point
	 * 
	 * @param libelle
	 * @return
	 */
	public static final String enForme(final String libelle) {
		return libelle + DEUX_POINT;
	}
}
