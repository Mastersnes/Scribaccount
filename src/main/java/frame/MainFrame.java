package frame;

import java.awt.GraphicsEnvironment;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dispatcher.MainMenu;
import dispatcher.ScreenDispatcher;
import dispatcher.ScreenEnum;

/**
 * Fenetre
 * 
 * @author 916792
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer {
	private final ScreenDispatcher screenDispatcher = new ScreenDispatcher();

	public MainFrame() {
		setVisible(true);

		final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setSize(graphicsEnvironment.getMaximumWindowBounds().getSize());

		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

		this.screenDispatcher.addObserver(this);

		setJMenuBar(new MainMenu(this.screenDispatcher));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		screenDispatcher.setCurrent(ScreenEnum.ACCUEIL.getInstance());
	}

	@Override
	public void update(final Observable o, final Object arg) {
		final JPanel panel = this.screenDispatcher.getCurrent();
		setContentPane(panel);
		panel.updateUI();
	}

	public static void main(final String[] args) {
		new MainFrame();
	}
}
