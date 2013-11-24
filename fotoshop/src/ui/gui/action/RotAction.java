package ui.gui.action;

import java.awt.event.ActionEvent;

import fotoshop.Editor;


public class RotAction extends GUIAction {
	private static final long serialVersionUID = 1L;
	
	public RotAction(Editor editor) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editor.rot90();
	}

}
