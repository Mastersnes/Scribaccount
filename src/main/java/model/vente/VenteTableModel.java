package model.vente;

import java.math.BigDecimal;

import javax.swing.table.AbstractTableModel;

import panel.VentePanel;
import panel.VentePopup;
import bdd.BDD;
import bdd.dao.CoupleLPDAO;
import bdd.dao.LivreDAO;
import bdd.dao.PrestataireDAO;
import bdd.table.Prestataire;
import bdd.table.Vente;
import bdd.table.VenteGroup;
import constantes.Phrases;
import constantes.Support;

@SuppressWarnings("serial")
public class VenteTableModel extends AbstractTableModel {
    private VenteGroup model;
    private final VentePanel panel;
    private final VentePopup popup;

    public VenteTableModel(final VentePanel panel, final VentePopup popup) {
        super();
        this.panel = panel;
        this.popup = popup;
    }

    @Override
    public int getRowCount() {
        return this.model != null ? this.model.getListVente().size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
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
        final Vente item = this.model.getListVente().values().toArray(new Vente[this.model.getListVente().size()])[rowIndex];
        final Prestataire prestataire = PrestataireDAO.getById(model.getIdPresta());
        switch (columnIndex) {
            case 0:
                return LivreDAO.getById(model.getIdLivre());
            case 1:
                return prestataire.getNom();
            case 2:
                return Support.findById(prestataire.getSupport());
            case 3:
                if ("-1".equals(model.getIdPresta())) {
                    return item.getPrixLibre();
                } else {
                    return CoupleLPDAO.getById(model.getIdLivre(), model.getIdPresta()).getRedevance();
                }
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
        final String newValue = (String) aValue;
        final Vente item = this.model.getListVente().values().toArray(new Vente[this.model.getListVente().size()])[rowIndex];
        switch (columnIndex) {
            case 0:
                if ("".equals(newValue)) {
                    this.model.getListVente().remove(item.getId());
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                try {
                    if ("-1".equals(model.getIdPresta())) {
                        this.model.getListVente().get(item.getId()).setPrixLibre(new BigDecimal(newValue));
                    }
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
        if (this.model.getListVente().size() == 0)
            popup.eraseAll();
    }

    public void setModel(final VenteGroup model) {
        this.model = model;
    }
}
