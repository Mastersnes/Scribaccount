package panel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.bean.Stock;
import model.stock.StockTableModel;
import bdd.BDD;
import bdd.dao.StockDAO;
import bdd.table.Livre;
import constantes.Phrases;
import constantes.TypeAchat;

/**
 * Ecran d'affichage des stocks
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class StockPanel extends Screen {
	private final JComboBox<Livre> livreList = new JComboBox<>();
	final JTable listeStockage = new JTable();
	final StockTableModel listeStockageModel = new StockTableModel();
	final JTable listeDestockage = new JTable();
	final StockTableModel listeDestockageModel = new StockTableModel();
	private final JLabel quantiteTotal = new JLabel("X");

	public StockPanel() {
		setLayout(new GridBagLayout());

		add(choixLivre(), 0, 0, 1, 0.01, this);
		add(createListStock(), 0, 1, 1, 1, this);
		add(createTotal(), 0, 2, 1, 0.01, this);
	}

	private JPanel choixLivre() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.STOCK_POUR));
		panel.add(this.livreList);
		this.livreList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Livre livre = (Livre) livreList.getSelectedItem();
				load(livre);
			}
		});
		return panel;
	}

	private JPanel createListStock() {
		final JPanel listStock = new JPanel(new GridBagLayout());
		add(createStock(listeStockage), 0, 0, 0.5, 1, listStock);
		add(createStock(listeDestockage), 1, 0, 0.5, 1, listStock);
		return listStock;
	}

	private JPanel createTotal() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.RESTE_STOCK));
		panel.add(quantiteTotal);
		return panel;
	}

	private JScrollPane createStock(final JTable table) {
		final JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	@Override
	public void load() {
		final Collection<Livre> livres = BDD.getInstance().getLivres().values();
		final ComboBoxModel<Livre> livreModel = new DefaultComboBoxModel<>(livres.toArray(new Livre[livres.size()]));
		livreList.setModel(livreModel);
		load((Livre) livres.toArray()[0]);
	}

	public void load(final Livre livre) {
		final Map<TypeAchat, List<Stock>> stockDestock = StockDAO.getStockageDestockage(livre.getId());
		listeStockageModel.setModel(stockDestock.get(TypeAchat.ACHAT_STOCK));
		listeStockage.setModel(listeStockageModel);
		listeDestockageModel.setModel(stockDestock.get(TypeAchat.PERTE_STOCK));
		listeDestockage.setModel(listeDestockageModel);

		int quantiteStock = 0;
		for (final Stock stock : stockDestock.get(TypeAchat.ACHAT_STOCK)) {
			quantiteStock += stock.getQuantite();
		}
		int quantiteDestock = 0;
		for (final Stock stock : stockDestock.get(TypeAchat.PERTE_STOCK)) {
			quantiteDestock += stock.getQuantite();
		}

		quantiteTotal.setText(String.valueOf(quantiteStock - quantiteDestock));

		this.repaint();
	}
}
