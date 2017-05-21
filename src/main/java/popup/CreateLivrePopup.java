package popup;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constantes.Phrases;

@SuppressWarnings("serial")
public class CreateLivrePopup extends JDialog {
	private boolean popupOk = false;
	private final JTextField titre = new JTextField(50);

	public CreateLivrePopup() {
		super(Frame.getFrames()[0], Phrases.NOUVEAU_LIVRE, true);
		add(createContent());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Component createContent() {
		final JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(createLivre());

		final JButton ok = new JButton(Phrases.OK);
		content.add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setOk(true);
				dispose();
			}
		});
		return content;
	}

	private JPanel createLivre() {
		final JPanel panel = new JPanel();
		panel.add(new JLabel(Phrases.enForme(Phrases.NOM)));
		panel.add(this.titre);
		return panel;
	}

	public String getTitre() {
		return this.titre.getText();
	}

	public void setOk(final boolean ok) {
		this.popupOk = ok;
	}

	public boolean isOk() {
		return this.popupOk;
	}
}
