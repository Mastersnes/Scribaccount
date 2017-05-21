package model.parametre;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.LivreDAO;
import bdd.table.Livre;
import constantes.Phrases;

@SuppressWarnings("serial")
public class LivreTableModel extends AbstractTableModel {
	private Map<String, Livre> model;
	private CoupleLPTableModel coupleModel;

	@Override
	public int getRowCount() {
		return this.model != null ? this.model.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Phrases.TITRE;
		default:
			return "";
		}
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return true;
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Livre item = this.model.values().toArray(new Livre[this.model.size()])[rowIndex];
		switch (columnIndex) {
		case 0:
			return item.getNom();
		default:
			return "";
		}
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		final String newValue = (String) aValue;
		final Livre item = this.model.values().toArray(new Livre[this.model.size()])[rowIndex];

		if ("".equals(newValue)) {
			this.model.remove(item.getId());
			CoupleLPDAO.remove(item.getId(), null);
		} else {
			this.model.get(item.getId()).setNom(newValue);
		}
		BDD.getInstance().save();
		fireTableDataChanged();
		coupleModel.fireTableDataChanged();
	}

	public void setModel(final Map<String, Livre> model) {
		this.model = model;
	}

	public void setCoupleModel(final CoupleLPTableModel coupleModel) {
		this.coupleModel = coupleModel;
	}

	public void add(final String titre) {
		final String id = LivreDAO.nextId();

		final Livre livre = Livre.create(id, titre);
		this.model.put(livre.getId(), livre);

		CoupleLPDAO.create(livre.getId(), null);
		BDD.getInstance().save();
		fireTableDataChanged();
		coupleModel.fireTableDataChanged();
	}

}
