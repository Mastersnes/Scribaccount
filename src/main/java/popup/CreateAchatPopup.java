package popup;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Collection;

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
import bdd.table.Livre;
import constantes.Phrases;
import constantes.TypeAchat;

@SuppressWarnings("serial")
public class CreateAchatPopup extends JDialog {
	private final JTextField nom = new JTextField(30);
	private final JComboBox<Livre> livreList = new JComboBox<>();
	private final JComboBox<TypeAchat> typeAchat = new JComboBox<>();
	private final JTextField quantite = new JTextField(10);
	private final JTextField prix = new JTextField(10);
	private boolean ok = false;
	private final JPanel panelLivre = new JPanel();

	public CreateAchatPopup() {
		super(Frame.getFrames()[0], Phrases.NOUVEL_ACHAT, true);
		setSize(500, 300);
		setLocationRelativeTo(null);
		add(createContent());
		typeAchat.setModel(new DefaultComboBoxModel<>(TypeAchat.values()));
		setVisible(false);
	}

	private Component createContent() {
		final JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(createNom());
		content.add(createType());
		content.add(createLivre());
		content.add(createQuantite());
		content.add(createPrix());
		content.add(createOk());
		return content;
	}

	private JPanel createNom() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.NOM)));
		panel.add(this.nom);
		return panel;
	}

	private JPanel createType() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.TYPE_ACHAT)));
		panel.add(this.typeAchat);
		typeAchat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final TypeAchat type = (TypeAchat) typeAchat.getSelectedItem();
				if ((type == TypeAchat.ACHAT_STOCK) || (type == TypeAchat.PERTE_STOCK)) {
					panelLivre.setVisible(true);
				} else {
					panelLivre.setVisible(false);
				}
			}
		});
		return panel;
	}

	private JPanel createLivre() {
		panelLivre.add(new JLabel(Phrases.enForme(Phrases.LIVRE_CONCERNE)));
		panelLivre.add(this.livreList);
		return panelLivre;
	}

	private JPanel createQuantite() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.QUANTITE)));
		panel.add(this.quantite);
		return panel;
	}

	private JPanel createPrix() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.PRIX)));
		panel.add(this.prix);
		return panel;
	}

	private JPanel createOk() {
		final JPanel panel = new JPanel();
		final JButton button = new JButton(Phrases.OK);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				ok = true;
				dispose();
			}
		});
		return panel;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom.getText();
	}

	/**
	 * @return the typeList
	 */
	public TypeAchat getTypeAchat() {
		return (TypeAchat) typeAchat.getSelectedItem();
	}

	public Livre getLivre() {
		final TypeAchat type = (TypeAchat) typeAchat.getSelectedItem();
		if ((type == TypeAchat.ACHAT_STOCK) || (type == TypeAchat.PERTE_STOCK)) {
			return (Livre) livreList.getSelectedItem();
		} else {
			return null;
		}
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return Integer.valueOf(quantite.getText());
	}

	/**
	 * @return the prix
	 */
	public BigDecimal getPrix() {
		return new BigDecimal(prix.getText());
	}

	public void load() {
		final Collection<Livre> livres = BDD.getInstance().getLivres().values();
		final ComboBoxModel<Livre> livreModel = new DefaultComboBoxModel<>(livres.toArray(new Livre[livres.size()]));
		this.livreList.setModel(livreModel);

		nom.setText("");
		typeAchat.setSelectedIndex(0);
		quantite.setText("");
		prix.setText("");
		ok = false;
		setVisible(true);
	}

	public boolean isOk() {
		return ok;
	}

}
