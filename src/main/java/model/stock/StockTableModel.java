package model.stock;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Stock;
import constantes.Phrases;

public class StockTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -1263574460361261826L;
	private List<Stock> model;

	@Override
	public int getRowCount() {
		return model.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Phrases.NOM;
		case 1:
			return Phrases.DATE;
		case 2:
			return Phrases.QUANTITE;
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
		final Stock item = this.model.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return item.getNom();
		case 1:
			return item.getMois();
		case 2:
			return item.getQuantite();
		default:
			return "";
		}
	}

	public void setModel(final List<Stock> model) {
		this.model = model;
	}

}
