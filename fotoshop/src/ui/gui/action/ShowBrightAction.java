package ui.gui.action;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import ui.gui.BrightnessWindow;
import fotoshop.Editor;

public class ShowBrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	private Component parent; // used by the brightness window to be modal

	public ShowBrightAction(Editor editor, Component parent) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO parent can be the main window (frame) or sequence window (dialog)
		System.out.println(parent);
		new BrightnessWindow((Frame) parent, editor).setVisible(true);
	}

}
