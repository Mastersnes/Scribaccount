package model.parametre;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.PrestataireDAO;
import bdd.table.Prestataire;
import constantes.Phrases;
import constantes.Support;

@SuppressWarnings("serial")
public class PrestataireTableModel extends AbstractTableModel {
	private Map<String, Prestataire> model;
	private CoupleLPTableModel coupleModel;

	@Override
	public int getRowCount() {
		return this.model != null ? this.model.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Phrases.PRESTATAIRE;
		case 1:
			return Phrases.SUPPORT;
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
		final Prestataire item = this.model.values().toArray(new Prestataire[this.model.size()])[rowIndex];
		switch (columnIndex) {
		case 0:
			return item.getNom();
		case 1:
			return Support.findById(item.getSupport());
		default:
			return "";
		}
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		final String newValue = (String) aValue;
		final Prestataire item = this.model.values().toArray(new Prestataire[this.model.size()])[rowIndex];
		switch (columnIndex) {
		case 0:
			if ("".equals(newValue)) {
				this.model.remove(item.getId());
				CoupleLPDAO.remove(null, item.getId());
			} else {
				this.model.get(item.getId()).setNom(newValue);
			}
			break;
		case 1:
			final Support support = Support.find(newValue);
			if (support != null) {
				this.model.get(item.getId()).setSupport(support.getId());
			}
			break;
		default:
			break;
		}
		BDD.getInstance().save();
		fireTableDataChanged();
		coupleModel.fireTableDataChanged();
	}

	public void setModel(final Map<String, Prestataire> model) {
		this.model = model;
	}

	public void setCoupleModel(final CoupleLPTableModel coupleModel) {
		this.coupleModel = coupleModel;
	}

	public void add(final String prestataire, final String support) {
		final String id = PrestataireDAO.nextId();
		final Prestataire type = Prestataire.create(id, prestataire, support);
		this.model.put(type.getId(), type);

		CoupleLPDAO.create(null, type.getId());
		BDD.getInstance().save();
		fireTableDataChanged();
		coupleModel.fireTableDataChanged();
	}

}
