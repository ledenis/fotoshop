package ui.gui.action;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import ui.gui.BrightnessWindow;
import fotoshop.Editor;

public class ShowBrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public ShowBrightAction(Component parent, Editor editor) {
		super(parent, editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BrightnessWindow((Frame) parent, editor).setVisible(true);
	}

}
