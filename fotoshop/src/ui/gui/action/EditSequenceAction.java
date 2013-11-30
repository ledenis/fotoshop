package ui.gui.action;

import java.awt.event.ActionEvent;

import ui.gui.SequenceWindow;

import fotoshop.Editor;

public class EditSequenceAction extends GUIAction {
	private static final long serialVersionUID = 1L;
	
	public EditSequenceAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		new SequenceWindow(editor).setVisible(true);
	}

}
