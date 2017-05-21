package popup;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.PrestataireDAO;
import bdd.table.CoupleLP;
import bdd.table.Livre;
import bdd.table.Prestataire;
import constantes.Phrases;

@SuppressWarnings("serial")
public class CreateVentePopup extends JDialog {
	private final JComboBox<Livre> livreList = new JComboBox<>();
	private final JComboBox<Prestataire> typeList = new JComboBox<>();
	private final JTextField prixLibre = new JTextField(10);
	private final JTextField quantite = new JTextField(10);
	private final JLabel redevance = new JLabel("XX");
	private boolean ok = false;

	public CreateVentePopup() {
		super(Frame.getFrames()[0], Phrases.NOUVELLE_VENTE, true);
		setSize(300, 300);
		setLocationRelativeTo(null);
		add(createContent());
		load();
	}

	private Component createContent() {
		final JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(createLivre());
		content.add(createPrestataire());
		content.add(createPrix());
		content.add(createQuantite());
		content.add(createOk());
		return content;
	}

	private JPanel createPrix() {
		final JPanel panel = new JPanel();

		panel.add(new JLabel(Phrases.enForme(Phrases.REDEVANCE)));
		panel.add(this.redevance);
		panel.add(this.prixLibre);
		panel.add(new JLabel(Phrases.EURO));

		return panel;
	}

	private JPanel createQuantite() {
		final JPanel panel = new JPanel();

		panel.add(new JLabel(Phrases.enForme(Phrases.QUANTITE)));
		panel.add(this.quantite);

		return panel;
	}

	private JPanel createLivre() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.LIVRE)));
		panel.add(this.livreList);
		this.livreList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				checkType();
			}
		});
		return panel;
	}

	private JPanel createPrestataire() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.PRESTATAIRE)));
		this.typeList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				checkType();
			}
		});
		panel.add(this.typeList);
		return panel;
	}

	protected void checkType() {
		final Livre livre = (Livre) livreList.getSelectedItem();
		final Prestataire type = (Prestataire) typeList.getSelectedItem();
		final CoupleLP couple = CoupleLPDAO.getById(livre.getId(), type.getId());
		prixLibre.setVisible("-1".equals(type.getId()));
		redevance.setVisible(!"-1".equals(type.getId()));

		if (couple != null) {
			redevance.setText(String.valueOf(couple.getRedevance()));
		} else {
			redevance.setText("0");
		}
	}

	private JPanel createOk() {
		final JPanel panel = new JPanel();
		final JButton button = new JButton(Phrases.OK);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				ok = true;
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}

	public void load() {
		final Collection<Livre> livres = BDD.getInstance().getLivres().values();
		final ComboBoxModel<Livre> livreModel = new DefaultComboBoxModel<>(livres.toArray(new Livre[livres.size()]));
		this.livreList.setModel(livreModel);

		final Prestataire venteLibre = PrestataireDAO.getById("-1");
		final List<Prestataire> listPrestataire = new ArrayList<>(BDD.getInstance().getPrestataires().values());
		listPrestataire.add(0, venteLibre);
		final ComboBoxModel<Prestataire> prestaModel = new DefaultComboBoxModel<>(
				listPrestataire.toArray(new Prestataire[listPrestataire.size()]));
		this.typeList.setModel(prestaModel);

		this.quantite.setText("1");

		checkType();

		setVisible(true);
	}

	/**
	 * @return the livreList
	 */
	public final String getLivre() {
		final Livre livre = (Livre) this.livreList.getSelectedItem();
		return livre.getId();
	}

	/**
	 * @return the typeList
	 */
	public final String getPrestataire() {
		final Prestataire presta = (Prestataire) this.typeList.getSelectedItem();
		return presta.getId();
	}

	/**
	 * @return the prixLibre
	 */
	public final BigDecimal getPrixLibre() {
		if ("".equals(prixLibre.getText())) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(prixLibre.getText());
	}

	/**
	 * Renvoi la quantite
	 * 
	 * @return
	 */
	public int getQuantite() {
		return Integer.parseInt(quantite.getText());
	}

	public boolean isOk() {
		return ok;
	}
}
