package model.achat;

import java.math.BigDecimal;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import panel.AchatPanel;
import bdd.BDD;
import bdd.dao.AchatDAO;
import bdd.dao.LivreDAO;
import bdd.table.Achat;
import constantes.Phrases;
import constantes.TypeAchat;

@SuppressWarnings("serial")
public class AchatTableModel extends AbstractTableModel {
	private Map<String, Achat> model;
	private final AchatPanel panel;

	public AchatTableModel(final AchatPanel panel) {
		super();
		this.panel = panel;
	}

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
			return Phrases.NOM;
		case 1:
			return Phrases.TYPE_ACHAT;
		case 2:
			return Phrases.QUANTITE;
		case 3:
			return Phrases.PRIX;
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
		final Achat item = this.model.values().toArray(new Achat[this.model.size()])[rowIndex];
		switch (columnIndex) {
		case 0:
			return item.getNom();
		case 1:
			final TypeAchat typeAchat = TypeAchat.findById(item.getIdTypeAchat());
			final StringBuilder retour = new StringBuilder(typeAchat.toString());
			if (item.getIdLivre() != null) {
				retour.append(Phrases.TRANSITION_PERTE_ACHAT_STOCK).append(LivreDAO.getById(item.getIdLivre()));
			}
			return retour;
		case 2:
			return item.getQuantite();
		case 3:
			return item.getPrix();
		default:
			return "";
		}
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		final String newValue = (String) aValue;
		final Achat item = this.model.values().toArray(new Achat[this.model.size()])[rowIndex];
		switch (columnIndex) {
		case 0:
			if ("".equals(newValue)) {
				this.model.remove(item.getId());
			} else {
				this.model.get(item.getId()).setNom(newValue);
			}
			break;
		case 1:
			break;
		case 2:
			try {
				this.model.get(item.getId()).setQuantite(Integer.parseInt(newValue));
			} catch (final NumberFormatException e) {
				System.out.println("Veuillez inscrire un nombre");
			}
			break;
		case 3:
			try {
				this.model.get(item.getId()).setPrix(new BigDecimal(newValue));
			} catch (final NumberFormatException e) {
				System.out.println("Veuillez inscrire un nombre");
			}
			break;
		default:
			break;
		}
		BDD.getInstance().save();
		fireTableDataChanged();
		panel.refreshTotal();
	}

	public void setModel(final Map<String, Achat> model) {
		this.model = model;
	}

	public void add(final String nom, final String idLivre, final String idType, final int quantite,
			final BigDecimal prix) {
		final String id = AchatDAO.nextId(model);
		final Achat achat = Achat.create(id, nom, idLivre, idType, quantite, prix);
		this.model.put(achat.getId(), achat);
		BDD.getInstance().save();
		fireTableDataChanged();
		panel.refreshTotal();
	}

	public BigDecimal calculTotal() {
		BigDecimal total = BigDecimal.ZERO;
		if (model != null) {
			for (final Achat achat : model.values()) {
				total = total.add(achat.getPrix());
			}
		}
		return total;
	}
}
