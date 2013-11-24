package ui.gui.action;

import javax.swing.AbstractAction;

import fotoshop.Editor;

public abstract class GUIAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	protected Editor editor;

	public GUIAction(Editor editor) {
		this.editor = editor;
	}

}
