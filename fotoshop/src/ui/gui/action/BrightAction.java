package ui.gui.action;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import fotoshop.Editor;

/** Change the brightness of the current image */
public class BrightAction extends GUIAction {
	private static final long serialVersionUID = 1L;

	private float brightValue; // brightness

	private Dialog brightWindow;

	public BrightAction(Editor editor, Dialog brightWindow) {
		super(editor);
		this.brightWindow = brightWindow;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO not always dispose the brightwindow
		brightWindow.dispose();
		// TODO editor bright
		 editor.bright(brightValue);
	}

	public void setValue(float value) {
		brightValue = value;
	}

}
