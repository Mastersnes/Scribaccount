package panel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.achat.AchatTableModel;
import popup.CreateAchatPopup;
import bdd.dao.AchatDAO;
import bdd.dao.VenteDAO;
import bdd.table.Achat;
import bdd.table.Livre;
import constantes.Phrases;
import constantes.TypeAchat;

/**
 * Ecran de declaration d'achat par mois
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class AchatPanel extends Screen {
	final JLabel month = new JLabel("XXX XXX");
	final JLabel total = new JLabel("XX.XX");
	final JTable listeAchat = new JTable();
	final AchatTableModel achatModel = new AchatTableModel(this);
	final CreateAchatPopup popup = new CreateAchatPopup();
	private Date currentDate;

	public AchatPanel() {
		setLayout(new GridBagLayout());

		add(createMonth(), 0, 0, 1, 0.01, this);
		add(createListAchat(), 0, 1, 1, 1, this);
		add(createTotal(), 0, 2, 1, 0.01, this);
		add(createAdd(), 0, 3, 1, 0.01, this);
	}

	private JPanel createAdd() {
		final JPanel panel = new JPanel();
		final JButton add = new JButton(Phrases.NOUVEL_ACHAT);
		panel.add(add);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				popup.load();
				if (popup.isOk()) {
					final String nom = popup.getNom();
					final Livre livre = popup.getLivre();
					final TypeAchat type = popup.getTypeAchat();
					final int quantite = popup.getQuantite();
					final BigDecimal prix = popup.getPrix();
					if ((nom != null) && !"".equals(nom)) {
						achatModel.add(nom, livre != null ? livre.getId() : null, type.getId(), quantite, prix);
					}
				}
			}
		});

		return panel;
	}

	private JPanel createMonth() {
		final JPanel panel = new JPanel();

		final JButton preview = new JButton(Phrases.BOUTON_PRECEDENT);
		panel.add(preview);
		preview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.MONTH, -1);
				load(cal.getTime());
			}
		});

		panel.add(new JLabel(Phrases.ACHAT_POUR_MOIS));
		panel.add(this.month);

		final JButton next = new JButton(Phrases.BOUTON_SUIVANT);
		panel.add(next);
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.MONTH, 1);
				load(cal.getTime());
			}
		});

		return panel;
	}

	private JScrollPane createListAchat() {
		final JScrollPane scroll = new JScrollPane(this.listeAchat);
		return scroll;
	}

	private JPanel createTotal() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.TOTAL)));
		panel.add(this.total);
		panel.add(new JLabel(Phrases.EURO));

		return panel;
	}

	@Override
	public void load() {
		this.load(Calendar.getInstance().getTime());
	}

	public void load(final Date dateSeach) {
		final String mois = VenteDAO.formatter2.format(dateSeach);
		final Map<String, Achat> achats = AchatDAO.getByMonth(mois);
		achatModel.setModel(achats);
		this.listeAchat.setModel(achatModel);
		achatModel.fireTableDataChanged();

		this.month.setText(mois);
		this.currentDate = dateSeach;

		refreshTotal();
	}

	public void refreshTotal() {
		final BigDecimal total = achatModel.calculTotal();
		this.total.setText(String.valueOf(total));
	}
}
