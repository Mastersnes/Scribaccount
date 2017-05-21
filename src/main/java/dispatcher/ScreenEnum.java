package dispatcher;

import panel.AccueilPanel;
import panel.AchatPanel;
import panel.BilanPanel;
import panel.CrPanel;
import panel.ParametresPanel;
import panel.Screen;
import panel.StockPanel;
import panel.VentePanel;
import constantes.Phrases;

public enum ScreenEnum {
	ACHAT(Phrases.ACHAT), VENTE(Phrases.VENTE), PARAMETRES(Phrases.PARAMETRES), CR(Phrases.COMPTE_RESULTAT), BILAN(
			Phrases.BILAN), STOCK(Phrases.GESTION_STOCK), ACCUEIL(Phrases.ACCUEIL);

	private String name;
	private Screen instance;

	private ScreenEnum(final String name) {
		this.name = name;
	}

	public Screen getInstance() {
		switch (this) {
		case ACCUEIL:
			if (this.instance == null) {
				this.instance = new AccueilPanel();
			}
			break;
		case ACHAT:
			if (this.instance == null) {
				this.instance = new AchatPanel();
			}
			break;
		case VENTE:
			if (this.instance == null) {
				this.instance = new VentePanel();
			}
			break;
		case CR:
			if (this.instance == null) {
				this.instance = new CrPanel();
			}
			break;
		case BILAN:
			if (this.instance == null) {
				this.instance = new BilanPanel();
			}
			break;
		case STOCK:
			if (this.instance == null) {
				this.instance = new StockPanel();
			}
			break;
		case PARAMETRES:
			if (this.instance == null) {
				this.instance = new ParametresPanel();
			}
			break;
		default:
		}
		if (this.instance != null) {
			this.instance.load();
		}
		return this.instance;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
