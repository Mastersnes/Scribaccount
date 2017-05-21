package popup;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constantes.Phrases;
import constantes.Support;

@SuppressWarnings("serial")
public class CreatePrestatairePopup extends JDialog {
	private boolean ok = false;

	private final JTextField prestataire = new JTextField(50);
	private final JComboBox<Support> support = new JComboBox<>();

	public CreatePrestatairePopup() {
		super(Frame.getFrames()[0], Phrases.NOUVEAU_PRESTA, true);
		add(createContent());
		support.setModel(new DefaultComboBoxModel<>(Support.values()));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Component createContent() {
		final JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(createPrestataire());
		content.add(createSupport());
		content.add(createOk());
		return content;
	}

	private JPanel createSupport() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.SUPPORT)));
		panel.add(this.support);
		return panel;
	}

	/**
	 * @param content
	 */
	private JPanel createOk() {
		final JPanel panel = new JPanel();
		final JButton okButton = new JButton(Phrases.OK);
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setOk(true);
				dispose();
			}
		});
		return panel;
	}

	private JPanel createPrestataire() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.PRESTATAIRE)));
		panel.add(this.prestataire);
		return panel;
	}

	public String getPrestataire() {
		return this.prestataire.getText();
	}

	/**
	 * @return the {@link Support}
	 */
	public Support getSupport() {
		return (Support) support.getSelectedItem();
	}

	public void setOk(final boolean ok) {
		this.ok = ok;
	}

	public boolean isOk() {
		return this.ok;
	}
}
