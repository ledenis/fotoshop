package ui.gui.action;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.AbstractAction;

import fotoshop.Editor;

public abstract class GUIAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	protected Editor editor;
	protected Frame parent;
	
	public GUIAction(Component parent, Editor editor) {
		this.editor = editor;
	}

}
