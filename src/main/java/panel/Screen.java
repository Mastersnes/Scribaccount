package panel;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Screen extends JPanel {
	public abstract void load();

	protected void add(final Component panel, final int x, final int y, final double w, final double h,
			final JPanel pere) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = 1;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.weightx = w;
		constraints.weighty = h;
		pere.add(panel, constraints);
	}
}
