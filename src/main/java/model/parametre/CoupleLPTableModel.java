package model.parametre;

import java.math.BigDecimal;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.LivreDAO;
import bdd.dao.PrestataireDAO;
import bdd.table.CoupleLP;
import bdd.table.Livre;
import bdd.table.Prestataire;
import constantes.Phrases;
import constantes.Support;

@SuppressWarnings("serial")
public class CoupleLPTableModel extends AbstractTableModel {
	private Map<String, CoupleLP> model;

	@Override
	public int getRowCount() {
		return this.model != null ? this.model.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Phrases.LIVRE;
		case 1:
			return Phrases.PRESTATAIRE;
		case 2:
			return Phrases.SUPPORT;
		case 3:
			return Phrases.REDEVANCE;
		default:
			return "";
		}
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return columnIndex == 3;
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final CoupleLP item = this.model.values().toArray(new CoupleLP[this.model.size()])[rowIndex];
		final Prestataire prestataire;
		switch (columnIndex) {
		case 0:
			final Livre livre = LivreDAO.getById(item.getIdLivre());
			return livre.getNom();
		case 1:
			prestataire = PrestataireDAO.getById(item.getIdPresta());
			return prestataire.getNom();
		case 2:
			prestataire = PrestataireDAO.getById(item.getIdPresta());
			return Support.findById(prestataire.getSupport());
		case 3:
			return item.getRedevance();
		default:
			return "";
		}
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		final String newValue = (String) aValue;
		final CoupleLP item = this.model.values().toArray(new CoupleLP[this.model.size()])[rowIndex];
		if (columnIndex == 3) {
			CoupleLPDAO.getById(item.getIdLivre(), item.getIdPresta()).setRedevance(new BigDecimal(newValue));
		}
		BDD.getInstance().save();
		fireTableDataChanged();
	}

	public void setModel(final Map<String, CoupleLP> model) {
		this.model = model;
	}
}
