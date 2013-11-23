package ui.gui;

import java.awt.Container;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This window should appear when the user wants to set a brightness filter. It
 * shows a slider that controls the brightness level of the filter.
 */
public class BrightnessWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String TEXT = "Adjust the brightness: ";

	private JLabel label;
	private JSlider slider;

	public BrightnessWindow(Frame parent) {
		super(parent, true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		label = new JLabel();
		label.setAlignmentX(CENTER_ALIGNMENT);
		slider = new JSlider();
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		updateText();
		JButton okButton = new JButton("Ok");
		// TODO: actionlistener
		// okButton.addActionListener(new BrightAction())
		okButton.setAlignmentX(CENTER_ALIGNMENT);

		contentPane.add(label);
		contentPane.add(slider);
		contentPane.add(okButton);

		// action listener
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateText();
			}
		});

		pack();
	}

	private void updateText() {
		label.setText(TEXT + slider.getValue() + "%");
	}
}
