package ui.gui.action;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;

import fotoshop.Editor;

public class BrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	private int brightValue;

	public BrightAction(Component parent, Editor editor, int value) {
		super(parent, editor);
		brightValue = value;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		((Dialog) parent).dispose();
		// TODO editor bright
		// editor.bright(brightValue);
	}

	public void setValue(int value) {
		brightValue = value;
	}

}
