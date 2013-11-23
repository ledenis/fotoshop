package ui.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;

import fotoshop.Editor;

import ui.gui.action.GUIAction;

public class ShowBrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	public ShowBrightAction(Component parent, Editor editor) {
		super(parent, editor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new BrightnessWindow().setVisible(true);
	}

}
