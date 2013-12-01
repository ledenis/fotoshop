package ui.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import ui.gui.SequenceWindow;

import fotoshop.Editor;
import fotoshop.Sequence;

public class EditSequenceAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	JComboBox<Sequence> sequencesCombo; // null if in new mode

	public EditSequenceAction(Editor editor) {
		super(editor);
	}

	/**
	 * This constructor is used when the edit button of MainWindow is pressed
	 * 
	 * @param editor
	 * @param sequencesCombo
	 *            The comboBox that contains the sequences
	 */
	public EditSequenceAction(Editor editor, JComboBox<Sequence> sequencesCombo) {
		super(editor);
		this.sequencesCombo = sequencesCombo;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		SequenceWindow window = new SequenceWindow(editor);

		if (sequencesCombo != null && sequencesCombo.getSelectedIndex() != -1) {
			window.fillForm((Sequence) sequencesCombo.getSelectedItem());
		}
		window.setVisible(true);
	}

}
