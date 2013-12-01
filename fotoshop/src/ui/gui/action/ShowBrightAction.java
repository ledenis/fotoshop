package ui.gui.action;

import java.awt.Component;
import java.awt.event.ActionEvent;

import ui.gui.BrightnessWindow;
import fotoshop.Editor;

public class ShowBrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public ShowBrightAction(Editor editor, Component parent) {
		super(editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BrightnessWindow(editor).setVisible(true);
	}

	@Override
	protected String getName() {
		return "Brightness...";
	}
	
	

}
