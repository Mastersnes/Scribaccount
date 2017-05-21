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

import model.vente.VenteTableModel;
import popup.CreateVentePopup;
import bdd.dao.VenteDAO;
import bdd.table.VenteGroup;
import constantes.Phrases;

/**
 * Ecran de vente par mois et par livre-prestaire
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class VentePanel extends Screen {
	final JLabel month = new JLabel("XXX XXX");
	final JLabel total = new JLabel("XX.XX");
	final JTable listeVenteGroup = new JTable();
	final VenteTableModel venteModel = new VenteTableModel(this);
	private Date currentDate;

	public VentePanel() {
		setLayout(new GridBagLayout());

		add(createMonth(), 0, 0, 1, 0.01, this);
		add(createListeVente(), 0, 1, 1, 1, this);
		add(createTotal(), 0, 2, 1, 0.01, this);
		add(createAdd(), 0, 3, 1, 0.01, this);
	}

	private JPanel createAdd() {
		final JPanel panel = new JPanel();
		final JButton add = new JButton(Phrases.NOUVELLE_VENTE);
		panel.add(add);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final CreateVentePopup popup = new CreateVentePopup();
				if (popup.isOk()) {
					final String idLivre = popup.getLivre();
					final String idPresta = popup.getPrestataire();
					final BigDecimal prix = popup.getPrixLibre();
					final int quantite = popup.getQuantite();
					if ((idLivre != null) && !"".equals(idLivre)) {
						for (int i = 0; i < quantite; i++) {
							venteModel.add(idLivre, idPresta, prix);
						}
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

		panel.add(new JLabel(Phrases.VENTE_POUR_MOIS));
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

	private JScrollPane createListeVente() {
		final JScrollPane scroll = new JScrollPane(this.listeVenteGroup);
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
		final Map<String, VenteGroup> ventes = VenteDAO.getByMonth(mois);
		venteModel.setModel(ventes);
		this.listeVenteGroup.setModel(venteModel);
		venteModel.fireTableDataChanged();

		this.month.setText(mois);
		this.currentDate = dateSeach;

		refreshTotal();
	}

	public void refreshTotal() {
		final BigDecimal total = venteModel.calculTotal();
		this.total.setText(String.valueOf(total));
	}
}
