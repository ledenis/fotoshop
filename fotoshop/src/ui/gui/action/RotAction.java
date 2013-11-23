package ui.gui.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import fotoshop.Editor;


public class RotAction extends GUIAction {
	private static final long serialVersionUID = 1L;
	
	public RotAction(Component parent, Editor editor) {
		super(parent, editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editor.rot90();
	}

}
