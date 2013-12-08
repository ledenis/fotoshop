package fotoshop.ui.gui.action;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import fotoshop.Editor;
import fotoshop.Sequence;

public class ApplySequenceAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	private JComboBox<Sequence> sequencesCombo;

	public ApplySequenceAction(Editor editor, JComboBox<Sequence> sequencesCombo) {
		super(editor);
		this.sequencesCombo = sequencesCombo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Sequence sequence = (Sequence) sequencesCombo.getSelectedItem();
		if (sequence != null) {
			editor.applySequence(sequence);
		}
	}

}
