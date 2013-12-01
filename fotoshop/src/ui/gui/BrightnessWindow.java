package ui.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.gui.action.BrightAction;
import filter.BrightFilter;
import fotoshop.Editor;

/**
 * This window (modal dialog) should appear when the user wants to set a
 * brightness filter. It shows a slider that controls the brightness level of
 * the filter. It is called either from the MainWindow, or the SequenceWindow
 */
public class BrightnessWindow extends JDialog {
	private static final long serialVersionUID = 1L;

	private static final String TEXT = "Adjust the brightness: ",
			TITLE = "Brightness";
	private static final int MIN = 0, MAX = 200, INIT = 100;

	private SequenceWindow seqWindow;

	private JLabel label;
	private JSlider slider;

	private BrightAction brightAction;

	public BrightnessWindow(Editor editor) {
		super((Frame) null, true); // modal
		brightAction = new BrightAction(editor, this);
		init();
	}

	/**
	 * This contructor is used when this dialog is called from the sequence
	 * dialog
	 */
	public BrightnessWindow(SequenceWindow seqWindow) {
		super(seqWindow, true); // modal
		this.seqWindow = seqWindow;
		init();
	}

	private void init() {
		setTitle(TITLE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		label = new JLabel();
		label.setAlignmentX(CENTER_ALIGNMENT);
		slider = new JSlider(MIN, MAX, INIT);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		JButton okButton = new JButton("Ok");

		okButton.setAlignmentX(CENTER_ALIGNMENT);
		updateBrightness();

		contentPane.add(label);
		contentPane.add(slider);
		contentPane.add(okButton);

		// action listener
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateBrightness();
			}
		});

		if (seqWindow == null) { // if we are from the MainWindow
			okButton.addActionListener(brightAction);
		} else { // from the sequence window
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					seqWindow.insertFilter(new BrightFilter(getValue()));
					dispose();
				}
			});
		}

		pack();
	}

	private void updateBrightness() {
		label.setText(TEXT + slider.getValue() + "%");
		if (brightAction != null) {
			brightAction.setValue(getValue());
		}
	}

	private float getValue() {
		return slider.getValue() / 100f;
	}
}
