package dispatcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import constantes.Phrases;

@SuppressWarnings("serial")
public class MainMenu extends JMenuBar {
	public MainMenu(final ScreenDispatcher screenDispatcher) {
		add(createScreen(ScreenEnum.ACHAT, screenDispatcher));
		add(createScreen(ScreenEnum.VENTE, screenDispatcher));
		add(createScreen(ScreenEnum.PARAMETRES, screenDispatcher));
		add(createConsultation(screenDispatcher));
	}

	private JMenuItem createScreen(final ScreenEnum screen, final ScreenDispatcher screenDispatcher) {
		final JMenuItem menu = new JMenuItem(screen.toString());

		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				screenDispatcher.setCurrent(screen.getInstance());
			}
		});

		return menu;
	}

	private JMenu createConsultation(final ScreenDispatcher screenDispatcher) {
		final JMenu menuConsultation = new JMenu(Phrases.CONSULTATION_COMPTABLE);
		menuConsultation.add(createScreen(ScreenEnum.CR, screenDispatcher));
		menuConsultation.add(createScreen(ScreenEnum.BILAN, screenDispatcher));
		menuConsultation.add(createScreen(ScreenEnum.STOCK, screenDispatcher));
		return menuConsultation;
	}
}
