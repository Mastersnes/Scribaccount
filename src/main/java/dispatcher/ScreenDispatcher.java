package dispatcher;

import java.util.Observable;

import javax.swing.JPanel;

public class ScreenDispatcher extends Observable {
	private JPanel current;

	/**
	 * @return the current
	 */
	public JPanel getCurrent() {
		return this.current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(final JPanel current) {
		if (current == null) {
			return;
		}
		this.current = current;
		setChanged();
		notifyObservers();
	}
}
