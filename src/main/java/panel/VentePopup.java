package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.vente.VenteTableModel;
import bdd.table.VenteGroup;

/**
 * Ecran de vente par mois et par livre-prestaire
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class VentePopup extends JFrame {
    final JTable listeVentes = new JTable();
    final VenteTableModel venteModel;
    VenteGroup currentModel = null;
    VentePanel parentPanel = null;

    public VentePopup(final VentePanel parentPanel) {
        super("Popup listant des ventes");
        this.parentPanel = parentPanel;

        final JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        venteModel = new VenteTableModel(parentPanel, this);
        p.add(new JScrollPane(listeVentes));
        final JButton button = new JButton("Supprimer la ligne");
        p.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                eraseAll();
            }
        });

        setContentPane(p);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void eraseAll() {
        if (currentModel != null) {
            parentPanel.remove(currentModel);
            setVisible(false);
        }
    }

    public void show(final VenteGroup model) {
        this.currentModel = model;
        venteModel.setModel(model);
        listeVentes.setModel(venteModel);
        venteModel.fireTableDataChanged();
        pack();
        setVisible(true);
    }

}
