package ui.gui.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import fotoshop.Editor;

public class MonoAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public MonoAction(Component parent, Editor editor) {
		super(parent, editor);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		editor.mono();
	}

}
