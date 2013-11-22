package ui.gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSlider;

/** This window should appear when the user wants to set a brightness filter.
 * It shows a slider that controls the brightness level of the filter.
 */
public class BrightnessWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	public BrightnessWindow() {
		Container contentPane = getContentPane();
		
		JSlider slider = new JSlider();
		JButton okButton = new JButton("Ok");
		
		contentPane.add(slider);
		contentPane.add(okButton);
	}
}
