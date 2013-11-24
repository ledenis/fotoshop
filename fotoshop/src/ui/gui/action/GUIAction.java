package ui.gui.action;

import java.awt.Component;

import javax.swing.AbstractAction;

import fotoshop.Editor;

public abstract class GUIAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	protected Editor editor;
	protected Component parent;

	public GUIAction(Component parent, Editor editor) {
		this.parent = parent;
		this.editor = editor;
	}

}
