package model.vente;

import java.math.BigDecimal;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import panel.VentePanel;
import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.LivreDAO;
import bdd.dao.PrestataireDAO;
import bdd.dao.VenteDAO;
import bdd.table.Prestataire;
import bdd.table.Vente;
import bdd.table.VenteGroup;
import constantes.Phrases;
import constantes.Support;

@SuppressWarnings("serial")
public class VenteTableModel extends AbstractTableModel {
	private Map<String, VenteGroup> model;
	private final VentePanel panel;

	public VenteTableModel(final VentePanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public int getRowCount() {
		return this.model != null ? this.model.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Phrases.TITRE;
		case 1:
			return Phrases.PRESTATAIRE;
		case 2:
			return Phrases.SUPPORT;
		case 3:
			return Phrases.QUANTITE;
		case 4:
			return Phrases.TOTAL;
		default:
			return "";
		}
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final VenteGroup item = this.model.values().toArray(new VenteGroup[model.size()])[rowIndex];
		final Prestataire prestataire = PrestataireDAO.getById(item.getIdPresta());
		switch (columnIndex) {
		case 0:
			return LivreDAO.getById(item.getIdLivre());
		case 1:
			return prestataire.getNom();
		case 2:
			return Support.findById(prestataire.getSupport());
		case 3:
			return item.getListVente().size();
		case 4:
			return calculTotalGroup(item);

		default:
			return "";
		}
	}

	public static BigDecimal calculTotalGroup(final VenteGroup item) {
		BigDecimal total = BigDecimal.ZERO;
		for (final Vente vente : item.getListVente().values()) {
			if ("-1".equals(item.getIdPresta())) {
				total = total.add(vente.getPrixLibre());
			} else {
				total = total.add(CoupleLPDAO.getById(item.getIdLivre(), item.getIdPresta()).getRedevance());
			}
		}
		return total;
	}

	public BigDecimal calculTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (final VenteGroup group : model.values()) {
			total = total.add(calculTotalGroup(group));
		}
		return total;
	}

	public void add(final String idLivre, final String idPresta, final BigDecimal prix) {
		final String groupKey = idLivre + Phrases.TRANSITION_LIVRE_PRESTA + idPresta;
		VenteGroup group = model.get(groupKey);
		if (group == null) {
			group = VenteGroup.create(idLivre, idPresta);
			model.put(groupKey, group);
		}
		final String id = VenteDAO.nextId(group);
		final Vente vente = Vente.create(id, prix);
		group.getListVente().put(vente.getId(), vente);
		BDD.getInstance().save();
		fireTableDataChanged();
		panel.refreshTotal();
	}

	public void setModel(final Map<String, VenteGroup> model) {
		this.model = model;
	}
}
