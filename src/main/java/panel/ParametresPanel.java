package panel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.parametre.CoupleLPTableModel;
import model.parametre.LivreTableModel;
import model.parametre.PrestataireTableModel;
import popup.CreateLivrePopup;
import popup.CreatePrestatairePopup;
import bdd.BDD;
import constantes.Phrases;
import constantes.Support;

/**
 * Ecran de configuration des parametres
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class ParametresPanel extends Screen {
	final LivreTableModel livreModel = new LivreTableModel();
	final JTable listeLivres = new JTable();

	final PrestataireTableModel prestataireModel = new PrestataireTableModel();
	final JTable listePrestataires = new JTable();

	final CoupleLPTableModel coupleModel = new CoupleLPTableModel();
	final JTable listeCoupleLP = new JTable();

	public ParametresPanel() {
		setLayout(new GridBagLayout());

		final JPanel livrePresta = new JPanel(new GridBagLayout());
		add(createLivres(), 0, 0, 0.5, 1, livrePresta);
		add(createPrestataires(), 1, 0, 0.5, 1, livrePresta);

		add(livrePresta, 0, 0, 1, 0.5, this);
		add(createCouple(), 0, 1, 1, 0.5, this);
	}

	private JPanel createLivres() {
		final JPanel panel = new JPanel(new GridBagLayout());

		final JScrollPane scrollPane = new JScrollPane(this.listeLivres);
		add(scrollPane, 0, 0, 1, 0.9, panel);

		final JButton addNewLivre = new JButton(Phrases.NOUVEAU_LIVRE);
		add(addNewLivre, 0, 1, 1, 0.01, panel);
		addNewLivre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final CreateLivrePopup popup = new CreateLivrePopup();
				if (popup.isOk()) {
					final String titre = popup.getTitre();
					if ((titre != null) && !"".equals(titre)) {
						ParametresPanel.this.livreModel.add(titre);
					}
				}
			}
		});

		return panel;
	}

	private JPanel createPrestataires() {
		final JPanel panel = new JPanel(new GridBagLayout());

		final JScrollPane scrollPane = new JScrollPane(this.listePrestataires);
		add(scrollPane, 0, 0, 1, 0.9, panel);

		final JButton addNewType = new JButton(Phrases.NOUVEAU_PRESTA);
		add(addNewType, 0, 1, 1, 0.01, panel);
		addNewType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final CreatePrestatairePopup popup = new CreatePrestatairePopup();
				if (popup.isOk()) {
					final String prestataire = popup.getPrestataire();
					final Support support = popup.getSupport();
					if ((prestataire != null) && !"".equals(prestataire)) {
						ParametresPanel.this.prestataireModel.add(prestataire, support.getId());
					}
				}
			}
		});

		return panel;
	}

	private JScrollPane createCouple() {
		final JScrollPane scrollPane = new JScrollPane(this.listeCoupleLP);
		return scrollPane;
	}

	@Override
	public void load() {
		this.coupleModel.setModel(BDD.getInstance().getRedevances());
		this.listeCoupleLP.setModel(this.coupleModel);

		this.livreModel.setModel(BDD.getInstance().getLivres());
		this.livreModel.setCoupleModel(coupleModel);
		this.listeLivres.setModel(this.livreModel);

		this.prestataireModel.setModel(BDD.getInstance().getPrestataires());
		this.prestataireModel.setCoupleModel(coupleModel);
		this.listePrestataires.setModel(this.prestataireModel);
	}
}
